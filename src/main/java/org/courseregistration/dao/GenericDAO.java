package org.courseregistration.dao;

import org.courseregistration.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Responsibility of the class is perform CRUD operations on the entities involved in the system
 * To be able to perform operations mentioned in the class, Entities should implement Serializable interface .
 */
 abstract class GenericDAO<T extends BaseEntity> {
	private final Class<T> clazz;
    @PersistenceContext
	protected   EntityManager entityManager;
	private static final Logger logger = LoggerFactory
			.getLogger(GenericDAO.class);

	protected GenericDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        clazz = (Class) pt.getActualTypeArguments()[0];

	}

    /**
     * Finds an entity by primary key
     *
     * @param id primary key of an entity to be searched
     * @return returns an entity instance or null if the entity with given primary key does not exists
     */
    public T findById(Long id) {

        T toReturn = null;
        try {
            toReturn = entityManager.find(clazz, id);
        } catch (Exception exception) {
            logger.error("Error occurred finding an entity of type:{}", clazz.getName(), exception);
            throw exception;
        }

        return (toReturn);
    }

    /**
     * Find all entities
     * Returns a list of entities of any given type.
     * @return the list of found entity instances
     */
    public abstract List<T> findAll();

    /**
     * Update Entity objects with the given values
     * @param updatedObject an entity object to be updated/merged in db
     * @return returns an updated object
     */
    @Transactional
    public T update(T updatedObject) {
        logger.debug("Updating object of type: {}", clazz.getName());

        T mergedInstance;
        try {
            updatedObject.setUpdatedAt(new Date());
            mergedInstance = entityManager.merge(updatedObject);
        } catch (Exception exception) {
            logger.error("Error updating object of type: {}", clazz.getName(), exception);
            throw exception;
        }

        logger.debug("Done updating object of type: {}", clazz.getName());

        return mergedInstance;
    }

    /**
     * Save an Entity objects with the given values
     * @param newObject an entity object to be stored in db
     */
    @Transactional
    public void save(T newObject) {
        logger.debug("Saving new object of type: {}", clazz.getName());

        try {
            newObject.setUpdatedAt(new Date());
            entityManager.persist(newObject);
        } catch (Exception exception) {
            logger.error("Error saving object of type: {}", clazz.getName(), exception);
            throw exception;
        }
        logger.debug("Done saving object of type: {}", clazz.getName());
    }

    /**
     * Bulk save operation on Entity objects with the given values
     * @param entities entity objects object to be stored in db
     */
    @Transactional
    public void save(Collection<T> entities) {
        logger.debug("Saving new object of type: {}", clazz.getName());

        try {
            int counter = 1;
            for (T entity : entities) {
                entity.setUpdatedAt(new Date());
                entityManager.persist(entity);
                if ((counter % 10000) == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
        } catch (Exception exception) {
            logger.error("Error saving object of type: {}", clazz.getName(), exception);
            throw exception;
        }
        logger.debug("Done saving object of type: {}", clazz.getName());
    }

    /**
     * Delete  Entity objects with the given ids
     * @param ids primary keys of the entity objects to be deleted
     */
    @Transactional
    public int delete(Collection<Long> ids) {
        checkNotNull(ids, "ids should not be null");
        checkArgument(!ids.isEmpty(), "ids should not be empty");
        int count = entityManager.createQuery("delete from " + clazz.getName() + " where id in (:ids)")
            .setParameter("ids", ids).executeUpdate();
        logger.debug("Deleted {} Entity objects of type {} for given ids of size {}", clazz.getName(), count, ids.size());

        return count;
    }

    /**
     * Delete given entity object
     *
     * @param toDelete entity to delete
     */
    @Transactional
    public void delete(T toDelete) {
        logger.debug("Deleting a object of type: {}", clazz.getName());

        try {
            entityManager.remove(toDelete);
        } catch (Exception exception) {
            logger.error("Error deleting object of type: {}", clazz.getName(), exception);
            throw exception;
        }
        logger.debug("Done deleting object of type: {}", clazz.getName());
    }

    /**
     * Delete given entity object by its id
     *
     * @param id entity to delete
     */
    @Transactional
    public void delete(Long id) {
        T toDelete = entityManager.find(clazz, id);
        if (toDelete != null) {
            logger.debug("Deleting a object of type: {} with id: {}", clazz.getName(), id);
            try {
                entityManager.remove(toDelete);
            } catch (Exception exception) {
                logger.error("Error deleting object of type: {} with id: {}", clazz.getName(), id, exception);
                throw exception;
            }
            logger.debug("Done deleting a object of type: {} with id: {}", clazz.getName(), id);
        }

    }


}
