package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 常量
 * @author su.zhang
 *
 */
public class Changliang extends BaseModel {
	private String id;//id
	private String mingcheng;//名称
	private String daima;//代码
	private String leibie;//类别
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;
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
	public void setDaima(String daima) {
		this.daima = daima == null ? null : daima.trim();
	}
	public String getDaima(){
		return daima;
	}
	public void setLeibie(String leibie) {
		this.leibie = leibie == null ? null : leibie.trim();
	}
	public String getLeibie(){
		return leibie;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1 == null ? null : ext1.trim();
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2 == null ? null : ext2.trim();
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3 == null ? null : ext3.trim();
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4 == null ? null : ext4.trim();
	}
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5 == null ? null : ext5.trim();
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