package com.lanmosoft.webapp.webmodel;

import java.io.Serializable;

public class SearchViewModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4337904567503550126L;
	private String name;//名称
	private String category;//类别，1：客户，2：联系人
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
