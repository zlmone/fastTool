package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 回访结论
 * @author su.zhang
 *
 */
public class Return_visit extends BaseModel {
	 private String id;//id
	 private String order_id;//订单Id
	 private String addTime;//创建时间
	 private String content;//回访结论
	 private String creator;//创建人
	
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
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
	 public String getContent(){
        return content;
    }
	public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
	 public String getCreator(){
        return creator;
    }
}