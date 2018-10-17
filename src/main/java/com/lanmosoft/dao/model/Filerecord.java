package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 文件记录
 * @author su.zhang
 *
 */
public class Filerecord extends BaseModel {
	 private String id;//id
	 private String yuanshimingcheng;//原始名称
	 private String xianshimingcheng;//显示名称
	 private String houzhuiming;//后缀名
	 private String logicname;//logicname
	 private String logicfullpath;//logicfullpath
	 private String beizhu;//备注
	 private String xitongzhuangtai;//系统状态
	 private String refid;//refid
	 private String gongxiangid;//gongxiangid
	 private String gongxiangname;//gongxiangname
	 private String leixing;//类型  1.身份证正面照片  2.身份证反面照片  3.本人手持身份证照片 4。和店员合照
	 private String delstatus;//delstatus
	 private String creatorId;//creatorId
	 private String creatorName;//creatorName
	 private String creatorCode;//creatorCode
	 private String creatorIdPath;//creatorIdPath
	 private String creatorNamePath;//creatorNamePath
	 private String creatorCodePath;//creatorCodePath
	 private Date createTime;//createTime
	 private Date modifiedTime;//modifiedTime
	
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setYuanshimingcheng(String yuanshimingcheng) {
        this.yuanshimingcheng = yuanshimingcheng == null ? null : yuanshimingcheng.trim();
    }
	 public String getYuanshimingcheng(){
        return yuanshimingcheng;
    }
	public void setXianshimingcheng(String xianshimingcheng) {
        this.xianshimingcheng = xianshimingcheng == null ? null : xianshimingcheng.trim();
    }
	 public String getXianshimingcheng(){
        return xianshimingcheng;
    }
	public void setHouzhuiming(String houzhuiming) {
        this.houzhuiming = houzhuiming == null ? null : houzhuiming.trim();
    }
	 public String getHouzhuiming(){
        return houzhuiming;
    }
	public void setLogicname(String logicname) {
        this.logicname = logicname == null ? null : logicname.trim();
    }
	 public String getLogicname(){
        return logicname;
    }
	public void setLogicfullpath(String logicfullpath) {
        this.logicfullpath = logicfullpath == null ? null : logicfullpath.trim();
    }
	 public String getLogicfullpath(){
        return logicfullpath;
    }
	public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }
	 public String getBeizhu(){
        return beizhu;
    }
	public void setXitongzhuangtai(String xitongzhuangtai) {
        this.xitongzhuangtai = xitongzhuangtai == null ? null : xitongzhuangtai.trim();
    }
	 public String getXitongzhuangtai(){
        return xitongzhuangtai;
    }
	public void setRefid(String refid) {
        this.refid = refid == null ? null : refid.trim();
    }
	 public String getRefid(){
        return refid;
    }
	public void setGongxiangid(String gongxiangid) {
        this.gongxiangid = gongxiangid == null ? null : gongxiangid.trim();
    }
	 public String getGongxiangid(){
        return gongxiangid;
    }
	public void setGongxiangname(String gongxiangname) {
        this.gongxiangname = gongxiangname == null ? null : gongxiangname.trim();
    }
	 public String getGongxiangname(){
        return gongxiangname;
    }
	public void setLeixing(String leixing) {
        this.leixing = leixing == null ? null : leixing.trim();
    }
	 public String getLeixing(){
        return leixing;
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
	public void setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode == null ? null : creatorCode.trim();
    }
	 public String getCreatorCode(){
        return creatorCode;
    }
	public void setCreatorIdPath(String creatorIdPath) {
        this.creatorIdPath = creatorIdPath == null ? null : creatorIdPath.trim();
    }
	 public String getCreatorIdPath(){
        return creatorIdPath;
    }
	public void setCreatorNamePath(String creatorNamePath) {
        this.creatorNamePath = creatorNamePath == null ? null : creatorNamePath.trim();
    }
	 public String getCreatorNamePath(){
        return creatorNamePath;
    }
	public void setCreatorCodePath(String creatorCodePath) {
        this.creatorCodePath = creatorCodePath == null ? null : creatorCodePath.trim();
    }
	 public String getCreatorCodePath(){
        return creatorCodePath;
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