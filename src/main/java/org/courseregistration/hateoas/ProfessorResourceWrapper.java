package org.courseregistration.hateoas;

import org.courseregistration.model.Professor;
import org.springframework.hateoas.ResourceSupport;

public class ProfessorResourceWrapper extends ResourceSupport {

    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
