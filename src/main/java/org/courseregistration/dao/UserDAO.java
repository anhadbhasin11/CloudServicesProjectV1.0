package org.courseregistration.dao;

import org.courseregistration.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO extends GenericDAO<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    public User findByUsername(String username) {

        User toReturn = null;
        try {
            toReturn = entityManager.createQuery("select u from User as u where u.username=:username",User.class)
            .setParameter("username",username)
            .getSingleResult();
        } catch (Exception exception) {
            logger.error("Error occurred finding an entity of type:User", exception);
            throw exception;
        }

        return (toReturn);
    }
}
