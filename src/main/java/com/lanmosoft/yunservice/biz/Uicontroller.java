package com.lanmosoft.yunservice.biz;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.lanmosoft.util.PinyinUtil;

@XmlRootElement
public class Uicontroller {
	private String name;
	private String id;
	private String sub;
	private List<UiControllerMethod> uiControllermethods = new ArrayList<UiControllerMethod>();
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
	@XmlElement(name="uicontrollermethod")
	public List<UiControllerMethod> getUiControllerMethods() {
		return uiControllermethods;
	}
	public void setUicontrollerMethods(List<UiControllerMethod> uiControllermethods) {
		this.uiControllermethods = uiControllermethods;
	}
	 @XmlAttribute
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
}
