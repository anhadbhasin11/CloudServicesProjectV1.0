package org.courseregistration.rest.view.student;

import java.util.Date;

import org.courseregistration.rest.view.section.CourseView;
import org.courseregistration.rest.view.section.ProfessorView;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SectionView {
	private Long id;
	private String semester;
	private Date classStartTime;
	private Date classEndTime;
	private String dayOfWeek;
	private Date startDate;
	private Date endDate;
	@JsonProperty("course")
	private CourseView courseView;
	@JsonProperty("professor")
	private ProfessorView professorView;
    private Integer price;
    private Integer totalCapacity;
    private Integer waitListCapacity;
    private String roomNumber;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Date getClassStartTime() {
		return classStartTime;
	}

	public void setClassStartTime(Date classStartTime) {
		this.classStartTime = classStartTime;
	}

	public Date getClassEndTime() {
		return classEndTime;
	}

	public void setClassEndTime(Date classEndTime) {
		this.classEndTime = classEndTime;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CourseView getCourseView() {
		return courseView;
	}

	public void setCourseView(CourseView courseView) {
		this.courseView = courseView;
	}

	public ProfessorView getProfessorView() {
		return professorView;
	}

	public void setProfessorView(ProfessorView professorView) {
		this.professorView = professorView;
	}

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setWaitListCapacity(Integer waitListCapacity) {
        this.waitListCapacity = waitListCapacity;
    }

    public Integer getWaitListCapacity() {
        return waitListCapacity;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
