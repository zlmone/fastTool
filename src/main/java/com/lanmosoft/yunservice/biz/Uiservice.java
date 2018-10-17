package com.lanmosoft.yunservice.biz;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.lanmosoft.util.PinyinUtil;

@XmlRootElement
public class Uiservice {
	private String name;
	private String id;
	private List<UiMethod> uimethods = new ArrayList<UiMethod>();
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
	@XmlElement(name="uimethod")
	public List<UiMethod> getMethods() {
		return uimethods;
	}
	public void setMethods(List<UiMethod> uimethods) {
		this.uimethods = uimethods;
	}
}
