package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 运营商账户信息
 * @author su.zhang
 *
 */
public class Account_info extends BaseModel {
	 private String id;//id
	 private String addTime;//创建时间
	 private String order_id;//订单Id
	 private String account_balance;//账户余额
	 private String current_fee;//当前话费
	 private String mobile_status;//账户状态
	 private String net_time;//入网时间
	 private String net_age;//网龄
	 private String real_info;//实名制信息
	
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
	public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance == null ? null : account_balance.trim();
    }
	 public String getAccount_balance(){
        return account_balance;
    }
	public void setCurrent_fee(String current_fee) {
        this.current_fee = current_fee == null ? null : current_fee.trim();
    }
	 public String getCurrent_fee(){
        return current_fee;
    }
	public void setMobile_status(String mobile_status) {
        this.mobile_status = mobile_status == null ? null : mobile_status.trim();
    }
	 public String getMobile_status(){
        return mobile_status;
    }
	public void setNet_time(String net_time) {
        this.net_time = net_time == null ? null : net_time.trim();
    }
	 public String getNet_time(){
        return net_time;
    }
	public void setNet_age(String net_age) {
        this.net_age = net_age == null ? null : net_age.trim();
    }
	 public String getNet_age(){
        return net_age;
    }
	public void setReal_info(String real_info) {
        this.real_info = real_info == null ? null : real_info.trim();
    }
	 public String getReal_info(){
        return real_info;
    }
}