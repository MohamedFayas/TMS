package com.virtusa.tmsless.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;
import com.virtusa.tmsless.model.Users;

@Repository
public class UsersDaoImpl implements UsersDaoIface{
	static Logger logger=Logger.getLogger(UsersDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly = true)
	public List<String> validateUser(Users user) {
		
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery("select typeUser,UserName from UsersNew where userId=:userId and password=:password");
		query.setParameter("userId", user.getUserId());
		query.setParameter("password", user.getUserPassword());
		List<Object[]> list=query.list();
		List<String> listValidate=new ArrayList<String>();
		if(list.size()>0) {
			for(Object[] itr:list) {
				listValidate.add(itr[0].toString());
				listValidate.add(itr[1].toString());
			}
			return listValidate;
		}
		return null;
	}
	@Transactional(readOnly = true)
	public Users aboutUsers(int userId) {
		return (Users)sessionFactory.getCurrentSession().get(Users.class,userId);
	}
	@Transactional
	public int addUser(Users user) {
		return (Integer)sessionFactory.getCurrentSession().save(user);
		
	}
	@Transactional
	public int generateUserId() {
		 SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(GENERATE_USER_ID);
		 return Integer.parseInt(query.list().get(0).toString());
	}
	@Transactional(readOnly = true)
	public List<Users> viewUsers() {
		Query query=sessionFactory.getCurrentSession().createQuery("from Users order by userId");
		List<Users> listUsers=query.list();
		return listUsers;
	}
	@Transactional(readOnly = true)
	public List<Courses> viewCourses() {
		Query query=sessionFactory.getCurrentSession().createQuery("from Courses order by courseId");
		List<Courses> listUsers=query.list();
		return listUsers;
	}
	@Transactional
	public int scheduleTraing(Courses courses) {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery("update courses set startDate=:sDate,endDate=:eDate,venue=:ven where courseId=:crseId");
		query.setParameter("sDate",courses.getStartDate());
		query.setParameter("eDate", courses.getEndDate());
		query.setParameter("ven",courses.getVenue());
		query.setParameter("crseId", courses.getCourseId());
		return query.executeUpdate();
		
	}
	@Transactional(readOnly = true)
	public Courses viewSchedule(String courseId) {
		return (Courses)sessionFactory.getCurrentSession().get(Courses.class,courseId);
	}
	@Transactional(readOnly = true)
	public List<CourseRegistration> viewNominations(String courseId) {
		Query query=sessionFactory.getCurrentSession().createQuery("from CourseRegistration where courseId=:cId");
		query.setParameter("cId",courseId);
		//List<CourseRegistration> listUsers=query.list();
		return query.list();
	} 
}
