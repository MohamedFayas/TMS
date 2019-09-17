package com.virtusa.tmsless.model;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="Courses")
public class Courses {
	public String getTutorName() {
		return tutorName;
	}
	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
	@Id
	@Column(name="courseId")
	private String courseId;
	@Column(name="CourseName")
	private String courseName;
	@Column(name="userId")
	private int userId;
	@Column(name = "startDate")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Column(name = "endDate")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@Column(name="venue")
	private String venue;
	@Column(name="tutorName")
	private String tutorName;
	@Column(name="feedback")
	private double feedback;
	@Column(name="countPeople")
	private double countPeople;
	public double getFeedback() {
		return feedback;
	}
	public void setFeedback(double feedback) {
		this.feedback = feedback;
	}
	public double getCountPeople() {
		return countPeople;
	}
	public void setCountPeople(double countPeople) {
		this.countPeople = countPeople;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "Courses [courseId=" + courseId + ", courseName=" + courseName + ", userId=" + userId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", venue=" + venue + ", tutorName=" + tutorName + "]";
	}
	
}
