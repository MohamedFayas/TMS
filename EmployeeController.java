package com.virtusa.tmsless.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;
import com.virtusa.tmsless.service.EmployeeServiceIface;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeServiceIface employeeServiceIface;
	
	@RequestMapping("/postCourseForm")
	public String postCourseForm(Model model,HttpServletRequest request) {
		model.addAttribute("course",new Courses());
		request.setAttribute("userName", request.getSession().getAttribute("name"));
		request.setAttribute("courseId", employeeServiceIface.generateCourseIdService());
		return "PostTraining";
	}
	@RequestMapping("/postCourse")
	public ModelAndView postCourse(@ModelAttribute("course") Courses course) {
		employeeServiceIface.postCourse(course);
		return new ModelAndView("Message","message","Added Successfully");
	}
	@RequestMapping("/viewCoursesEmployee")
	public ModelAndView viewCourses(HttpServletRequest request) {
		int userId=(Integer)request.getSession().getAttribute("userId");
		List<Courses> listCourses=employeeServiceIface.listCoursesService(userId);
		return new ModelAndView("ViewCourses","listCourses",listCourses);
	}
	@RequestMapping("/postedCourses")
	public ModelAndView viewCoursesPosted(HttpServletRequest request) {
		int userId=(Integer)request.getSession().getAttribute("userId");
		List<Courses> listCourses=employeeServiceIface.listCoursesPostedService(userId);
		return new ModelAndView("PostedCourses","listCourses",listCourses);
	} 
	@RequestMapping("/viewHistory")
	public ModelAndView viewHistory(HttpServletRequest request) {
		int userId=(Integer)request.getSession().getAttribute("userId");
		Map<Courses,CourseRegistration> map=employeeServiceIface.viewHistoryService(userId);
		return new ModelAndView("ViewHistory","listHistory",map);
	}
	@RequestMapping("/viewEmployee")
	public ModelAndView viewNomination(HttpServletRequest request) {
		int userId=(Integer)request.getSession().getAttribute("userId");
		List<CourseRegistration> listEmploy =employeeServiceIface.viewEmploy(userId);
		request.setAttribute("length", listEmploy.size());
		return new ModelAndView("ViewEmployee","listEmploy",listEmploy);
	}
	@RequestMapping("/updateStatus")
	public ModelAndView updateStatus(@RequestParam("status") String status,@RequestParam("registrationId") String registrationId) {
		int result=employeeServiceIface.upadteStatusService(status, registrationId);
		if(result>0) {
			return new ModelAndView("Message","message","updated Successfully");
		}
		else {
			return new ModelAndView("Message","message","update error");
		}
	}
	@RequestMapping("/giveFeedback")
	public String giveFeedback(@ModelAttribute("course") Courses course,HttpServletRequest request,@RequestParam("courseId") String courseId){
		request.setAttribute("courseId", courseId);
		return "GiveFeedback";
	}
	@RequestMapping("/updateFeedback")
	public ModelAndView updateFeedback(@RequestParam("courseId") String courseId,@RequestParam("rating") int rating)
	{
		int a=employeeServiceIface.updateFeedbackService(courseId, rating);
		if(a>0) {
			return new  ModelAndView("Message","message","updated Successfully");
		}
		else {

			return new  ModelAndView("Message","message","Error");
		}
	}
	@RequestMapping("/registerCourseForm")
	public String registerCourseForm(Model m,HttpServletRequest request,@RequestParam("courseId") String courseId,@RequestParam("courseName") String courseName) {
		m.addAttribute("registration",new CourseRegistration());
		String registrationId=employeeServiceIface.generateRegisterIdService();
		request.setAttribute("courseId", courseId);
		request.setAttribute("courseName", courseName);
		request.setAttribute("registrationId",registrationId );
		return "RegisterCourse";
		
	}
	@RequestMapping("/registerCourse")
	public ModelAndView registerCourse(@ModelAttribute("registration") CourseRegistration registration) {
		System.out.println(registration);
		employeeServiceIface.registerCourse(registration);
		return new ModelAndView("Message","message","Registered Successfully");
	}
	@RequestMapping("/viewTaughtCourses")
	public ModelAndView viewTaughtCourses(HttpServletRequest request)
	{
		int userId=(Integer)request.getSession().getAttribute("userId");
		List<Courses> listCourses=employeeServiceIface.listCoursesPostedService(userId);
		return new ModelAndView("ViewTaughtCourses","listCourses",listCourses);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
