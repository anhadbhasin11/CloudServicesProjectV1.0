package org.courseregistration.rest.writters;

import org.courseregistration.rest.StudentResource;
import org.courseregistration.hateoas.StudentResourceWrapper;
import org.courseregistration.model.Student;

public class StudentAssembler extends ResourceAssembler<Student, StudentResourceWrapper> {

    public StudentAssembler() {
        super(StudentResource.class, StudentResourceWrapper.class);
    }
    @Override
    public StudentResourceWrapper toResource(Student entity) {

        StudentResourceWrapper resource = createResourceWithId(entity.getId(),entity);
        resource.setStudent(entity);
        return resource;
    }
}
