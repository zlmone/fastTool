package com.lanmosoft.yunservice.biz;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.lanmosoft.util.PinyinUtil;

@XmlRootElement(name="action" )
public class Action {

	private String name;
	private String id;
	
	private List<Controller> controllers = new ArrayList<Controller>();
	 @XmlAttribute
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 @XmlAttribute
	public String getId() {
		 if(StringUtils.isNotEmpty(id)){
			 return id;
		 }
		return PinyinUtil.convert(name); 
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElementWrapper(name="controllers")
	@XmlElement(name="controller")
	public List<Controller> getControllers() {
		return controllers;
	}
	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
	}
	
	
	
}
