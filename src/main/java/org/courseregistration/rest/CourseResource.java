package org.courseregistration.rest;

import com.google.common.collect.Lists;
import org.courseregistration.rest.writters.CourseAssembler;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.hateoas.CourseResourceWrapper;
import org.courseregistration.model.Course;
import org.courseregistration.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Date;
import java.util.List;

import static org.courseregistration.rest.ResponseHelper.getCacheControl;

@Component
@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
@ExposesResourceFor(Course.class)
public class CourseResource {
    @Autowired
	private CourseService courseService;
    @Autowired
    private EntityLinks entityLinks;


	/**
	 * Get details of a specific course
	 * @param id
	 * course identifier of the required course
	 * @response.representation.200.doc Details of course
	 * @response.representation.200.mediaType application/json
	 *
	 * @response.representation.404.doc Requested course with id not found

	 * @return details of a course
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCourseById(@PathParam("id") Long id,
                                   @Context Request request) throws ApplicationException {

        Course course = courseService.findById(id);

        Resource<Course>  resource = new Resource<>(course);
        Link selfRel = entityLinks.linkToSingleResource(Course.class, course.getId()).withSelfRel();
        resource.add(selfRel);

        CacheControl cc = getCacheControl();

        EntityTag tag = new EntityTag(Integer.toString(course.hashCode()));
        Date lastUpdated = course.getUpdatedAt();

        Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(lastUpdated, tag);

        if (responseBuilder != null) {
            responseBuilder.cacheControl(cc);
            return responseBuilder.build();
        }
        responseBuilder = Response.ok(resource);
        responseBuilder.cacheControl(cc);
        responseBuilder.tag(tag);
        return responseBuilder.build();
	}


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCoursesPaged(
        @QueryParam("page") @DefaultValue("1") int page,
        @QueryParam("size") @DefaultValue("2") int size,@Context UriInfo uriInfo) {

        if(page<1 || size<1){
            return Response.status(400).build();
        }

        List<Course> allCourses = courseService.findAllCourses();
        CourseAssembler courseAssembler = new CourseAssembler();
        List<CourseResourceWrapper> resources = courseAssembler.toResources(allCourses);

        List<CourseResourceWrapper> toShow = Lists.newArrayList();
        for(int i= (page-1)*size, j=0;j<size && i<resources.size(); i++,j++){
            toShow.add(resources.get(i));
        }

        int totalNumberOfPages = resources.size() / size;
        totalNumberOfPages = resources.size()%size != 0?totalNumberOfPages+1:totalNumberOfPages;

        List<Link> links = PaginationHelper.getPaginationLinks(page, size, uriInfo, totalNumberOfPages);

        PagedResources<CourseResourceWrapper> courseResources = new PagedResources<>(
            toShow,
            new PagedResources.PageMetadata(
                size,
                page,
                resources.size(),
                (long) totalNumberOfPages),links
        );

        return Response.ok(courseResources).build();
    }



    @DELETE
	@Path("{id}")
    @RolesAllowed({"ADMIN","PROFESSOR"})
	@Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourseById(@PathParam("id") Long id) {
		courseService.deleteById(id);
        return Response.noContent()
            .entity("Course successfully deleted from the system.").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN","PROFESSOR"})
    @Produces(MediaType.TEXT_PLAIN)
	public Response createCourse(@Context UriInfo uriInfo,Course course) {

		courseService.save(course);

		return Response.created(uriInfo.getAbsolutePathBuilder()
            .path(course.getId().toString()).build())
            .entity("Course successfully created")
            .build();
	}
}
