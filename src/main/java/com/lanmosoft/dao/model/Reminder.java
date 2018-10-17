package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 提示
 * @author su.zhang
 *
 */
public class Reminder extends BaseModel {
	 private String id;//id
	 private String reminder_id;//提醒者id
	 private String reminder_name;//提醒者
	 private String form_id;//表单id
	 private String type;//请假  离职 等等..   1物料管理    2 报销管理    3离职管理      4请假管理     5 更改为1审批   
	 private String state;//1.待审核  2.审核通过  3.驳回
	 private String content;//类容
	 private String isRead;//是否已读（0：未读，1：已读）
	 private String delstatus;//delstatus
	 private String creatorId;//creatorId
	 private String creatorName;//creatorName
	 private String superOrg_id;//所属公司id
	 private String superOrg_name;//所属公司名称
	 private String fulldept_id;//全部门路径id
	 private String fulldept_name;//全部门路径名
	 private String fullpost_id;//全岗位路径id
	 private String fullpost_name;//全岗位路径名
	 private String org_code;//组织机构代码
	 private Date createTime;//createTime
	 private Date modifiedTime;//modifiedTime
	
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setReminder_id(String reminder_id) {
        this.reminder_id = reminder_id == null ? null : reminder_id.trim();
    }
	 public String getReminder_id(){
        return reminder_id;
    }
	public void setReminder_name(String reminder_name) {
        this.reminder_name = reminder_name == null ? null : reminder_name.trim();
    }
	 public String getReminder_name(){
        return reminder_name;
    }
	public void setForm_id(String form_id) {
        this.form_id = form_id == null ? null : form_id.trim();
    }
	 public String getForm_id(){
        return form_id;
    }
	public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
	 public String getType(){
        return type;
    }
	public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
	 public String getState(){
        return state;
    }
	public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
	 public String getContent(){
        return content;
    }
	public void setIsRead(String isRead) {
        this.isRead = isRead == null ? null : isRead.trim();
    }
	 public String getIsRead(){
        return isRead;
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
	public void setOrg_code(String org_code) {
        this.org_code = org_code == null ? null : org_code.trim();
    }
	 public String getOrg_code(){
        return org_code;
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