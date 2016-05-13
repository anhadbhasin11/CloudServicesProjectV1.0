package org.courseregistration.dao;

import org.courseregistration.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StudentDAO extends GenericDAO<Student> {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentDAO.class);

    /**
     * Find students from database
     *
     * @return a list of students fetched from database
     */
	@Override
	public List<Student> findAll() {
		logger.debug("about to load all Students");
		String query = "from Student order by firstName asc";
		List<Student> students = entityManager
				.createQuery(query, Student.class).getResultList();
		logger.debug("returning all({}) Students", students.size());
		return students;
	}
    public Student findByName(String name) {
        try {
            return entityManager
                .createQuery(
                    "select s from Student s where s.firstName=:firstName",
                    Student.class).setParameter("firstName", name)
                .getSingleResult();
        } catch (Exception e) {
            return null;

        }
    }
}
