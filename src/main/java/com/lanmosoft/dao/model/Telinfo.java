package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 电话信息
 * @author su.zhang
 *
 */
public class Telinfo extends BaseModel {
	 private String id;//id
	 private String order_id;//订单Id
	 private String source;//来源
	 private String type;//类型
	 private String phonenum;//电话号码
	 private String status;//是否调查
	 private String address;//归属地
	 private String note;//备注
	 private Date createTime;//创建时间
	 private Date telTime;//核实时间
	
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
    }
	 public String getOrder_id(){
        return order_id;
    }
	public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
	 public String getSource(){
        return source;
    }
	public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
	 public String getType(){
        return type;
    }
	public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }
	 public String getPhonenum(){
        return phonenum;
    }
	public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
	 public String getStatus(){
        return status;
    }
	public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
	 public String getAddress(){
        return address;
    }
	public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
	 public String getNote(){
        return note;
    }
	 public void setCreateTime(Date createTime) {
          this.createTime = createTime ;
    }
	 public Date getCreateTime(){
        return createTime;
    }
	 public void setTelTime(Date telTime) {
          this.telTime = telTime ;
    }
	 public Date getTelTime(){
        return telTime;
    }
}