package org.courseregistration.rest;

import org.courseregistration.dao.UserDAO;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.hateoas.ProfessorResourceWrapper;
import org.courseregistration.hateoas.StudentResourceWrapper;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Role;
import org.courseregistration.model.Student;
import org.courseregistration.model.User;
import org.courseregistration.rest.login.LoginRequest;
import org.courseregistration.rest.login.LoginResponse;
import org.courseregistration.rest.writters.ProfessorAssembler;
import org.courseregistration.rest.writters.StudentAssembler;
import org.courseregistration.service.ProfessorService;
import org.courseregistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Set;

@Component
@Path("/")
@PermitAll
public class UserResource {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UserDAO userDAO;

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request) throws ApplicationException {
        User user = userDAO.findByUsername(request.getUsername());
        Long id = user.getId();

        LoginResponse response = new LoginResponse();
        response.setUsername(user.getUsername());
        Set<Role> roles = user.getRoles();
        for(Role role :roles){
            if("ROLE_STUDENT".equals(role.getName())){
                response.setIsStudent(true);
                response.setStudent(getStudent(id));
            }else if("ROLE_PROFESSOR".equals(role.getName())){
                response.setIsProfessor(true);
                response.setProfessor(getProfessor(id));
            }
        }
        return Response.ok(response).build();
    }

    private ProfessorResourceWrapper getProfessor(Long id) throws ApplicationException {
        Professor professor = professorService.findProfessorById(id);
        ProfessorAssembler professorAssembler = new ProfessorAssembler();
        return professorAssembler.toResource(professor);
    }

    private StudentResourceWrapper getStudent(Long id) throws ApplicationException {
        Student student = studentService.findById(id);
        StudentAssembler studentAssembler = new StudentAssembler();
        return studentAssembler.toResource(student);
    }

}
