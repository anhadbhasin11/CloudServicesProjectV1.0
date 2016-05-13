package org.courseregistration.dao;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class StudentDAOTest extends BaseTest {
    StudentDAO studentDAO = new StudentDAO(entityManager);

    @Test
    public void findAll_students() throws Exception {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Student").executeUpdate();
        entityManager.getTransaction().commit();

        Student student1 = TestUtils.createStudent(89234L, "Test Stuent1");
        Student student2 = TestUtils.createStudent(89244L, "Test Stuent2");

        entityManager.getTransaction().begin();
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.getTransaction().commit();


        List<Student> students = studentDAO.findAll();

        assertEquals("Number of students do not match", 2, students.size());
        assertTrue(Iterables.elementsEqual(students, Lists.newArrayList(student1, student2)));
    }

    @Test
    public void find_student_by_id() throws Exception {

        Student student = TestUtils.createStudent(89234L, "Test Stuent");

        entityManager.persist(student);

        Student actual = studentDAO.findById(student.getId());

        assertEquals(student.getId(), (actual.getId()));
        assertEquals(student.getFirstName(), (actual.getFirstName()));
        assertEquals(student.getLastName(), (actual.getLastName()));
        assertEquals(student.getMiddleName(), (actual.getMiddleName()));
    }


    @Test
    public void update_student_by_dropping_a_course() throws Exception {

        Student student = TestUtils.createStudent(185354L, "Daniel");
        Professor professor = TestUtils.createProfessor(890897L, "Thomas");
        Course course = TestUtils.createCourse("CMPE-281", "Cloud Technologies");

        Section section = TestUtils.createSection(professor, course);

        student.addSection(section);
        entityManager.persist(student);

        assertNotNull(student.getId());
        assertNotNull(section.getId());
        assertEquals(1, student.getSections().size());
        assertEquals(section, student.getSections().get(0));

        student.dropSection(section);

        studentDAO.update(student);

        Student updatedStudent = entityManager.find(Student.class, student.getId());

        assertEquals(0, updatedStudent.getSections().size());

    }

    @Test
    public void update_student_by_adding_a_course() throws Exception {

        StudentDAO studentDAO = new StudentDAO(entityManager);

        Student student = TestUtils.createStudent(187354L, "Daniel");
        Professor professor = TestUtils.createProfessor(8904897L, "Thomas");
        Course course = TestUtils.createCourse("CMPE-281", "Cloud Technologies");
        Section section = TestUtils.createSection(professor, course);

        Professor professorForSection2 = TestUtils.createProfessor(897554L, "Prof.ABC");
        Section section2 = TestUtils.createSection(professorForSection2, course);

        student.addSection(section);
        entityManager.persist(student);
        entityManager.persist(section2);

        assertNotNull(student.getId());
        assertNotNull(section.getId());
        assertEquals(1, student.getSections().size());
        assertEquals(section, student.getSections().get(0));

        student.addSection(section2);

        studentDAO.update(student);

        Student updatedStudent = entityManager.find(Student.class, student.getId());

        assertEquals(2, updatedStudent.getSections().size());
        assertTrue(Iterables.elementsEqual(newArrayList(section, section2), updatedStudent.getSections()));
    }

    @Test
    public void delete_student_by_id() {
        StudentDAO studentDAO = new StudentDAO(entityManager);

        Student student = TestUtils.createStudent(12345656, "Shrutee");
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();

        studentDAO.delete(student.getId());

        Student updatedStudent = entityManager.find(Student.class, student.getId());

        assertNull(updatedStudent);
    }

    @Test
    public void delete_student() {
        StudentDAO studentDAO = new StudentDAO(entityManager);

        Student student = TestUtils.createStudent(3434L, "Shrutee");
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();

        studentDAO.delete(student);

        Student updatedStudent = entityManager.find(Student.class, student.getId());

        assertNull(updatedStudent);
    }

    //student has a section associated.Deleting a student also deletes a row from "enrolled_students"
    // cascading delete
    @Test
    public void test_referential_integrity() {
        Student student = TestUtils.createStudent(454L, "student");

        Section section = TestUtils.createSection(656L, "Prof.John", "CMPE-865");

        student.addSection(section);

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();

        studentDAO.delete(student);

        Student updatedStudent = entityManager.find(Student.class, student.getId());
        List resultList = entityManager.createNativeQuery("select * from enrolled_student s where s.student_id=" + student.getId()).getResultList();

        assertNull(updatedStudent);
        assertEquals(0, resultList.size());
    }

    // A user should not be allowed to insert  value of foreign key in enrolled_student table
    @Test
    public void test_referential_integrity2() {

        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("insert into enrolled_student (student_id, section_id) VALUES (444,555)").executeUpdate();
        entityManager.getTransaction().commit();

    }


    // Checking the Isolation Level (READ COMMITTED)
    @Test
    public void test_isolation_level() {

      /*  entityManager.createNativeQuery("insert into student_details(admissionType, previous_degree, user_id) VALUES ('GGG','HHH', 555)").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.createNativeQuery("select * from student_details").executeUpdate();

        entityManager.createNativeQuery("insert into student_details(admissionType, previous_degree, user_id) VALUES ('JJJ','GGG', 888)").executeUpdate();
        entityManager.createNativeQuery("select * from student_details").executeUpdate();*/

        Student student = TestUtils.createStudent(6595L, "test");

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();

        Student studentFromDB = entityManager.find(Student.class, student.getId());

        System.out.println(studentFromDB.toString() + studentFromDB.getFirstName());

        studentFromDB.setFirstName("test2");

        entityManager.getTransaction().begin();
        entityManager.merge(studentFromDB);
        Student studentFromDB2 = entityManager.find(Student.class, student.getId());
        System.out.println(studentFromDB2.toString() + studentFromDB2.getFirstName());
        entityManager.getTransaction().commit();

        Student studentFromDB3 = entityManager.find(Student.class, student.getId());
        System.out.println(studentFromDB2.toString() + studentFromDB3.getFirstName());
        assertEquals(studentFromDB, student);

    }
}
