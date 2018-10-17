package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 运营商通话详单
 * @author su.zhang
 *
 */
public class Call_info extends BaseModel {
	 private String id;//id
	 private String addTime;//创建时间
	 private String order_id;//订单Id
	 private String call_address;//通话地点
	 private String call_type_name;//呼叫类型
	 private String call_other_number;//对方号码
	 private String call_time;//通话时长
	 private String contact_type;//号码分类
	 private String contact_name;//号码标签
	 private String contact_area;//号码归属地
	
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
	public void setCall_address(String call_address) {
        this.call_address = call_address == null ? null : call_address.trim();
    }
	 public String getCall_address(){
        return call_address;
    }
	public void setCall_type_name(String call_type_name) {
        this.call_type_name = call_type_name == null ? null : call_type_name.trim();
    }
	 public String getCall_type_name(){
        return call_type_name;
    }
	public void setCall_other_number(String call_other_number) {
        this.call_other_number = call_other_number == null ? null : call_other_number.trim();
    }
	 public String getCall_other_number(){
        return call_other_number;
    }
	public void setCall_time(String call_time) {
        this.call_time = call_time == null ? null : call_time.trim();
    }
	 public String getCall_time(){
        return call_time;
    }
	public void setContact_type(String contact_type) {
        this.contact_type = contact_type == null ? null : contact_type.trim();
    }
	 public String getContact_type(){
        return contact_type;
    }
	public void setContact_name(String contact_name) {
        this.contact_name = contact_name == null ? null : contact_name.trim();
    }
	 public String getContact_name(){
        return contact_name;
    }
	public void setContact_area(String contact_area) {
        this.contact_area = contact_area == null ? null : contact_area.trim();
    }
	 public String getContact_area(){
        return contact_area;
    }
}