package com.lanmosoft.yunservice.biz;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.lanmosoft.util.PinyinUtil;

@XmlRootElement
public class UiField {

	private String id;
	private String name;
	
	private String type;
	
	private String length;
	
	private String select;
	
	private String tag;
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
	 @XmlAttribute
	public String getType() {
		if(StringUtils.isNotEmpty(type)){
			return type;
		}
		return "string";
	}
	public void setType(String type) {
		this.type = type;
	}
	 @XmlAttribute
	public String getLength() {
		 if(StringUtils.isNotEmpty(length)){
			 return length;
		 }
		 if(StringUtils.equals("decimal", type)){
			 return "15,2";
		 }
		 if(StringUtils.equals("long", type)){
			 return "10";
		 }
		return "128";
	}
	public void setLength(String length) {
		this.length = length;
	}
	 @XmlAttribute
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	 @XmlAttribute
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
