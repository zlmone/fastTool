package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 常量树
 * @author su.zhang
 *
 */
public class Changliangshu extends BaseModel {
	private String id;//id
	private String mingcheng;//名称
	private String bianma;//编码
	private String quanlujingming;//全路径名
	private String quanlujingbianma;//全路径编码
	private String quanlujingid;//全路径id
	private String fujiedian;//父节点
	private String xuhao;//序号
	private String keyongzhuangtai;//可用状态
	private String changliangshuleixing;//常量树类型
	private String jiesuostatus;//解锁状态
	private String delstatus;//delstatus
	private Date createTime;//createTime
	private Date modifiedTime;//modifiedTime

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	public String getId(){
		return id;
	}
	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng == null ? null : mingcheng.trim();
	}
	public String getMingcheng(){
		return mingcheng;
	}
	public void setBianma(String bianma) {
		this.bianma = bianma == null ? null : bianma.trim();
	}
	public String getBianma(){
		return bianma;
	}
	public void setQuanlujingming(String quanlujingming) {
		this.quanlujingming = quanlujingming == null ? null : quanlujingming.trim();
	}
	public String getQuanlujingming(){
		return quanlujingming;
	}
	public void setQuanlujingbianma(String quanlujingbianma) {
		this.quanlujingbianma = quanlujingbianma == null ? null : quanlujingbianma.trim();
	}
	public String getQuanlujingbianma(){
		return quanlujingbianma;
	}
	public void setQuanlujingid(String quanlujingid) {
		this.quanlujingid = quanlujingid == null ? null : quanlujingid.trim();
	}
	public String getQuanlujingid(){
		return quanlujingid;
	}
	public void setFujiedian(String fujiedian) {
		this.fujiedian = fujiedian == null ? null : fujiedian.trim();
	}
	public String getFujiedian(){
		return fujiedian;
	}
	public void setXuhao(String xuhao) {
		this.xuhao = xuhao == null ? null : xuhao.trim();
	}
	public String getXuhao(){
		return xuhao;
	}
	public void setKeyongzhuangtai(String keyongzhuangtai) {
		this.keyongzhuangtai = keyongzhuangtai == null ? null : keyongzhuangtai.trim();
	}
	public String getKeyongzhuangtai(){
		return keyongzhuangtai;
	}
	public void setChangliangshuleixing(String changliangshuleixing) {
		this.changliangshuleixing = changliangshuleixing == null ? null : changliangshuleixing.trim();
	}
	public String getChangliangshuleixing(){
		return changliangshuleixing;
	}
	public String getJiesuostatus() {
		return jiesuostatus;
	}
	public void setJiesuostatus(String jiesuostatus) {
		this.jiesuostatus = jiesuostatus;
	}
	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus == null ? null : delstatus.trim();
	}
	public String getDelstatus(){
		return delstatus;
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