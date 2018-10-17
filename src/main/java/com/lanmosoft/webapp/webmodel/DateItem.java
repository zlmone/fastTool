package com.lanmosoft.webapp.webmodel;

import java.io.Serializable;

public class DateItem implements Serializable{

	private String fullYear;
	private String month;
	private String date;
	private String day;
	private boolean cur;
	private String hasTask;
	private String hasEvent;
	public String getFullYear() {
		return fullYear;
	}
	public void setFullYear(String fullYear) {
		this.fullYear = fullYear;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public boolean isCur() {
		return cur;
	}
	public void setCur(boolean cur) {
		this.cur = cur;
	}
	public String getHasTask() {
		return hasTask;
	}
	public void setHasTask(String hasTask) {
		this.hasTask = hasTask;
	}
	public String getHasEvent() {
		return hasEvent;
	}
	public void setHasEvent(String hasEvent) {
		this.hasEvent = hasEvent;
	}
}
