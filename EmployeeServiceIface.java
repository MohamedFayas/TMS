package com.virtusa.tmsless.service;

import java.util.List;
import java.util.Map;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;

public interface EmployeeServiceIface {
	String postCourse(Courses course);
	String generateCourseIdService();
	List<Courses>listCoursesService(int userId);
	List<Courses>listCoursesPostedService(int userId);
	Map<Courses,CourseRegistration> viewHistoryService(int userId);
	List<CourseRegistration>viewEmploy(int userId);
	int upadteStatusService(String status,String registrationId );
	int updateFeedbackService(String courseId,double rating);
	String registerCourseService(CourseRegistration courseRegistration);
	String generateRegisterIdService();
	String registerCourse(CourseRegistration courseRegistration);

}
