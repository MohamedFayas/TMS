package com.virtusa.tmsless.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;

@Repository
public class EmployeeDaoImpl implements EmployeeDaoIface {
	static Logger logger=Logger.getLogger(EmployeeDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public String postCourses(Courses course) {
		return (String)sessionFactory.getCurrentSession().save(course);
	}

	@Transactional
	public String generateCourseId() {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(GENERATE_COURSE_ID);
		List<String> listCourseId=query.list();
		String courseId=listCourseId.get(0);
		int sp=Integer.parseInt(courseId.substring(1));
		sp++;
		String newCourseId="";
		if(sp >=1 && sp <= 9) {
			newCourseId="C00"+sp;
		}
		if(sp >=10 && sp <= 99) {
			newCourseId="C0"+sp;
		}
		if(sp >=100 && sp <= 999) {
			newCourseId="C"+sp;
		}
		return newCourseId;
	}
	@Transactional
	public List<Courses> listCourses(int userId) {
		Query query=sessionFactory.getCurrentSession().createQuery("from Courses where  userId!=:userId");
		query.setParameter("userId",userId);
		List<Courses> listCourses=query.list();
		return listCourses;
		
	}
	@Transactional
	public List<Courses> listCoursesPosted(int userId) {
		Query query=sessionFactory.getCurrentSession().createQuery("from Courses where  userId=:userId");
		query.setParameter("userId",userId);
		List<Courses> listCourses=query.list();
		return listCourses;
	}
	@Transactional
	public Map<Courses, CourseRegistration> viewHistory(int userId) {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery("select * from courses c inner join  courseRegistration rs  on c.courseId=rs.courseId where rs.userId=:Id");
		query.addEntity(Courses.class);
		query.addEntity(CourseRegistration.class);
		query.setParameter("Id",userId);
		List<Object[]> list=query.list();
		Map<Courses,CourseRegistration> map=new HashMap<Courses, CourseRegistration>();
		for(Object[] itr:list) {
			map.put((Courses)itr[0],(CourseRegistration)itr[1]);
		}
		return map;
	}
	@Transactional(readOnly = true)
	public List<CourseRegistration> viewEmpolyee(int userId) {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery("select * from CourseRegistration cr inner join Usersnew on cr.userId=usersnew.userId where mgrno=:mgrId");
		query.addEntity(CourseRegistration.class);
		query.setParameter("mgrId", userId);
		List<CourseRegistration> listCourse=query.list();
		return listCourse;
	}
	@Transactional
	public int updateStatus(String status,String registrationId) {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery("update courseregistration set approvalstatus=:sat where registerid=:rid");
		query.setParameter("sat",status);
		query.setParameter("rid",registrationId);
		return query.executeUpdate();
	}
	@Transactional
	public int updateFeedback(String courseId, double rating) {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery("{call prcUpdateFeedBack(:courseId,:rating)}");
		query.setParameter("courseId", courseId);
		query.setParameter("rating", rating);
		return query.executeUpdate();
		
	}
	@Transactional
	public String registerCourse(CourseRegistration courseRegistration) {
		return (String)sessionFactory.getCurrentSession().save(courseRegistration);
		
	}
	@Transactional
	public String generateRegistrationId() {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(GENERATE_REGISTER_ID);
		List<String> listCourseId=query.list();
		String courseId=listCourseId.get(0);
		int sp=Integer.parseInt(courseId.substring(1));
		sp++;
		String newCourseId="";
		if(sp >=1 && sp <= 9) {
			newCourseId="R00"+sp;
		}
		if(sp >=10 && sp <= 99) {
			newCourseId="R0"+sp;
		}
		if(sp >=100 && sp <= 999) {
			newCourseId="R"+sp;
		}
		return newCourseId;
	}
}
