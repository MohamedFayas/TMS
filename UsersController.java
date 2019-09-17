package com.virtusa.tmsless.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;
import com.virtusa.tmsless.model.Users;
import com.virtusa.tmsless.service.UsersServiceIface;


@Controller
public class UsersController {
	@Autowired
	UsersServiceIface usersServiceIface;
	@RequestMapping("/loginForm")
	public String userLogin(Model m) {
		m.addAttribute("user" ,new Users());
		return "Index2";
	}
	@RequestMapping("/login")
	public ModelAndView validateUser(@ModelAttribute("user") Users user,HttpServletRequest request ) {
		List<String> list=usersServiceIface.validateUsersService(user);
		if(list==null) {
			return new ModelAndView("Index2", "msg","inavalid Crendentials");
		}
		else {
				HttpSession session=request.getSession();
				session=request.getSession();
				session.setAttribute("userId",user.getUserId());
				session.setAttribute("type", list.get(0));
				session.setAttribute("name",list.get(1));
				return new ModelAndView(list.get(0), "name",list.get(1));
		}
	}
	@RequestMapping("/aboutUser")
	public ModelAndView aboutUser(HttpServletRequest request) {
		int userId=(Integer)request.getSession().getAttribute("userId");
		Users user=usersServiceIface.aboutUsers(userId);
		return new ModelAndView("About","user",user);
	}
	@RequestMapping("/userForm")
	public ModelAndView userForm(Model model) {
		model.addAttribute("user" ,new Users());
		int userId=usersServiceIface.generateUserIdService();
		return new ModelAndView("AddUsers","userId",userId);
	}
	@RequestMapping("/addUser")
	public ModelAndView addUser(@ModelAttribute("user") Users user) {
		if(usersServiceIface.addUser(user)>0)
		{
			return new ModelAndView("Message","message","Successfully Added");
		}
		return new ModelAndView("AddUsers","message","Error");
	}
	@RequestMapping("/type")
	public String redirectPage(HttpServletRequest request) {
		return (String)request.getSession().getAttribute("type");
	}
	@RequestMapping("/viewUsers")
	public ModelAndView viewUsers() {
		List<Users> listUsers=usersServiceIface.viewUsersService();
		return new ModelAndView("ViewUsers","users",listUsers);
	}
	@RequestMapping("/viewCourses")
	public ModelAndView viewCourses(HttpServletRequest request) {
		
		List<Courses> viewCourses=usersServiceIface.viewCoursesService();
		request.setAttribute("length", viewCourses.size());
		return new ModelAndView("ScheduleTraining","viewCourses",viewCourses);
	}
	@RequestMapping("/scheduleTrainingForm")
	public String scheduleTrainingForm(Model model,@RequestParam("courseId") String courseId,HttpServletRequest request) {
		model.addAttribute("course",new Courses());
		request.setAttribute("courseId", courseId);
		System.out.println(courseId);
		return "CreateSchedule";
		
	}
	@RequestMapping("/scheduleTraining")
	public ModelAndView scheduleTraining(@ModelAttribute("course") Courses course) {
		usersServiceIface.scheduleTraingService(course);
		return new ModelAndView("Message","message","Successfully Updated");
	}
	@RequestMapping("/viewSchedule")
	public ModelAndView viewSchedule(@RequestParam("courseId") String courseId){
		Courses courses=usersServiceIface.viewScheduleService(courseId);
		System.out.println(courses);
		return new ModelAndView("ViewScheduleAdmin","courses",courses);
	}
	@RequestMapping("/viewNominations")
	public ModelAndView viewNomination(@RequestParam("courseId") String courseId,HttpServletRequest request){
		List<CourseRegistration> listUsers=usersServiceIface.viewNomination(courseId);
		request.setAttribute("length", listUsers.size());
		return new ModelAndView("ViewNominations","listUsers",listUsers);
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		request.getSession().invalidate();
		return "logout";
		
	}
}
