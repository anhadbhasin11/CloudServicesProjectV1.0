package org.courseregistration.service;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.courseregistration.dao.CourseDAO;
import org.courseregistration.dao.ProfessorDAO;
import org.courseregistration.dao.SearchCriteria;
import org.courseregistration.dao.SectionDAO;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
	@Autowired
	private SectionDAO sectionDAO;
    @Autowired
	private CourseDAO courseDAO;
    @Autowired
	private ProfessorDAO professorDAO;

	public List<Section> findAllSections() {
		List<Section> students = sectionDAO.findAll();
		List<Section> toReturn = newArrayList();
		for (Section student : students) {
			toReturn.add(student);
		}
		return toReturn;
	}

	public Section findById(Long id) throws ApplicationException {
		checkNotNull(id, "id should not ne null");

		Section toReturn = sectionDAO.findById(id);

		if (toReturn == null) {
			throw ApplicationException
					.createNew()
					.withCode(404)
					.withTitle("Resource not found")
					.withStatus(Response.Status.NOT_FOUND.getStatusCode())
					.withDetail(
							"The Section with id " + id + " is not available")
					.build();
		}

		return toReturn;
	}

	public List<Section> findByName(String name) {
		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, name);
		List<Section> toReturn = sectionDAO.findCourseByCriteria(criteria);
		return toReturn;
	}

	public List<Section> findByCriteria(Map<SearchCriteria, String> criteria) {
		List<Section> toReturn = sectionDAO.findCourseByCriteria(criteria);
		return toReturn;
	}

	public boolean addSection(Section section) throws ApplicationException {
		try {
            Course course = courseDAO.findById(section.getCourse().getId());
            Professor professor = professorDAO.findById(section.getProfessor().getId());
            section.setCourse(course);
            section.setProfessor(professor);
            sectionDAO.update(section);
			return true;
		} catch (Exception e) {
			throw ApplicationException
					.createNew()
					.withCode(Response.Status.PARTIAL_CONTENT.getStatusCode())
					.withTitle("Section creation failed.")
					.withStatus(Response.Status.PARTIAL_CONTENT.getStatusCode())
					.withDetail(
							"Can not create section object. Please check section object.")
					.build();
		}
	}

	public void deleteSection(long section_id) throws ApplicationException {
		try {
			checkNotNull(section_id, "ID should not be null");
			sectionDAO.delete(section_id);
		} catch (Exception e) {
			throw ApplicationException.createNew()
					.withCode(Response.Status.NOT_FOUND.getStatusCode())
					.withTitle("No Section entry found in database")
					.withStatus(Response.Status.BAD_REQUEST.getStatusCode())
					.withDetail("Can not delete. Please check section object.")
					.build();
		}
	}

	public void update(Section section) {
		Section toReturn = sectionDAO.findById(section.getId());
		toReturn = section;
	}

	public boolean updateSectionPrice(Long id, int price) {
		Section toReturn = sectionDAO.findById(id);
		if (toReturn != null) {
			toReturn.setPrice(price);
			sectionDAO.update(toReturn);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateSection(long id, Section current, Section sectionFromDB)
			throws ApplicationException {
		checkNotNull(id, "Id Should not be null");
		if (sectionFromDB != null) {
			sectionFromDB.setPrice(current.getPrice());
			sectionFromDB.setModeOfInstruction(current.getModeOfInstruction());
			sectionFromDB.setRoomNumber(current.getRoomNumber());
			sectionFromDB.setSemester(current.getSemester());
			sectionFromDB.setTotalCapacity(current.getTotalCapacity());
			sectionFromDB.setWaitListCapacity(current.getWaitListCapacity());
			sectionFromDB.setDayOfWeek(current.getDayOfWeek());
			sectionFromDB.setStartDate(current.getStartDate());
			sectionFromDB.setEndDate(current.getEndDate());
			sectionFromDB.setClassStartTime(current.getClassStartTime());
			sectionFromDB.setClassEndTime(current.getClassEndTime());
			sectionDAO.update(sectionFromDB);
			return true;
		} else {
			throw ApplicationException.createNew()
					.withCode(Response.Status.NOT_FOUND.getStatusCode())
					.withTitle("No Section entry found in database")
					.withStatus(Response.Status.BAD_REQUEST.getStatusCode())
					.withDetail("Can not update. Please check section object.")
					.build();
		}
	}

}
