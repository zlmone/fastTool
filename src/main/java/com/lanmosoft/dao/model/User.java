package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 人员
 * @author su.zhang
 *
 */
public class User extends BaseModel {
	 private String id;//id
	 private String pic;//头像
	 private String value_name;//真实姓名
	 private String name;//用户名
	 private String password;//密码
	 private String category_id;//用户类别id
	 private String category_name;//用户类别
	 private String belong_city;//所属城市
	 private String status;//帐号状态
	 private String department_id;//部门id
	 private String department_name;//部门名称
	 private String position_id;//岗位id
	 private String position_name;//岗位名称
	 private String sex;//性别
	 private String email;//邮箱
	 private String telephone;//手机
	 private String address;//联系地址
	 private String delstatus;//delstatus
	 private String creatorId;//creatorId
	 private String creatorName;//creatorName
	 private String superOrg_id;//所属公司id
	 private String superOrg_name;//所属公司名称
	 private String fulldept_id;//全部门路径id
	 private String fulldept_name;//全部门路径名
	 private String fullpost_id;//全岗位路径id
	 private String fullpost_name;//全岗位路径名
	 private Date createTime;//createTime
	 private Date modifiedTime;//modifiedTime
	 private String account_id;//所属账户id
	 private String account_name;//所属账户名
	 private String org_code;//组织机构代码
	 private String prisonNum;//犯人番号
	 private String kejian_city;
	 private String jianquName;//监区名称
	 
	 private String mark;//备注
	 
	public String getKejian_city() {
		return kejian_city;
	}
	public void setKejian_city(String kejian_city) {
		this.kejian_city = kejian_city== null ? null : kejian_city.trim();
	}
	public String getBelong_city() {
		return belong_city;
	}
	public void setBelong_city(String belong_city) {
		this.belong_city = belong_city;
	}
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
	 public String getPic(){
        return pic;
    }
	public void setValue_name(String value_name) {
        this.value_name = value_name == null ? null : value_name.trim();
    }
	 public String getValue_name(){
        return value_name;
    }
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
	 public String getName(){
        return name;
    }
	public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
	 public String getPassword(){
        return password;
    }
	public void setCategory_id(String category_id) {
        this.category_id = category_id == null ? null : category_id.trim();
    }
	 public String getCategory_id(){
        return category_id;
    }
	public void setCategory_name(String category_name) {
        this.category_name = category_name == null ? null : category_name.trim();
    }
	 public String getCategory_name(){
        return category_name;
    }
	public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
	 public String getStatus(){
        return status;
    }
	public void setDepartment_id(String department_id) {
        this.department_id = department_id == null ? null : department_id.trim();
    }
	 public String getDepartment_id(){
        return department_id;
    }
	public void setDepartment_name(String department_name) {
        this.department_name = department_name == null ? null : department_name.trim();
    }
	 public String getDepartment_name(){
        return department_name;
    }
	public void setPosition_id(String position_id) {
        this.position_id = position_id == null ? null : position_id.trim();
    }
	 public String getPosition_id(){
        return position_id;
    }
	public void setPosition_name(String position_name) {
        this.position_name = position_name == null ? null : position_name.trim();
    }
	 public String getPosition_name(){
        return position_name;
    }
	public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
	 public String getSex(){
        return sex;
    }
	public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
	 public String getEmail(){
        return email;
    }
	public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }
	 public String getTelephone(){
        return telephone;
    }
	public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
	 public String getAddress(){
        return address;
    }
	public void setDelstatus(String delstatus) {
        this.delstatus = delstatus == null ? null : delstatus.trim();
    }
	 public String getDelstatus(){
        return delstatus;
    }
	public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }
	 public String getCreatorId(){
        return creatorId;
    }
	public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }
	 public String getCreatorName(){
        return creatorName;
    }
	public void setSuperOrg_id(String superOrg_id) {
        this.superOrg_id = superOrg_id == null ? null : superOrg_id.trim();
    }
	 public String getSuperOrg_id(){
        return superOrg_id;
    }
	public void setSuperOrg_name(String superOrg_name) {
        this.superOrg_name = superOrg_name == null ? null : superOrg_name.trim();
    }
	 public String getSuperOrg_name(){
        return superOrg_name;
    }
	public void setFulldept_id(String fulldept_id) {
        this.fulldept_id = fulldept_id == null ? null : fulldept_id.trim();
    }
	 public String getFulldept_id(){
        return fulldept_id;
    }
	public void setFulldept_name(String fulldept_name) {
        this.fulldept_name = fulldept_name == null ? null : fulldept_name.trim();
    }
	 public String getFulldept_name(){
        return fulldept_name;
    }
	public void setFullpost_id(String fullpost_id) {
        this.fullpost_id = fullpost_id == null ? null : fullpost_id.trim();
    }
	 public String getFullpost_id(){
        return fullpost_id;
    }
	public void setFullpost_name(String fullpost_name) {
        this.fullpost_name = fullpost_name == null ? null : fullpost_name.trim();
    }
	 public String getFullpost_name(){
        return fullpost_name;
    }
	 public void setCreateTime(Date createTime) {
          this.createTime = createTime ;
    }
	 public Date getCreateTime(){
        return createTime;
    }
	 public void setModifiedTime(Date modifiedTime) {
          this.modifiedTime = modifiedTime ;
    }
	 public Date getModifiedTime(){
        return modifiedTime;
    }
	public void setAccount_id(String account_id) {
        this.account_id = account_id == null ? null : account_id.trim();
    }
	 public String getAccount_id(){
        return account_id;
    }
	public void setAccount_name(String account_name) {
        this.account_name = account_name == null ? null : account_name.trim();
    }
	 public String getAccount_name(){
        return account_name;
    }
	public void setOrg_code(String org_code) {
        this.org_code = org_code == null ? null : org_code.trim();
    }
	 public String getOrg_code(){
        return org_code;
    }
	public String getPrisonNum() {
		return prisonNum;
	}
	public void setPrisonNum(String prisonNum) {
		this.prisonNum = prisonNum ==null ? null : prisonNum.trim();
	}
	public String getJianquName() {
		return jianquName;
	}
	public void setJianquName(String jianquName) {
		this.jianquName = jianquName == null ? null : jianquName.trim();
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	 
}