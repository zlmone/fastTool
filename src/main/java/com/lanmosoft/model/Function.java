package com.lanmosoft.model;


//功能点模型
public class Function{
	
	private String id;//功能点id
	private String name;//功能点名称
	private String type;//功能点类型simple:简单功能，complex:含数据权限功能
	private String firstModule;//所属一级模块
	private String secondModule;//所二级属模块
	private boolean checked;
	private String description;//button：按钮，menu:菜单
	private String dataRole;//数据角色权限
	public Function() {
		super();
	}
	public Function(String id, String name, String type, String firstModule,
			String secondModule, String description) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.firstModule = firstModule;
		this.secondModule = secondModule;
		this.description = description;
	}
	public Function(String id, String name, String type, String firstModule,
			String secondModule, boolean checked, String description) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.firstModule = firstModule;
		this.secondModule = secondModule;
		this.checked = checked;
		this.description = description;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFirstModule() {
		return firstModule;
	}
	public void setFirstModule(String firstModule) {
		this.firstModule = firstModule;
	}
	public String getSecondModule() {
		return secondModule;
	}
	public void setSecondModule(String secondModule) {
		this.secondModule = secondModule;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDataRole() {
		return dataRole;
	}
	public void setDataRole(String dataRole) {
		this.dataRole = dataRole;
	}
}
