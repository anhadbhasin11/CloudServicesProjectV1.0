package org.courseregistration.dbtests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * Responsibility of the class is to manage the life cycle of an entity manager
 */
public class HibernateUtils {
	private static EntityManagerFactory entityManagerFactory = null;
	private static EntityManager entityManager = null;

    /**
     * Initialize singleton instance of  entity manager
     */
	public static void initEntityManager() {
		if (entityManager == null) {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("student_registration_system");
			entityManager = entityManagerFactory.createEntityManager();
		}

	}

    /**
     * To provide singleton instance of  entity manager
     * @return singleton instance of entity manager
     */
	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			HibernateUtils.initEntityManager();
		}
		return entityManager;
	}

    /**
     * Close entity manager and entity manager factory
     */
	public static void closeEntityManager() {
		entityManager.close();
		entityManagerFactory.close();
	}


}
