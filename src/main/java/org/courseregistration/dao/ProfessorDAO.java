package org.courseregistration.dao;


import org.courseregistration.model.Professor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An implementation of {@link org.courseregistration.dao.GenericDAO}
 * The responsibility of this class is to perform database operations on the Professor
 */
@Repository
public class ProfessorDAO extends GenericDAO<Professor> {


    private static final Logger logger = LoggerFactory.getLogger(ProfessorDAO.class);

    /**
     * Find list of Professors
     *
     * @return list of Professors fetched from the database ordered by first name of the professor
     */
    @Override
    public List<Professor> findAll() {
        logger.debug("about to load all Professors");
        String query = "from Professor order by firstName asc";
        List<Professor> professors = entityManager.createQuery(query, Professor.class)
            .getResultList();
        logger.debug("returning all({}) Professors", professors.size());
        return professors;
    }

}
