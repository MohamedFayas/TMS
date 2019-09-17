package com.virtusa.tmsless.service;


import java.util.List;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;
import com.virtusa.tmsless.model.Users;


public interface UsersServiceIface {
	List<String> validateUsersService(Users user);
	Users aboutUsers(int UserId);
	int addUser(Users user);
	int generateUserIdService();
	List<Users> viewUsersService();
	List<Courses> viewCoursesService();
	int scheduleTraingService(Courses courses);
	Courses viewScheduleService(String courseId);
	List<CourseRegistration>viewNomination(String courseId);
}
