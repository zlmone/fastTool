package com.lanmosoft.dao.model;

import java.io.Serializable;

public class TongJi implements Serializable{

	private String amount;
	
	private String jine;

	private String riqi;
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getJine() {
		return jine;
	}

	public void setJine(String jine) {
		this.jine = jine;
	}

	public String getRiqi() {
		return riqi;
	}

	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}
	
}
