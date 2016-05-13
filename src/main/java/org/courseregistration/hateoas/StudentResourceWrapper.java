package org.courseregistration.hateoas;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.courseregistration.rest.view.section.CourseView;
import org.courseregistration.rest.view.section.ProfessorView;
import org.courseregistration.rest.view.student.SectionView;
import org.courseregistration.rest.view.student.StudentView;
import org.courseregistration.rest.writters.CourseAssembler;
import org.courseregistration.rest.writters.ProfessorAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class StudentResourceWrapper extends ResourceSupport {
    @JsonProperty("student")
    private StudentView studentView;

    public StudentView getStudentView() {
        return studentView;
    }


    public void setStudent(Student student) {

        List<SectionView> sectionViewList = Lists.newArrayList();

        for(Section section : student.getSections()){
            Course course = section.getCourse();
            CourseAssembler courseAssembler = new CourseAssembler();
            CourseResourceWrapper courseResourceWrapper = courseAssembler.toResource(course);

            CourseView courseView = new CourseView();
            courseView.setId(course.getId());
            courseView.setName(course.getName());
            courseView.setLink(courseResourceWrapper.getLink(Link.REL_SELF));

            Professor professor = section.getProfessor();
            ProfessorAssembler professorAssembler = new ProfessorAssembler();
            ProfessorResourceWrapper professorResourceWrapper = professorAssembler.toResource(professor);

            ProfessorView professorView = new ProfessorView();
            professorView.setId(professor.getId());
            professorView.setFirstName(professor.getFirstName());
            professorView.setLastName(professor.getLastName());
            professorView.setEmailId(professor.getEmailId());
            professorView.setPhoneNumber(professor.getPhoneNumber());
            professorView.setLink(professorResourceWrapper.getLink(Link.REL_SELF));

            SectionView sectionView = new SectionView();
            sectionView.setId(section.getId());
            sectionView.setClassEndTime(section.getClassEndTime());
            sectionView.setClassStartTime(section.getClassStartTime());
            sectionView.setDayOfWeek(section.getDayOfWeek());
            sectionView.setPrice(section.getPrice());
            sectionView.setTotalCapacity(section.getTotalCapacity());
            sectionView.setWaitListCapacity(section.getWaitListCapacity());
            sectionView.setStartDate(section.getStartDate());
            sectionView.setEndDate(section.getEndDate());
            sectionView.setSemester(section.getSemester());
            sectionView.setRoomNumber(section.getRoomNumber());
            sectionView.setCourseView(courseView);
            sectionView.setProfessorView(professorView);

            sectionViewList.add(sectionView);
        }
        StudentView studentView = new StudentView();
        studentView.setId(student.getId());
        studentView.setEmailId(student.getEmailId());
        studentView.setAddress(student.getAddress());
        studentView.setAdmissionType(student.getAdmissionType());
        studentView.setDateOfBirth(student.getDateOfBirth());
        studentView.setFirstName(student.getFirstName());
        studentView.setLastName(student.getLastName());
        studentView.setMiddleName(student.getMiddleName());
        studentView.setPhoneNumber(student.getPhoneNumber());
        studentView.setPreviousDegree(student.getPreviousDegree());
        studentView.setUsername(student.getUsername());
        studentView.setSectionViews(sectionViewList);

        this.studentView = studentView;
    }


}
