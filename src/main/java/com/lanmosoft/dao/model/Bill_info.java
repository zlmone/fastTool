package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 运营商账单信息
 * @author su.zhang
 *
 */
public class Bill_info extends BaseModel {
	 private String id;//id
	 private String addTime;//创建时间
	 private String order_id;//订单Id
	 private String fee_name;//费用名称
	 private String fee_category;//费用类别
	 private String fee_amount;//金额
	 private String user_number;//用户号码
	
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
	public void setFee_name(String fee_name) {
        this.fee_name = fee_name == null ? null : fee_name.trim();
    }
	 public String getFee_name(){
        return fee_name;
    }
	public void setFee_category(String fee_category) {
        this.fee_category = fee_category == null ? null : fee_category.trim();
    }
	 public String getFee_category(){
        return fee_category;
    }
	public void setFee_amount(String fee_amount) {
        this.fee_amount = fee_amount == null ? null : fee_amount.trim();
    }
	 public String getFee_amount(){
        return fee_amount;
    }
	public void setUser_number(String user_number) {
        this.user_number = user_number == null ? null : user_number.trim();
    }
	 public String getUser_number(){
        return user_number;
    }
}