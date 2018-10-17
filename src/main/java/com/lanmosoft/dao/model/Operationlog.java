package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 操作日志
 * @author su.zhang
 *
 */
public class Operationlog extends BaseModel {
	 private String id;//id
	 private String addTime;//创建时间
	 private String content;//操作内容
	 private String operator;//操作人
	 private String IPAddress;//ip地址
	 
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
	 public String getContent(){
        return content;
    }
	public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
	 public String getOperator(){
        return operator;
    }
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	 
}