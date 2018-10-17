package com.lanmosoft.webapp.webmodel;

import java.util.List;

import com.lanmosoft.dao.model.User;

//ZTREE节点模型
public class Ztree {
	
	private String id;
	private String pid;
	private String name;
	private String type;
	private boolean checked;
	
	
	private String iconSkin;
	
	private String idpath;
	private String namepath;
	private boolean drag;
	private List<User> users;
	public Ztree() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIdpath() {
		return idpath;
	}
	public void setIdpath(String idpath) {
		this.idpath = idpath;
	}
	public String getNamepath() {
		return namepath;
	}
	public void setNamepath(String namepath) {
		this.namepath = namepath;
	}
	public boolean isDrag() {
		return drag;
	}
	public void setDrag(boolean drag) {
		this.drag = drag;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
