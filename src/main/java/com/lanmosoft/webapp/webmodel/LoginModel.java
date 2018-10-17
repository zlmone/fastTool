package com.lanmosoft.webapp.webmodel;

import java.io.Serializable;
import java.util.List;

import com.lanmosoft.dao.model.Permission;
import com.lanmosoft.dao.model.Quanxianbiao;
import com.lanmosoft.dao.model.Quanxianbiaoview;
import com.lanmosoft.dao.model.Renyuan;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.dao.model.Zuzhi;

public class LoginModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4337904567503550126L;
	private User user;
	private List<Permission> permissionList;
	
	private Renyuan renyuan;
	private List<Quanxianbiaoview> gongnengQuanxianList;
	private List<Quanxianbiaoview> shujuQuanxianList;
	private Zuzhi zuzhi;
	
	
	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	public List<Permission> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
	public Renyuan getRenyuan() {
		return renyuan;
	}
	public void setRenyuan(Renyuan renyuan) {
		this.renyuan = renyuan;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Quanxianbiaoview> getGongnengQuanxianList() {
		return gongnengQuanxianList;
	}
	public void setGongnengQuanxianList(List<Quanxianbiaoview> gongnengQuanxianList) {
		this.gongnengQuanxianList = gongnengQuanxianList;
	}
	public List<Quanxianbiaoview> getShujuQuanxianList() {
		return shujuQuanxianList;
	}
	public void setShujuQuanxianList(List<Quanxianbiaoview> shujuQuanxianList) {
		this.shujuQuanxianList = shujuQuanxianList;
	}
	public Zuzhi getZuzhi() {
		return zuzhi;
	}
	public void setZuzhi(Zuzhi zuzhi) {
		this.zuzhi = zuzhi;
	}



	
	
}
