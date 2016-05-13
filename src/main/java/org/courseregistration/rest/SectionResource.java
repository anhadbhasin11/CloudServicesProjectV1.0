package org.courseregistration.rest;

import static org.courseregistration.rest.ResponseHelper.getCacheControl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.courseregistration.dao.SearchCriteria;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.hateoas.SectionResourceWrapper;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.courseregistration.rest.writters.SectionAssembler;
import org.courseregistration.service.SectionService;
import org.courseregistration.webconfig.CacheControlAnnotation.CacheMaxAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component
@Path("sections")
@PermitAll
@ExposesResourceFor(Section.class)
public class SectionResource {

	@Autowired
	private SectionService sectionService;
	@Autowired
	private EntityLinks entityLinks;

	/**
	 * Get details of a specific Section
	 *
	 * @param id
	 *            section identifier
	 * @return details of a Section
	 */
	@GET
	@Path("/{id}")
	@CacheMaxAge(time = 10, unit = TimeUnit.MINUTES, isPrivate = false, noStore = false)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSectionById(@PathParam("id") Long id,
			@Context Request request) throws ApplicationException {

		Section section = sectionService.findById(id);
		SectionAssembler sectionAssembler = new SectionAssembler();
		SectionResourceWrapper resources = sectionAssembler.toResource(section);

		int hashCode = section.hashCode();

		EntityTag tag = new EntityTag(Integer.toString(hashCode));
		Date lastUpdatedAt = section.getUpdatedAt();

		Response.ResponseBuilder responseBuilder = request
				.evaluatePreconditions(lastUpdatedAt, tag);

		if (responseBuilder != null) {
			responseBuilder.lastModified(section.getUpdatedAt());
			return responseBuilder.build();
		}
		responseBuilder = Response.ok(resources);
		responseBuilder.lastModified(section.getUpdatedAt());
		responseBuilder.tag(tag);
		return responseBuilder.build();
	}

	/**
	 * Find Section By Section's Course Name
	 *
	 * @param name
	 *            name of the course
	 * @return Response.Status.OK with Details of Sections which match the
	 *         course name
	 */
	@GET
	@Path("/coursename:{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSectionByName(@PathParam("name") String name) {
		List<Section> allSections = sectionService.findByName(name);
		return Response.ok(200).entity(allSections).build();
	}

	/**
	 * Find all Section
	 *
	 * @return Response.Status.OK with List of Sections
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSections(
			@QueryParam("page") @DefaultValue("1") int page,
			@QueryParam("size") @DefaultValue("2") int size,
			@Context UriInfo uriInfo) {
		if (page < 1 || size < 1) {
			return Response.status(400).build();
		}

		List<Section> allSections = sectionService.findAllSections();

		SectionAssembler sectionAssembler = new SectionAssembler();
		List<SectionResourceWrapper> resources = sectionAssembler
				.toResources(allSections);

		List<SectionResourceWrapper> toShow = Lists.newArrayList();
		for (int i = (page - 1) * size, j = 0; j < size && i < resources.size(); i++, j++) {
			toShow.add(resources.get(i));
		}

		int totalNumberOfPages = resources.size() / size;
		totalNumberOfPages = resources.size() % size != 0 ? totalNumberOfPages + 1
				: totalNumberOfPages;

		List<Link> links = PaginationHelper.getPaginationLinks(page, size,
				uriInfo, totalNumberOfPages);

		PagedResources<SectionResourceWrapper> sectionResourceWrappers = new PagedResources<>(
				toShow, new PagedResources.PageMetadata(size, page,
						resources.size(), (long) totalNumberOfPages), links);

		return Response.ok(sectionResourceWrappers)
				.cacheControl(getCacheControl()).build();
	}

	/**
	 * Creates a Section
	 *
	 * @param section
	 *            data of the new section to be created
	 * @return
	 * @throws ApplicationException
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response addSection(Section section, @Context UriInfo uriInfo)
			throws ApplicationException {

		boolean isSaved = sectionService.addSection(section);
		if (isSaved) {
			String result = "Section saved! Successfully";
			String id = section.getId() != null ? section.getId().toString()
					: "";
			return Response
					.created(uriInfo.getAbsolutePathBuilder().path(id).build())
					.entity(result).build();
		} else {
			String result = "Section is not saved" + section;
			return Response.status(400).entity(result).build();
		}
	}

	/**
	 * Deletes a section by ID
	 *
	 * @param section_id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response deleteSection(@PathParam("id") Long section_id)
			throws ApplicationException {
		sectionService.deleteSection(section_id);
		return Response.ok(201).entity(section_id).build();
	}

	/**
	 * Updates the price of course from section
	 *
	 * @param id
	 * @param price
	 * @return OK Response with message, or NOT_FOUND response with message
	 */
	@PUT
	@Path("/{section_id}/price/update/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response updateSectionPrice(@PathParam("section_id") Long id,
			@PathParam("price") int price) {
		boolean isUpdated = sectionService.updateSectionPrice(id, price);
		if (isUpdated)
			return Response.ok(Response.Status.OK)
					.entity("Section Price updated").build();
		else
			return Response.ok(Response.Status.NOT_FOUND)
					.entity("Section Price is not updated").build();
	}

