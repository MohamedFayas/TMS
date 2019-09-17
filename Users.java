package com.virtusa.tmsless.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="UsersNew")
public class Users {
	@Id
	@Column(name="UserId")
	private int userId;
	@Column(name="userName")
	private String userName;
	@Column(name="Job")
	private String userJob;
	@Column(name="Salary")
	private int userSalary;
	@Column(name="MgrNo")
	private int userMgrno;
	@Column(name="ContactNumber")
	private String contactNumber;
	@Column(name="Email")
	private String userMail;
	@Column(name="Password")
	private String userPassword;
	@Column (name="typeUser")
	private String userType;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserJob() {
		return userJob;
	}
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public int getUserSalary() {
		return userSalary;
	}
	public void setUserSalary(int userSalary) {
		this.userSalary = userSalary;
	}
	public int getUserMgrno() {
		return userMgrno;
	}
	public void setUserMgrno(int userMgrno) {
		this.userMgrno = userMgrno;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
