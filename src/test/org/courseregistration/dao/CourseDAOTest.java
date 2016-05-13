package org.courseregistration.dao;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.courseregistration.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(BlockJUnit4ClassRunner.class)
public class CourseDAOTest extends BaseTest {

    @Test
    public void findAll_courses_asc_order() throws Exception {
        CourseDAO courseDAO = new CourseDAO(entityManager);

        Course course1 = TestUtils.createCourse("CMPE-285", "Virtualization Tech");
        Course course2 = TestUtils.createCourse("CMPE-284", "Virtualization Tech2");

        entityManager.getTransaction().begin();
        entityManager.persist(course1);
        entityManager.persist(course2);
        entityManager.getTransaction().commit();


        List<Course> courses = courseDAO.findAll();

        assertEquals("Number of courses do not match", 2, courses.size());
        assertTrue(Iterables.elementsEqual(courses, Lists.newArrayList(course2, course1)));
    }


    // Checking the Isolation Level (READ COMMITTED)
    @Test
    public void check_isolation_level() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("insert into roles(role_id, name) values (3833, 'student')").executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("insert into roles(role_id, name) values (7777, 'professor')").executeUpdate();

    }
}
