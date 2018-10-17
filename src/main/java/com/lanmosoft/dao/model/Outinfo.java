package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 外部信息
 * @author su.zhang
 *
 */
public class Outinfo extends BaseModel {
	 private String id;//id
	 private String order_id;//订单Id
	 private String business_status;//工商状态
	 private String business_note;//工商备注
	 private String network_status;//网络状态
	 private String network_note;//网络备注
	 private String manlaw_status;//人法状态
	 private String manlaw_note;//人法备注
	 private String discredit_status;//失信状态
	 private String discredit_note;//失信备注
	 private String note;//备注
	
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
    }
	 public String getOrder_id(){
        return order_id;
    }
	public void setBusiness_status(String business_status) {
        this.business_status = business_status == null ? null : business_status.trim();
    }
	 public String getBusiness_status(){
        return business_status;
    }
	public void setBusiness_note(String business_note) {
        this.business_note = business_note == null ? null : business_note.trim();
    }
	 public String getBusiness_note(){
        return business_note;
    }
	public void setNetwork_status(String network_status) {
        this.network_status = network_status == null ? null : network_status.trim();
    }
	 public String getNetwork_status(){
        return network_status;
    }
	public void setNetwork_note(String network_note) {
        this.network_note = network_note == null ? null : network_note.trim();
    }
	 public String getNetwork_note(){
        return network_note;
    }
	public void setManlaw_status(String manlaw_status) {
        this.manlaw_status = manlaw_status == null ? null : manlaw_status.trim();
    }
	 public String getManlaw_status(){
        return manlaw_status;
    }
	public void setManlaw_note(String manlaw_note) {
        this.manlaw_note = manlaw_note == null ? null : manlaw_note.trim();
    }
	 public String getManlaw_note(){
        return manlaw_note;
    }
	public void setDiscredit_status(String discredit_status) {
        this.discredit_status = discredit_status == null ? null : discredit_status.trim();
    }
	 public String getDiscredit_status(){
        return discredit_status;
    }
	public void setDiscredit_note(String discredit_note) {
        this.discredit_note = discredit_note == null ? null : discredit_note.trim();
    }
	 public String getDiscredit_note(){
        return discredit_note;
    }
	public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
	 public String getNote(){
        return note;
    }
}