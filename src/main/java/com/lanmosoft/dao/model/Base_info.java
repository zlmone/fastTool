package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 运营商个人信息
 * @author su.zhang
 *
 */
public class Base_info extends BaseModel {
	 private String id;//id
	 private String order_id;//订单Id
	 private String addTime;//创建时间
	 private String user_name;//姓名
	 private String user_sex;//性别
	 private String user_number;//手机号码
	 private String cert_num;//身份证号码
	 private String cert_addr;//联系地址
	 private String user_contact_no;//联系电话
	 private String user_email;//邮箱地址
	
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
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }
	 public String getUser_name(){
        return user_name;
    }
	public void setUser_sex(String user_sex) {
        this.user_sex = user_sex == null ? null : user_sex.trim();
    }
	 public String getUser_sex(){
        return user_sex;
    }
	public void setUser_number(String user_number) {
        this.user_number = user_number == null ? null : user_number.trim();
    }
	 public String getUser_number(){
        return user_number;
    }
	public void setCert_num(String cert_num) {
        this.cert_num = cert_num == null ? null : cert_num.trim();
    }
	 public String getCert_num(){
        return cert_num;
    }
	public void setCert_addr(String cert_addr) {
        this.cert_addr = cert_addr == null ? null : cert_addr.trim();
    }
	 public String getCert_addr(){
        return cert_addr;
    }
	public void setUser_contact_no(String user_contact_no) {
        this.user_contact_no = user_contact_no == null ? null : user_contact_no.trim();
    }
	 public String getUser_contact_no(){
        return user_contact_no;
    }
	public void setUser_email(String user_email) {
        this.user_email = user_email == null ? null : user_email.trim();
    }
	 public String getUser_email(){
        return user_email;
    }
}