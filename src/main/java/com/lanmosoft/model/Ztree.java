package com.lanmosoft.model;
//ZTREE节点模型
public class Ztree {
	
	private String id;
	private String pid;
	private String name;
	private String type;
	
	private boolean checked;
	
	private String idpath;
	private String namepath;
	
	public Ztree() {
		super();
		// TODO Auto-generated constructor stub
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


	public Ztree(String id, String pid, String name, String type) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.type = type;
	}
	public Ztree(String id, String pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
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


	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