	/**
	 * Updates the Section
	 *
	 * @param id
	 * @param current
	 * @return OK Response with message, or NOT_FOUND response with message
	 */
	@PUT
	@Path("{sectionId}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@RolesAllowed({ "PROFESSOR", "ADMIN" })
	public Response updateSection(@PathParam("sectionId") long id,
			Section current,@Context Request request) throws ApplicationException {
        Section fromDB = sectionService.findById(id);

        EntityTag tag = new EntityTag(Integer.toString(fromDB.hashCode()));
        Date timestamp = fromDB.getUpdatedAt();

        Response.ResponseBuilder builder =request.evaluatePreconditions(timestamp, tag);
        if (builder != null) {
            return builder.build();
        }

        boolean isUpdated = sectionService.updateSection(id, current,fromDB);
		if (isUpdated)
			return Response.ok(Response.Status.OK)
					.entity("Section Price updated").build();
		else
			return Response.ok(Response.Status.NOT_FOUND)
					.entity("Section Price is not updated").build();
	}

	/**
	 * Get section List by course name and professor name and price for course
	 *
	 * @param name
	 * @param price
	 * @param lastname
	 * @return Ok response with Section List
	 */
	@GET
	@Path("/coursename:{name}/price:{price}/proflastname:{lastname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSection(@PathParam("name") String name,
			@PathParam("price") int price,
			@PathParam("lastname") String lastname) {

		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, name);
		criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_CONTAINS, lastname);
		criteria.put(SearchCriteria.SECTION_PRICE_EQUALS, "" + price);

		List<Section> allSections = sectionService.findByCriteria(criteria);

		SectionAssembler sectionAssembler = new SectionAssembler();
		List<SectionResourceWrapper> resources = sectionAssembler
				.toResources(allSections);

		Resources<SectionResourceWrapper> wrapped = new Resources<>(resources);
		wrapped.add(JaxRsLinkBuilder.linkTo(SectionResource.class)
				.withSelfRel());

