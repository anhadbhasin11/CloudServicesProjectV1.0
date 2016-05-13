package org.courseregistration.dao;

import com.google.common.collect.Maps;
import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class SectionDAOTest extends BaseTest {
    private SectionDAO sectionDAO = new SectionDAO(entityManager);


    @Test
    public void testFindCourseByCriteria() throws Exception {
        //Data For 2 Sections
        Professor professor = TestUtils.createProfessor(8904897L, "Thomas");
        Course course1 = TestUtils.createCourse("CMPE-281", "Cloud Technologies");
        Section section1 = TestUtils.createSection(professor, course1);

        Professor professorForSection2 = TestUtils.createProfessor(897554L, "Prof.ABC");
        Course course2 = TestUtils.createCourse("CMPE-283", "Cloud Technologies");
        Section section2 = TestUtils.createSection(professorForSection2, course2);

        entityManager.getTransaction().begin();
        entityManager.persist(section1);
        entityManager.persist(section2);
        entityManager.getTransaction().commit();


        //Search Section based on criteria
        Map<SearchCriteria, String> criteria = Maps.newHashMap();
        criteria.put(SearchCriteria.COURSE_CODE_CONTAINS, "28");
        criteria.put(SearchCriteria.PROFESSOR_FIRST_NAME_CONTAINS, "Thomas");

        List<Section> foundSections = sectionDAO.findCourseByCriteria(criteria);

        assertEquals(1, foundSections.size());
        assertEquals(section1.getCourse().getCode(), foundSections.get(0).getCourse().getCode());
        assertEquals(section1.getCourse().getName(), foundSections.get(0).getCourse().getName());
        assertEquals(section1.getProfessor().getFirstName(), foundSections.get(0).getProfessor().getFirstName());

    }

    @Test
    public void test_find_all() {

        Section section1 = TestUtils.createSection(232423L, "Prof.abc", "CMPE-343");
        Section section2 = TestUtils.createSection(3453445L, "Prof.xyz", "CMPE-183");
        Section section3 = TestUtils.createSection(45465546L, "Prof.pqr", "CMPE-789");

        entityManager.getTransaction().begin();
        entityManager.persist(section1);
        entityManager.persist(section2);
        entityManager.persist(section3);
        entityManager.getTransaction().commit();

        List<Section> sections = sectionDAO.findAll();


        assertEquals(3, sections.size());
    }

    //removing a section does not affect Professor/course
    @Test
    public void test_referential_integrity_deleting_a_section() {
        Professor professor = TestUtils.createProfessor(13298L, "Prof.Tom");
        Course course = TestUtils.createCourse("CMPE-789", "Databases");

        Section section = TestUtils.createSection(professor, course);

        entityManager.getTransaction().begin();
        entityManager.persist(section);
        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();

        sectionDAO.delete(section.getId());

        Professor professorFromDB = entityManager.find(Professor.class, professor.getId());
        Course courseFromDB = entityManager.find(Course.class, course.getId());
        Section sectionFromDB = entityManager.find(Section.class, section.getId());

        assertEquals(professorFromDB, professor);
        assertEquals(courseFromDB, course);
        assertNull(sectionFromDB);
    }

    //removing a section does not affect Student associated
    @Test
    public void test_referential_integrity_deleting_a_section_should_not_affect_student() {
        Professor professor = TestUtils.createProfessor(13298L, "Prof.Tom");
        Course course = TestUtils.createCourse("CMPE-789", "Databases");
        Student student = TestUtils.createStudent(4342L, "student");

        Section section = TestUtils.createSection(professor, course);

        entityManager.getTransaction().begin();
        entityManager.persist(section);
        student.addSection(section);
        entityManager.persist(student);
        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();

        Student fromDb = entityManager.find(Student.class, student.getId());
        assertEquals(1, fromDb.getSections().size());
        assertEquals(fromDb, student);

        sectionDAO.delete(section.getId());

        Section sectionFromDB = entityManager.find(Section.class, section.getId());

        List enrolled_student = entityManager.createNativeQuery("select * from enrolled_student s where s.student_id=" + student.getId()).getResultList();
        Object[] arr = (Object[]) enrolled_student.get(0);

        Student studentFromDB = entityManager.find(Student.class, student.getId());

        assertNull(sectionFromDB);
        assertEquals(1, enrolled_student.size());
        assertEquals(2, arr.length);
        assertNotNull(studentFromDB);
        assertEquals(student, studentFromDB);

    }

    @Test(expected = Exception.class)
    public void testDelete() {
        Professor professor = TestUtils.createProfessor(13298L, "Prof.Tom");
        Course course = TestUtils.createCourse("CMPE-789", "Databases");
        Student student = TestUtils.createStudent(4342L, "student");

        Section section = TestUtils.createSection(professor, course);

        entityManager.getTransaction().begin();
        entityManager.persist(section);
        student.addSection(section);
        entityManager.persist(student);
        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();

        sectionDAO.delete(section.getId());

        Section sectionFromDB = entityManager.find(Section.class, section.getId());

        assertNull(sectionFromDB);
    }

}
