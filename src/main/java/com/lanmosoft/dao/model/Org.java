package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * org
 * @author su.zhang
 *
 */
public class Org extends BaseModel {
	 private String id;//id
	 private String name;//名称
	 private String department_id;//所属部门id
	 private String department_name;//所属部门名称
	 private String position_id;//所属岗位id
	 private String position_name;//所属岗位名称
	 private String description;//描述
	 private String org_type;//组织类型
	 private String status;//可用状态
	 private Integer sortNum;//排序
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
	public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
	 public String getDescription(){
        return description;
    }
	public void setOrg_type(String org_type) {
        this.org_type = org_type == null ? null : org_type.trim();
    }
	 public String getOrg_type(){
        return org_type;
    }
	public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
	 public String getStatus(){
        return status;
    }
	 public void setSortNum(Integer sortNum) {
          this.sortNum = sortNum ;
    }
	 public Integer getSortNum(){
        return sortNum;
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
}