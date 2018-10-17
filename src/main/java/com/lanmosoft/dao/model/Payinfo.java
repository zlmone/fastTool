package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 还款信息
 * @author su.zhang
 *
 */
public class Payinfo extends BaseModel {
	 private String id;//id
	 private String uid;//订单id
	 private String loanid;//loanid
	 private String gid;//gid
	 private String yborderid;//yborderid
	 private String requestno;//requestno
	 private String status;//是否还款
	 private String sort_order;//sort_order
	 private String totalnumber;//总期数
	 private String monthpay;//每期还款
	 private String capital;//本金
	 private String interest;//利息
	 private String overdue;//逾期费用
	 private String overdays;//逾期天数
	 private String dorepayment_time;//已还时间
	 private String repayment_time;//还款时间
	 private String admin_name;//admin_name
	 private String addtime;//添加时间
	 private String fangkuanTime;//放款日期
	 private String realname;//借款人
	 private String cellphone;//手机号码
	 private String idcard;//身份证号码
	 private String sellercode;//销售代码
	 private String goodsprice;//商品价格
	 private String firstpay;//首付
	 private String days;//逾期天数
	 
	 
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }
	 public String getUid(){
        return uid;
    }
	public void setLoanid(String loanid) {
        this.loanid = loanid == null ? null : loanid.trim();
    }
	 public String getLoanid(){
        return loanid;
    }
	public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }
	 public String getGid(){
        return gid;
    }
	public void setYborderid(String yborderid) {
        this.yborderid = yborderid == null ? null : yborderid.trim();
    }
	 public String getYborderid(){
        return yborderid;
    }
	public void setRequestno(String requestno) {
        this.requestno = requestno == null ? null : requestno.trim();
    }
	 public String getRequestno(){
        return requestno;
    }
	public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
	 public String getStatus(){
        return status;
    }
	public void setSort_order(String sort_order) {
        this.sort_order = sort_order == null ? null : sort_order.trim();
    }
	 public String getSort_order(){
        return sort_order;
    }
	public void setTotalnumber(String totalnumber) {
        this.totalnumber = totalnumber == null ? null : totalnumber.trim();
    }
	 public String getTotalnumber(){
        return totalnumber;
    }
	
	public String getMonthpay() {
		return monthpay;
	}
	public void setMonthpay(String monthpay) {
		this.monthpay = monthpay;
	}
	public void setCapital(String capital) {
        this.capital = capital == null ? null : capital.trim();
    }
	 public String getCapital(){
        return capital;
    }
	public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }
	 public String getInterest(){
        return interest;
    }
	public void setOverdue(String overdue) {
        this.overdue = overdue == null ? null : overdue.trim();
    }
	 public String getOverdue(){
        return overdue;
    }
	public void setDorepayment_time(String dorepayment_time) {
        this.dorepayment_time = dorepayment_time == null ? null : dorepayment_time.trim();
    }
	 public String getDorepayment_time(){
        return dorepayment_time;
    }
	public void setRepayment_time(String repayment_time) {
        this.repayment_time = repayment_time == null ? null : repayment_time.trim();
    }
	 public String getRepayment_time(){
        return repayment_time;
    }
	public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name == null ? null : admin_name.trim();
    }
	 public String getAdmin_name(){
        return admin_name;
    }
	public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }
	 public String getAddtime(){
        return addtime;
    }
	public String getOverdays() {
		return overdays;
	}
	public void setOverdays(String overdays) {
		this.overdays = overdays;
	}
	public String getFangkuanTime() {
		return fangkuanTime;
	}
	public void setFangkuanTime(String fangkuanTime) {
		this.fangkuanTime = fangkuanTime;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getSellercode() {
		return sellercode;
	}
	public void setSellercode(String sellercode) {
		this.sellercode = sellercode;
	}
	public String getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(String goodsprice) {
		this.goodsprice = goodsprice;
	}
	public String getFirstpay() {
		return firstpay;
	}
	public void setFirstpay(String firstpay) {
		this.firstpay = firstpay;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	 
}