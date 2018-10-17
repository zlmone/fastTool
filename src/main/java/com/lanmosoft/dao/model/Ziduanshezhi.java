package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 字段设置
 * @author su.zhang
 *
 */
public class Ziduanshezhi extends BaseModel {
	 private String id;//id
	 private String ziduanmingcheng;//字段名称
	 private String ziduandaima;//字段代码
	 private String ziduanbieming;//字段别名
	 private String ziduanleixing;//字段类型
	 private String ziduanbiaoqian;//字段标签
	 private String ziduankuozhan;//字段扩展
	 private String ziduanxingzhi;//字段性质
	 private String ziduanpaixu;//字段排序
	 private String zhuti;//主体
	 private String qiyong;//启用
	 private String delstatus;//delstatus
	 private Date createTime;//createTime
	 private Date modifiedTime;//modifiedTime
	
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setZiduanmingcheng(String ziduanmingcheng) {
        this.ziduanmingcheng = ziduanmingcheng == null ? null : ziduanmingcheng.trim();
    }
	 public String getZiduanmingcheng(){
        return ziduanmingcheng;
    }
	public void setZiduandaima(String ziduandaima) {
        this.ziduandaima = ziduandaima == null ? null : ziduandaima.trim();
    }
	 public String getZiduandaima(){
        return ziduandaima;
    }
	public void setZiduanbieming(String ziduanbieming) {
        this.ziduanbieming = ziduanbieming == null ? null : ziduanbieming.trim();
    }
	 public String getZiduanbieming(){
        return ziduanbieming;
    }
	public void setZiduanleixing(String ziduanleixing) {
        this.ziduanleixing = ziduanleixing == null ? null : ziduanleixing.trim();
    }
	 public String getZiduanleixing(){
        return ziduanleixing;
    }
	public void setZiduanbiaoqian(String ziduanbiaoqian) {
        this.ziduanbiaoqian = ziduanbiaoqian == null ? null : ziduanbiaoqian.trim();
    }
	 public String getZiduanbiaoqian(){
        return ziduanbiaoqian;
    }
	public void setZiduankuozhan(String ziduankuozhan) {
        this.ziduankuozhan = ziduankuozhan == null ? null : ziduankuozhan.trim();
    }
	 public String getZiduankuozhan(){
        return ziduankuozhan;
    }
	public void setZiduanxingzhi(String ziduanxingzhi) {
        this.ziduanxingzhi = ziduanxingzhi == null ? null : ziduanxingzhi.trim();
    }
	 public String getZiduanxingzhi(){
        return ziduanxingzhi;
    }
	public void setZiduanpaixu(String ziduanpaixu) {
        this.ziduanpaixu = ziduanpaixu == null ? null : ziduanpaixu.trim();
    }
	 public String getZiduanpaixu(){
        return ziduanpaixu;
    }
	public void setZhuti(String zhuti) {
        this.zhuti = zhuti == null ? null : zhuti.trim();
    }
	 public String getZhuti(){
        return zhuti;
    }
	public void setQiyong(String qiyong) {
        this.qiyong = qiyong == null ? null : qiyong.trim();
    }
	 public String getQiyong(){
        return qiyong;
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