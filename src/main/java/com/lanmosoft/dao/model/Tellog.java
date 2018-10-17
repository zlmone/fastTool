package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 手机运营商
 * @author su.zhang
 *
 */
public class Tellog extends BaseModel {
	 private String id;//id
	 private String order_id;//订单id
	 private String addTime;//创建时间
	 private String realSystem;//实名制
	 private String onlinestate;//在网状态
	 private String onlinedes;//在网描述
	 private String timestate;//在网时长类别
	 private String timedes;//在网时长描述
	 private String consume_isp;//消费档次供应商
	 private String consume_code;//消费档次代码
	 private String consume_desc;//消费档次描述
	
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
	public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }
	 public String getAddTime(){
        return addTime;
    }
	public void setRealSystem(String realSystem) {
        this.realSystem = realSystem == null ? null : realSystem.trim();
    }
	 public String getRealSystem(){
        return realSystem;
    }
	public void setOnlinestate(String onlinestate) {
        this.onlinestate = onlinestate == null ? null : onlinestate.trim();
    }
	 public String getOnlinestate(){
        return onlinestate;
    }
	public void setOnlinedes(String onlinedes) {
        this.onlinedes = onlinedes == null ? null : onlinedes.trim();
    }
	 public String getOnlinedes(){
        return onlinedes;
    }
	public void setTimestate(String timestate) {
        this.timestate = timestate == null ? null : timestate.trim();
    }
	 public String getTimestate(){
        return timestate;
    }
	public void setTimedes(String timedes) {
        this.timedes = timedes == null ? null : timedes.trim();
    }
	 public String getTimedes(){
        return timedes;
    }
	public void setConsume_isp(String consume_isp) {
        this.consume_isp = consume_isp == null ? null : consume_isp.trim();
    }
	 public String getConsume_isp(){
        return consume_isp;
    }
	public void setConsume_code(String consume_code) {
        this.consume_code = consume_code == null ? null : consume_code.trim();
    }
	 public String getConsume_code(){
        return consume_code;
    }
	public void setConsume_desc(String consume_desc) {
        this.consume_desc = consume_desc == null ? null : consume_desc.trim();
    }
	 public String getConsume_desc(){
        return consume_desc;
    }
}