package com.lanmosoft.yunservice.biz;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.lanmosoft.util.PinyinUtil;

@XmlRootElement(name="uijscontroller" )
public class UijsController {

	private String name;
	private String id;
	
	private List<Uicontroller> uicontrollers = new ArrayList<Uicontroller>();
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
	@XmlElementWrapper(name="uicontrollers")
	@XmlElement(name="uicontroller")
	public List<Uicontroller> getUicontrollers() {
		return uicontrollers;
	}
	public void setUicontrollers(List<Uicontroller> uicontrollers) {
		this.uicontrollers = uicontrollers;
	}
	
}
