package org.courseregistration.hateoas;

import org.courseregistration.model.Course;
import org.springframework.hateoas.ResourceSupport;

public class CourseResourceWrapper extends ResourceSupport{

    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
