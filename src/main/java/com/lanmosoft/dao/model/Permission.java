package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * permission
 * @author su.zhang
 *
 */
public class Permission extends BaseModel {
	 private String id;//id
	 private String position_id;//岗位id
	 private String position_name;//岗位名称
	 private String function_id;//功能点id
	 private String function_name;//功能点名称
	 private String function_type;//功能点类型
	 private String firstModule;//所属一级模块
	 private String secondModule;//所二级属模块
	 private String data_role;//数据角色权限
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
	
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
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
	public void setFunction_id(String function_id) {
        this.function_id = function_id == null ? null : function_id.trim();
    }
	 public String getFunction_id(){
        return function_id;
    }
	public void setFunction_name(String function_name) {
        this.function_name = function_name == null ? null : function_name.trim();
    }
	 public String getFunction_name(){
        return function_name;
    }
	public void setFunction_type(String function_type) {
        this.function_type = function_type == null ? null : function_type.trim();
    }
	 public String getFunction_type(){
        return function_type;
    }
	public void setFirstModule(String firstModule) {
        this.firstModule = firstModule == null ? null : firstModule.trim();
    }
	 public String getFirstModule(){
        return firstModule;
    }
	public void setSecondModule(String secondModule) {
        this.secondModule = secondModule == null ? null : secondModule.trim();
    }
	 public String getSecondModule(){
        return secondModule;
    }
	public void setData_role(String data_role) {
        this.data_role = data_role == null ? null : data_role.trim();
    }
	 public String getData_role(){
        return data_role;
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
}