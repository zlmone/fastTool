package com.lanmosoft.yunservice.biz;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.lanmosoft.util.PinyinUtil;

@XmlRootElement
public class Model {
	private String name;
	private String id;
	private String ext;//定义扩展项，如果true,表示有扩展字段，比如ext0,ext1...ext9
	private String type;

	private List<Field> fields = new ArrayList<Field>();
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
	@XmlElement(name="field")
	public List<Field> getFields() {
		if(StringUtils.equals(ext, "true")){
			 List<Field> fields2 = new ArrayList<Field>();
			 fields2.addAll(fields);
			 for(int i=0;i<10;i++){
				 Field f= new Field();
				 f.setId("ext"+i);
				 f.setName("ext"+i); 
				 fields2.add(f);
			 }
			return fields2;
		}
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	@XmlAttribute
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	@XmlAttribute
	public String getType() {
		if(StringUtils.isEmpty(type)){
			return "table";
		}
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
