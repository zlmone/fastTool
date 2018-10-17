package com.lanmosoft.dao.model;

import java.util.List;
/**
 * 自定义查询
 * @author yuan.Yuwen
 *
 */
public class Zidingyi {
	
	private List<String> titleList;
	private List<ZidingyiSon> infoList;
	
	
	private String sql;
	
	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql(){
		return sql;
	}
	
	public List<String> getTitleList() {
		return titleList;
	}
	
	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}

	public List<ZidingyiSon> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<ZidingyiSon> infoList) {
		this.infoList = infoList;
	}

	

	

}
