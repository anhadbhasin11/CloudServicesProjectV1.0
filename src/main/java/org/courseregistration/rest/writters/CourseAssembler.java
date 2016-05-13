package org.courseregistration.rest.writters;

import org.courseregistration.rest.CourseResource;
import org.courseregistration.hateoas.CourseResourceWrapper;
import org.courseregistration.model.Course;

public class CourseAssembler extends ResourceAssembler<Course, CourseResourceWrapper> {

    public CourseAssembler() {
        super(CourseResource.class, CourseResourceWrapper.class);
    }
    @Override
    public CourseResourceWrapper toResource(Course entity) {
        CourseResourceWrapper resource = createResourceWithId(entity.getId(),entity);
        resource.setCourse(entity);
        return resource;
    }
}