		return Response.ok(wrapped).build();
	}

	/**
	 * Get section List by course name and professor name
	 *
	 * @param name
	 * @param lastname
	 * @return Ok response with Section List
	 */
	@GET
	@Path("/coursename:{name}/proflastname:{lastname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSection(@PathParam("name") String name,
			@PathParam("lastname") String lastname) {

		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, name);
		criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_CONTAINS, lastname);

		List<Section> allSections = sectionService.findByCriteria(criteria);
		SectionAssembler sectionAssembler = new SectionAssembler();
		List<SectionResourceWrapper> resources = sectionAssembler
				.toResources(allSections);

		Resources<SectionResourceWrapper> wrapped = new Resources<>(resources);
		wrapped.add(JaxRsLinkBuilder.linkTo(SectionResource.class)
				.withSelfRel());

		return Response.ok(wrapped).build();
	}

	/**
	 * Using query parameters, search sections using parameters
	 *
	 * @param coursename
	 * @param lastname
	 * @param price
	 * @param dayofweek
	 * @param semester
	 * @param coursecode
	 * @return Response.ok(Response.Status.OK) with allSections
	 */
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSectionsQueryParams(
			@QueryParam("coursename") String coursename,
			@QueryParam("lastname") String lastname,
			@QueryParam("price") int price,
			@QueryParam("gteprice") int gteprice,
			@QueryParam("lteprice") int lteprice,
			@QueryParam("dayofweek") String dayofweek,
			@QueryParam("semester") String semester,
			@QueryParam("coursecode") String coursecode,
			@Context UriInfo uriInfo) throws ApplicationException {
		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		String priceStr = "" + price;
		String gtepriceStr = "" + gteprice;
		String ltepriceStr = "" + lteprice;

		Map<String, Object> queryParams = Maps.newHashMap();

		if (semester != null && !semester.isEmpty()) {
			criteria.put(SearchCriteria.SEMESTER_EQUALS, semester);
			queryParams.put("semester", semester);
		}
		if (dayofweek != null && !dayofweek.isEmpty()) {
			criteria.put(SearchCriteria.DAY_OF_WEEK_EQUALS, dayofweek);
			queryParams.put("dayofweek", dayofweek);
		}
		if (lastname != null && !lastname.isEmpty()) {
			criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_CONTAINS, lastname);
			queryParams.put("lastname", lastname);
		}
		if (coursename != null && !coursename.isEmpty()) {
			criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, coursename);
			queryParams.put("coursename", coursename);
		}
		if (coursecode != null && !coursecode.isEmpty()) {
			criteria.put(SearchCriteria.COURSE_CODE_EQUALS, coursecode);
			queryParams.put("coursecode", coursecode);
		}
		if (!priceStr.trim().isEmpty() && !priceStr.equalsIgnoreCase("null")
				&& !priceStr.equals("0")) {
			criteria.put(SearchCriteria.SECTION_PRICE_EQUALS, "" + price);
			queryParams.put("price", priceStr);
		}
		if (!gtepriceStr.trim().isEmpty()
				&& !gtepriceStr.equalsIgnoreCase("null")
				&& !gtepriceStr.equals("0")) {
			criteria.put(SearchCriteria.SECTION_PRICE_GREATER_EQUALS, ""
					+ gtepriceStr);
			queryParams.put("gteprice", gtepriceStr);
		}
		if (!ltepriceStr.trim().isEmpty()
				&& !ltepriceStr.equalsIgnoreCase("null")
				&& !ltepriceStr.equals("0")) {
			criteria.put(SearchCriteria.SECTION_PRICE_LESS_EQUALS, ""
					+ ltepriceStr);
			queryParams.put("lteprice", ltepriceStr);
		}
		if (criteria.size() > 0) {

			List<Section> allSections = sectionService.findByCriteria(criteria);

			SectionAssembler sectionAssembler = new SectionAssembler();
			List<SectionResourceWrapper> resources = sectionAssembler
					.toResources(allSections);

			Resources<SectionResourceWrapper> wrapped = new Resources<>(
					resources);

			UriBuilder linkBuilder = uriInfo.getAbsolutePathBuilder();
			for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
				linkBuilder.queryParam(entry.getKey(), entry.getValue());
			}

			Link link = new Link(linkBuilder.build().toString(), Link.REL_NEXT);
			wrapped.add(link);

			return Response.ok(wrapped).build();
		}

		throw ApplicationException
				.createNew()
				.withCode(Response.Status.BAD_REQUEST.getStatusCode())
				.withTitle("No Search parameters are defined")
				.withStatus(Response.Status.BAD_REQUEST.getStatusCode())
				.withDetail(
						"The Query Parameters are not provided. Please provide at least one param from following.\n coursename\n lastname\n price\n dayofweek\n semester\n coursecode")
				.build();

	}
}
