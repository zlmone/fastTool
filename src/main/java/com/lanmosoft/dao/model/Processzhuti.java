package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 审批主题
 * @author su.zhang
 *
 */
public class Processzhuti extends BaseModel {
	 private String id;//id
	 private String shenpileixing;//审批类型
	 private String iszhanleixin_notice;//是否站内信通知
	 private String isduanxin_notice;//是否短信通知
	 private String isyoujian_notice;//是否邮件通知
	 private String status;//启用or禁用
	 private String belong_city;//所属城市
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
	
	 
	 
	 
	public String getBelong_city() {
		return belong_city;
	}
	public void setBelong_city(String belong_city) {
		this.belong_city = belong_city== null ? null : belong_city.trim();
	}
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setShenpileixing(String shenpileixing) {
        this.shenpileixing = shenpileixing == null ? null : shenpileixing.trim();
    }
	 public String getShenpileixing(){
        return shenpileixing;
    }
	public void setIszhanleixin_notice(String iszhanleixin_notice) {
        this.iszhanleixin_notice = iszhanleixin_notice == null ? null : iszhanleixin_notice.trim();
    }
	 public String getIszhanleixin_notice(){
        return iszhanleixin_notice;
    }
	public void setIsduanxin_notice(String isduanxin_notice) {
        this.isduanxin_notice = isduanxin_notice == null ? null : isduanxin_notice.trim();
    }
	 public String getIsduanxin_notice(){
        return isduanxin_notice;
    }
	public void setIsyoujian_notice(String isyoujian_notice) {
        this.isyoujian_notice = isyoujian_notice == null ? null : isyoujian_notice.trim();
    }
	 public String getIsyoujian_notice(){
        return isyoujian_notice;
    }
	public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
	 public String getStatus(){
        return status;
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