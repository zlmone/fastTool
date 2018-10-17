package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 运营商亲情网信息
 * @author su.zhang
 *
 */
public class Family_info extends BaseModel {
	 private String id;//id
	 private String addTime;//创建时间
	 private String order_id;//订单Id
	 private String member_number;//成员号码
	 private String member_cornet;//成员短号
	 private String member_type;//成员类型
	 private String member_name;//成员名称
	
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
	public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
    }
	 public String getOrder_id(){
        return order_id;
    }
	public void setMember_number(String member_number) {
        this.member_number = member_number == null ? null : member_number.trim();
    }
	 public String getMember_number(){
        return member_number;
    }
	public void setMember_cornet(String member_cornet) {
        this.member_cornet = member_cornet == null ? null : member_cornet.trim();
    }
	 public String getMember_cornet(){
        return member_cornet;
    }
	public void setMember_type(String member_type) {
        this.member_type = member_type == null ? null : member_type.trim();
    }
	 public String getMember_type(){
        return member_type;
    }
	public void setMember_name(String member_name) {
        this.member_name = member_name == null ? null : member_name.trim();
    }
	 public String getMember_name(){
        return member_name;
    }
}