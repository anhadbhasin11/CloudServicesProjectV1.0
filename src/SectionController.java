package org.courseregistration.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.courseregistration.model.Section;
import org.courseregistration.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("sections")
public class SectionController {

	@Autowired
	private SectionService sectionService;

	/**
	 * Get details of a specific student
	 *
	 * @param id
	 *            student identifier of the required student
	 *
	 * @response.representation.200.doc Details of student
	 * @response.representation.200.mediaType application/json
	 *
	 * @response.representation.404.doc Requested student with id not found
	 *
	 * @return details of a student
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Section findStudentById(@PathParam("id") Long id) {
		return sectionService.findById(id);
	}

	@GET
	@Path("/name:{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findStudentById(@PathParam("name") String name) {
		List<Section> allStudents = sectionService.findByName(name);
		return Response.ok(200).entity(allStudents).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findStudents() {
		List<Section> allStudents = sectionService.findAllSections();
		return Response.ok(200).entity(allStudents).build();
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Section section) {

		String result = "Section saved : " + section;
		sectionService.save(section);
		return Response.status(201).entity(result).build();

	}

	// @PUT
	// @Path("/{param}")
	// public Response putMsg(@PathParam("param") Section section) {
	// String output = "PUT: Jersey say : " + section;
	//
	// sectionService.update(section);
	// return Response.status(200).entity(output).build();
	// }
	//
	// @DELETE
	// @Path("/{param}")
	// public Response deleteMsg(@PathParam("param") Section section) {
	// String output = "DELETE:Jersey say : " + section;
	// sectionService.delete(section);
	// return Response.status(200).entity(output).build();
	// }
	//
	// @HEAD
	// @Path("/{param}")
	// public Response headMsg(@PathParam("param") String msg) {
	// String output = "HEAD:Jersey say : " + msg;
	// return Response.status(200).entity(output).build();
	// }

}
