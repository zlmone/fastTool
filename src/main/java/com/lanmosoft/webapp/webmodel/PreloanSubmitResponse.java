package com.lanmosoft.webapp.webmodel;

import java.io.Serializable;

public class PreloanSubmitResponse implements Serializable {

    private static final long serialVersionUID = 4152462611121573434L;
    private Boolean                 success          = false;
    private String                  report_id;
    private PreloanQueryResponse    data;
    
    //ignore getter and setter


	public Boolean getSuccess() {
		return success;
	}

	@Override
	public String toString() {
		return "PreloanSubmitResponse [success=" + success + ", report_id=" + report_id + ", data=" + data + "]";
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getReport_id() {
		return report_id;
	}

	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}

	public PreloanQueryResponse getData() {
		return data;
	}

	public void setData(PreloanQueryResponse data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
            