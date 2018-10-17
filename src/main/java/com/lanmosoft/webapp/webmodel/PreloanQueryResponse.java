package com.lanmosoft.webapp.webmodel;

import java.io.Serializable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author qbq-pc on 16/11/16.
 */
public class PreloanQueryResponse implements Serializable {

    private static final long serialVersionUID = 4152462211121573434L;

    private Boolean           success          = false;
    private JSONArray         risk_items;
    private JSONObject        address_detect;
    private String            application_id;
    private String            final_decision;
    private String            report_id;
    private Long              apply_time;
    private Long              report_time;
    private Integer           final_score;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public JSONArray getRisk_items() {
		return risk_items;
	}
	public void setRisk_items(JSONArray risk_items) {
		this.risk_items = risk_items;
	}
	public JSONObject getAddress_detect() {
		return address_detect;
	}
	public void setAddress_detect(JSONObject address_detect) {
		this.address_detect = address_detect;
	}
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	public String getFinal_decision() {
		return final_decision;
	}
	public void setFinal_decision(String final_decision) {
		this.final_decision = final_decision;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public Long getApply_time() {
		return apply_time;
	}
	public void setApply_time(Long apply_time) {
		this.apply_time = apply_time;
	}
	public Long getReport_time() {
		return report_time;
	}
	public void setReport_time(Long report_time) {
		this.report_time = report_time;
	}
	public Integer getFinal_score() {
		return final_score;
	}
	public void setFinal_score(Integer final_score) {
		this.final_score = final_score;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    //ignore getter and setter
    
    
}

            