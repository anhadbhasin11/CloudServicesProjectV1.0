package org.courseregistration.rest.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.courseregistration.hateoas.ProfessorResourceWrapper;
import org.courseregistration.hateoas.StudentResourceWrapper;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Student;

public class LoginResponse {
    private String username;
    @JsonProperty("isStudent")
    private boolean isStudent;
    @JsonProperty("isProfessor")
    private boolean isProfessor;
    @JsonProperty("isAdmin")
    private boolean isAdmin;
    private StudentResourceWrapper student;
    private ProfessorResourceWrapper professor;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setIsProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public StudentResourceWrapper getStudent() {
        return student;
    }

    public void setStudent(StudentResourceWrapper student) {
        this.student = student;
    }

    public ProfessorResourceWrapper getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorResourceWrapper professor) {
        this.professor = professor;
    }
}
