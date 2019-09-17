package com.virtusa.tmsless.dao;

import java.util.List;
import java.util.Map;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;

public interface EmployeeDaoIface {
	
	String GENERATE_COURSE_ID="select case when max(CourseId) is null then 'C000' else max(CourseId) end Cid from  Courses";
	String GENERATE_REGISTER_ID="select case when max(registerId) is null then 'R000' else max(registerId) end Rid from  CourseRegistration";
	String UPDATE_FEEDBACK="update courses set feedback=(feedback*countPeople+feedback=:rating)/(countPeople+1),countPeople=countPeople+1 where courseId=:courseId";
	String postCourses(Courses course);
	String generateCourseId();
	List<Courses> listCourses(int userId);
	List<Courses> listCoursesPosted(int userId);
	Map<Courses,CourseRegistration>viewHistory(int userId);
	List<CourseRegistration> viewEmpolyee(int userId);
	int updateStatus(String status,String registrationId);
	int updateFeedback(String courseId,double rating);
	String registerCourse(CourseRegistration courseRegistration);
	String generateRegistrationId();

}
