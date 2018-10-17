package com.lanmosoft.webapp.webmodel;

import java.io.Serializable;
import java.util.List;

import com.lanmosoft.dao.model.User;

public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4337904567503550126L;
	 private String id;//id
	 private String name;//名称
	private List<User> userList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
