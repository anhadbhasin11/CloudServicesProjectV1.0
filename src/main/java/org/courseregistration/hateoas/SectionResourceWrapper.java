package org.courseregistration.hateoas;

import org.courseregistration.model.Section;
import org.courseregistration.rest.view.section.CourseView;
import org.courseregistration.rest.view.section.ProfessorView;
import org.courseregistration.rest.view.section.SectionView;
import org.courseregistration.rest.view.section.StudentView;
import org.courseregistration.rest.writters.CourseAssembler;
import org.courseregistration.rest.writters.ProfessorAssembler;
import org.courseregistration.rest.writters.StudentAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class SectionResourceWrapper extends ResourceSupport {
    private SectionView sectionView;


    public SectionView getSection() {
        return sectionView;
    }

    public void setSection(Section section) {
        CourseView course = new CourseView();
        CourseAssembler courseAssembler = new CourseAssembler();
        course.setId(section.getCourse().getId());
        course.setName(section.getCourse().getName());
        course.setLink(courseAssembler.toResource(section.getCourse()).getLink(Link.REL_SELF));

        ProfessorView professor = new ProfessorView();
        ProfessorAssembler professorAssembler = new ProfessorAssembler();
        professor.setId(section.getProfessor().getId());
        professor.setFirstName(section.getProfessor().getFirstName());
        professor.setLastName(section.getProfessor().getLastName());
        professor.setEmailId(section.getProfessor().getEmailId());
        professor.setLink(professorAssembler.toResource(section.getProfessor()).getLink(Link.REL_SELF));

        List<StudentView> studentView = newArrayList();

        for(org.courseregistration.model.Student student:section.getStudents()){
            StudentView tempStudent = new StudentView();
            StudentAssembler studentAssembler = new StudentAssembler();
            tempStudent.setId(student.getId());
            tempStudent.setEmailId(student.getEmailId());
            tempStudent.setFirstName(student.getFirstName());
            tempStudent.setLastName(student.getLastName());
            tempStudent.setLink(studentAssembler.toResource(student).getLink(Link.REL_SELF));
            studentView.add(tempStudent);
        }
        SectionView sectionView = new SectionView();
        sectionView.setId(section.getId());
        sectionView.setCourse(course);
        sectionView.setProfessor(professor);
        sectionView.setStudents(studentView);
        sectionView.setSemester(section.getSemester());
        sectionView.setClassStartTime(section.getClassStartTime());
        sectionView.setClassEndTime(section.getClassEndTime());
        sectionView.setDayOfWeek(section.getDayOfWeek());
        sectionView.setStartDate(section.getStartDate());
        sectionView.setEndDate(section.getEndDate());
        sectionView.setRoomNumber(section.getRoomNumber());
        sectionView.setTotalCapacity(section.getTotalCapacity());
        sectionView.setWaitListCapacity(section.getWaitListCapacity());
        sectionView.setModeOfInstruction(section.getModeOfInstruction());
        sectionView.setPrice(section.getPrice());
        sectionView.setNumberOfEnrolledStudents(section.getStudents().size());
        this.sectionView = sectionView;
    }
}
