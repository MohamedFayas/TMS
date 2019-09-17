package com.virtusa.tmsless.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.tmsless.dao.UsersDaoIface;
import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;
import com.virtusa.tmsless.model.Users;

@Service
public class UsersServiceImpl implements UsersServiceIface{
	@Autowired
	UsersDaoIface daoIface;

	public List<String> validateUsersService(Users user) {
		return daoIface.validateUser(user);
	}

	public Users aboutUsers(int userId) {
		return daoIface.aboutUsers(userId);
	}

	public int addUser(Users user) {
		return daoIface.addUser(user);
	}

	public int generateUserIdService() {
		return daoIface.generateUserId();
	}

	public List<Users> viewUsersService() {
		return daoIface.viewUsers();
	}

	public List<Courses> viewCoursesService() {
		return daoIface.viewCourses();
	}

	public int scheduleTraingService(Courses courses) {
		return daoIface.scheduleTraing(courses);
	}

	public Courses viewScheduleService(String courseId) {
		return daoIface.viewSchedule(courseId);
	}

	public List<CourseRegistration> viewNomination(String courseId) {
		return daoIface.viewNominations(courseId);
	}

}
