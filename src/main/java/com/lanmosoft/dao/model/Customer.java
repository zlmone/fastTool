package com.lanmosoft.dao.model;

import com.lanmosoft.model.BaseModel;

/**
 * 登录
 * 
 * @author luhuiqi
 */
public class Customer extends BaseModel {

	private static final long serialVersionUID = 3236366141763086022L;
	private String id;
	private String login_name;
	private String login_password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

}
