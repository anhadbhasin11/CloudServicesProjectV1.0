package org.courseregistration.dao;

import org.courseregistration.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An implementation of {@link org.courseregistration.dao.GenericDAO}
 * The responsibility of this class is to perform database operations on the Course
 */
@Repository
public class CourseDAO extends GenericDAO<Course>{

    private static final Logger logger = LoggerFactory.getLogger(CourseDAO.class);

    /**
     * Find list of Courses
     *
     * @return list of Courses fetched from the database ordered by course code
     */
    @Override
    public List<Course> findAll() {
        logger.debug("about to load all Courses");
        String query = "from Course order by name asc";
        List<Course> courses = this.entityManager.createQuery(query, Course.class)
            .getResultList();
        logger.debug("returning all({}) Courses", courses.size());
        return courses;
    }

    public List<Course> findAll(int pageNumber,int pageSize) {
        logger.debug("about to load all Courses");
        String query = "from Course order by name asc";
        List<Course> courses = this.entityManager.createQuery(query, Course.class)
            .setFirstResult((pageNumber - 1) * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
        logger.debug("returning all({}) Courses", courses.size());
        return courses;
    }



}
