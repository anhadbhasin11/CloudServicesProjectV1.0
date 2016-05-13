package org.courseregistration.rest.writters;

import org.courseregistration.rest.ProfessorResource;
import org.courseregistration.hateoas.ProfessorResourceWrapper;
import org.courseregistration.model.Professor;

public class ProfessorAssembler extends ResourceAssembler<Professor, ProfessorResourceWrapper> {

        public ProfessorAssembler() {
            super(ProfessorResource.class, ProfessorResourceWrapper.class);
        }
        @Override
        public ProfessorResourceWrapper toResource(Professor entity) {
            ProfessorResourceWrapper resource = createResourceWithId(entity.getId(),entity);
            resource.setProfessor(entity);
            return resource;
        }
    }
