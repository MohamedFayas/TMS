package com.virtusa.tmsless.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.tmsless.dao.EmployeeDaoIface;
import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;
@Service
public class EmployeeServiceImpl implements EmployeeServiceIface{
	
	@Autowired
	EmployeeDaoIface daoIface;
	public String postCourse(Courses course) {
		return daoIface.postCourses(course);
	}
	public String generateCourseIdService() {
		return daoIface.generateCourseId();
	}
	public List<Courses> listCoursesService(int userId) {
		return daoIface.listCourses(userId);
	}
	public List<Courses> listCoursesPostedService(int userId) {
		return daoIface.listCoursesPosted(userId);
	}
	public Map<Courses, CourseRegistration> viewHistoryService(int userId) {
		return daoIface.viewHistory(userId);
	}
	public List<CourseRegistration> viewEmploy(int userId) {
		return daoIface.viewEmpolyee(userId);
	}
	public int upadteStatusService(String status, String registrationId) {
		return daoIface.updateStatus(status, registrationId);
	}
	public int updateFeedbackService(String courseId, double rating) {
		return daoIface.updateFeedback(courseId, rating);
	}
	public String registerCourseService(CourseRegistration courseRegistration) {
		return daoIface.registerCourse(courseRegistration);
	}
	public String generateRegisterIdService() {
		return daoIface.generateRegistrationId();
	}
	public String registerCourse(CourseRegistration courseRegistration) {
		return daoIface.registerCourse(courseRegistration);
	}

}
