package org.courseregistration.dao;

import org.courseregistration.model.*;

import java.util.Calendar;

public class TestUtils {

    public static Section createSection(Professor professor, Course course) {
        Section section = new Section();

        Calendar classEndDateTime = Calendar.getInstance();
        classEndDateTime.set(2015, Calendar.DECEMBER, 12, 18, 0);

        section.setClassEndTime(classEndDateTime.getTime());

        Calendar classStartDateTime = Calendar.getInstance();
        classStartDateTime.set(2015, Calendar.AUGUST, 24, 20, 45);

        section.setClassStartTime(classStartDateTime.getTime());

        section.setDayOfWeek("Thursday");
        section.setEndDate(classEndDateTime.getTime());
        section.setStartDate(classStartDateTime.getTime());

        section.setModeOfInstruction("In Class");
        section.setRoomNumber("EM-501");
        section.setSemester("Fall-2015");
        section.setTotalCapacity(40);
        section.setWaitListCapacity(20);
        section.setProfessor(professor);
        section.setCourse(course);
        return section;
    }

    public static Address createAddress() {
        Address address = new Address();
        address.setState("CA");
        address.setAptNo(23);
        address.setCity("San Jose");
        address.setStreetName("Street name");
        address.setZipcode(49982);

        return address;
    }

    public static Student createStudent(long collegeId, String name) {
        Student student1 = new Student();
        student1.setUsername(collegeId);
        student1.setFirstName(name);
        student1.setMiddleName("R.");
        student1.setLastName("Edward");
        student1.setAddress(createAddress());
        student1.setAdmissionType("Accepted");

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(1988, Calendar.FEBRUARY, 5);
        student1.setDateOfBirth(calendar1.getTime());

        student1.setEmailId("John@gmail.com");
        student1.setPhoneNumber("+1 408 386 7260");
        student1.setPreviousDegree("Bsc Comp Science");
        return student1;
    }

    public static Professor createProfessor(long collegeId, String name) {
        Professor professor = new Professor();

        professor.setFirstName(name);
        professor.setMiddleName("W.");
        professor.setUsername(collegeId);
        professor.setLastName("Campbell");
        professor.setAddress(createAddress());
        professor.setFacultyType("Permanant");
        professor.setYearsOfExperience(12);

        Calendar prof_calendar1 = Calendar.getInstance();
        prof_calendar1.set(1986, Calendar.AUGUST, 7);
        professor.setDateOfBirth(prof_calendar1.getTime());

        professor.setEmailId("alice@gmail.com");
        professor.setPhoneNumber("+1 408 376 7860");

        Calendar prof1_calendar1 = Calendar.getInstance();
        prof1_calendar1.set(0, Calendar.JULY, 0, 10, 0);
        professor.setOfficeHoursFromTime(prof1_calendar1.getTime());

        Calendar prof1_calendar2 = Calendar.getInstance();
        prof1_calendar2.set(0, Calendar.MAY, 0, 17, 0);
        professor.setOfficeHoursToTime(prof1_calendar2.getTime());

        return professor;
    }

    public static Course createCourse(String courseCode, String courseName) {
        Course course = new Course();

        course.setCode(courseCode);
        course.setName(courseName);
        course.setDescription("");
        course.setDepartment("");
        course.setNumOfCredits(9);
        course.setPrerequisiteCourse("Operating System");
        course.setProgram("Software Engineering");
        return course;
    }

    public static Section createSection(long collegeId, String professorName, String courseCode) {
        Professor professor = createProfessor(collegeId, professorName);
        Course course = createCourse(courseCode, "Course Name");

        return createSection(professor, course);
    }
}
