package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 商户管理
 * @author su.zhang
 *
 */
public class Seller extends BaseModel {
	 private String id;//id
	 private String name;//商户名
	 private String contactor;//联系人
	 private String address;//地址
	 private String cellphone;//电话号码
	 private String number;//编号
	 private String password;//密码
	 private String addtime;//创建时间
	 private String delstatus;//删除标志
	
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
	 public String getName(){
        return name;
    }
	public void setContactor(String contactor) {
        this.contactor = contactor == null ? null : contactor.trim();
    }
	 public String getContactor(){
        return contactor;
    }
	public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
	 public String getAddress(){
        return address;
    }
	public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }
	 public String getCellphone(){
        return cellphone;
    }
	public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }
	 public String getNumber(){
        return number;
    }
	public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
	 public String getPassword(){
        return password;
    }
	public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }
	 public String getAddtime(){
        return addtime;
    }
	public void setDelstatus(String delstatus) {
        this.delstatus = delstatus == null ? null : delstatus.trim();
    }
	 public String getDelstatus(){
        return delstatus;
    }
}