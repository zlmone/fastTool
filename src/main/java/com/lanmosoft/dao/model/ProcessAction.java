package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * processAction
 * @author su.zhang
 *
 */
public class ProcessAction extends BaseModel {
	 private String id;//id
	 private String activityId;//activityId
	 private String biaodanid;//表单id
	 private String zhuti;//主体
	 private String beizhu;//备注
	 private String dongzuo;//动作
	 private String kusuo;//库所
	 private String lastkusuo;//last库所
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
	public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }
	 public String getActivityId(){
        return activityId;
    }
	public void setBiaodanid(String biaodanid) {
        this.biaodanid = biaodanid == null ? null : biaodanid.trim();
    }
	 public String getBiaodanid(){
        return biaodanid;
    }
	public void setZhuti(String zhuti) {
        this.zhuti = zhuti == null ? null : zhuti.trim();
    }
	 public String getZhuti(){
        return zhuti;
    }
	public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }
	 public String getBeizhu(){
        return beizhu;
    }
	public void setDongzuo(String dongzuo) {
        this.dongzuo = dongzuo == null ? null : dongzuo.trim();
    }
	 public String getDongzuo(){
        return dongzuo;
    }
	public void setKusuo(String kusuo) {
        this.kusuo = kusuo == null ? null : kusuo.trim();
    }
	 public String getKusuo(){
        return kusuo;
    }
	public void setLastkusuo(String lastkusuo) {
        this.lastkusuo = lastkusuo == null ? null : lastkusuo.trim();
    }
	 public String getLastkusuo(){
        return lastkusuo;
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