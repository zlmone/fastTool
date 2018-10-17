package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * processActivity
 * @author su.zhang
 *
 */
public class ProcessActivity extends BaseModel {
	 private String id;//id
	 private String mubanid;//mubanid
	 private String fnid;//fnid
	 private String mobanmingcheng;//模板名称
	 private String zhuti;//主体
	 private String muban;//muban
	 private String biaodanid;//表单id
	 private String zhuangtai;//状态
	 private String xuhao;
	 private String dangqiankusuo;//当前库所
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
	
	 
	 
	public String getXuhao() {
		return xuhao;
	}
	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setMubanid(String mubanid) {
        this.mubanid = mubanid == null ? null : mubanid.trim();
    }
	 public String getMubanid(){
        return mubanid;
    }
	public void setFnid(String fnid) {
        this.fnid = fnid == null ? null : fnid.trim();
    }
	 public String getFnid(){
        return fnid;
    }
	public void setMobanmingcheng(String mobanmingcheng) {
        this.mobanmingcheng = mobanmingcheng == null ? null : mobanmingcheng.trim();
    }
	 public String getMobanmingcheng(){
        return mobanmingcheng;
    }
	public void setZhuti(String zhuti) {
        this.zhuti = zhuti == null ? null : zhuti.trim();
    }
	 public String getZhuti(){
        return zhuti;
    }
	public void setMuban(String muban) {
        this.muban = muban == null ? null : muban.trim();
    }
	 public String getMuban(){
        return muban;
    }
	public void setBiaodanid(String biaodanid) {
        this.biaodanid = biaodanid == null ? null : biaodanid.trim();
    }
	 public String getBiaodanid(){
        return biaodanid;
    }
	public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai == null ? null : zhuangtai.trim();
    }
	 public String getZhuangtai(){
        return zhuangtai;
    }
	public void setDangqiankusuo(String dangqiankusuo) {
        this.dangqiankusuo = dangqiankusuo == null ? null : dangqiankusuo.trim();
    }
	 public String getDangqiankusuo(){
        return dangqiankusuo;
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