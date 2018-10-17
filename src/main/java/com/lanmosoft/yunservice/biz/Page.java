package com.lanmosoft.yunservice.biz;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.lanmosoft.util.PinyinUtil;

@XmlRootElement
public class Page {
	private String name;
	private String id;
	private List<UiField> search = new ArrayList<UiField>();
	private List<UiField> table = new ArrayList<UiField>();
	private SubTable subTable;
	private WindowPage windowPage;
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
	@XmlElementWrapper(name="search")
	@XmlElement(name="field")
	public List<UiField> getSearch() {
		return search;
	}
	public void setFields( List<UiField> search) {
		this.search = search;
	}
	@XmlElementWrapper(name="table")
	@XmlElement(name="field")
	public List<UiField> getTable() {
		return table;
	}
	public void setTable( List<UiField> table) {
		this.table = table;
	}
	@XmlElement(name="subtable")
	public SubTable getSubTable() {
		return subTable;
	}
	public void setSubTable(SubTable subTable) {
		this.subTable = subTable;
	}
	@XmlElement(name="windowpage")
	public WindowPage getWindowPage() {
		return windowPage;
	}
	public void setWindowPage(WindowPage windowPage) {
		this.windowPage = windowPage;
	}
}
