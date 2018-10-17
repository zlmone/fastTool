package com.lanmosoft.yunservice.biz;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.lanmosoft.util.PinyinUtil;
@XmlRootElement
public class WindowPage {

	private String id;
	private String name;
	private  Page page;
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
	 @XmlAttribute
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name="page")
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
