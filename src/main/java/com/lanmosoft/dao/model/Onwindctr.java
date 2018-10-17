package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
/**
 * 在线风控
 * @author su.zhang
 *
 */
public class Onwindctr extends BaseModel {
	 private String id;//id
	 private String order_id;//订单Id
	 private String addTime;//创建时间
	 private Integer age;//年龄
	 private String gender;//性别
	 private String birthday;//生日
	 private String idcard_validate;//身份证是否是有效身份证，1通过，0未通过
	 private String idcard_province;//身份证户籍省份
	 private String idcard_city;//身份证户籍城市
	 private String idcard_region;//身份证户籍地区
	 private String phone_operator;//手机运营商
	 private String phone_province;//手机归属地省份
	 private String phone_city;//手机归属地城市
	 private String record_idcard_days;//身份证号记录天数
	 private String record_phone_days;//手机号记录天数
	 private String last_appear_idcard;//身份证最近出现时间
	 private String last_appear_phone;//手机号最近出现时间
	 private String used_idcards_cnt;//关联身份证数量
	 private String used_phones_cnt;//关联手机号数量
	 private String sn_score;//葫芦分
	 private String sn_order1_contacts_cnt;//直接联系人
	 private String sn_order1_blacklist_contacts_cnt;//直接联系人在黑名单中数量(直接黑人)
	 private String sn_order2_blacklist_contacts_cnt;//间接联系人在黑名单中数量(间接黑人)
	 private String sn_order2_blacklist_routers_cnt;//认识间接黑人的直接联系人个数
	 private String sn_order2_blacklist_contacts_pct;//认识间接黑人的直接联系人比例
	 private String idcard_in_blacklist;//身份证是否命中黑名单
	 private String phone_in_blacklist;//手机号是否命中黑名单
	 private String in_court_blacklist;//是否命中法院名单
	 private String in_p2p_blacklist;//是否命中网贷名单
	 private String in_bank_blacklist;//是否命中银行黑名单
	 private String last_appear_idcard_in_blacklist;//最近该身份证出现在黑名单中时间
	 private String last_appear_phone_in_blacklist;//最近该手机号出现在黑名单中时间
	 private String online_installment_cnt;//线上消费分期出现次数
	 private String offline_installment_cnt;//线下消费分期出现次数
	 private String credit_card_repayment_cnt;//信用卡代还出现次数
	 private String payday_loan_cnt;//小额快速贷出现次数
	 private String online_cash_loan_cnt;//线上现金贷出现次数
	 private String offline_cash_loan_cnt;//线下现金贷出现次数
	 private String others_cnt;//其他
	 private String search_cnt;//历史查询总次数
	 private String search_cnt_recent_7_days;//最近7天查询次数
	 private String search_cnt_recent_14_days;//最近14天查询次数
	 private String search_cnt_recent_30_days;//最近30天查询次数
	 private String search_cnt_recent_60_days;//最近60天查询次数
	 private String search_cnt_recent_90_days;//最近90天查询次数
	 private String search_cnt_recent_180_days;//最近180天查询次数
	 private String org_cnt;//历史查询总机构数
	 private String org_cnt_recent_7_days;//最近7天查询机构数
	 private String org_cnt_recent_14_days;//最近14天查询机构数
	 private String org_cnt_recent_30_days;//最近30天查询次数
	 private String org_cnt_recent_60_days;//最近60天查询次数
	 private String org_cnt_recent_90_days;//最近90天查询次数
	 private String org_cnt_recent_180_days;//最近180天查询次数
	 private String result;//火眼黑名单2.0命中规则结果
	 private String status;//0未命中，1命中
	 
	 
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
	 public void setAge(Integer age) {
          this.age = age ;
    }
	 public Integer getAge(){
        return age;
    }
	public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
	 public String getGender(){
        return gender;
    }
	public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }
	 public String getBirthday(){
        return birthday;
    }
	public void setIdcard_validate(String idcard_validate) {
        this.idcard_validate = idcard_validate == null ? null : idcard_validate.trim();
    }
	 public String getIdcard_validate(){
        return idcard_validate;
    }
	public void setIdcard_province(String idcard_province) {
        this.idcard_province = idcard_province == null ? null : idcard_province.trim();
    }
	 public String getIdcard_province(){
        return idcard_province;
    }
	public void setIdcard_city(String idcard_city) {
        this.idcard_city = idcard_city == null ? null : idcard_city.trim();
    }
	 public String getIdcard_city(){
        return idcard_city;
    }
	public void setIdcard_region(String idcard_region) {
        this.idcard_region = idcard_region == null ? null : idcard_region.trim();
    }
	 public String getIdcard_region(){
        return idcard_region;
    }
	public void setPhone_operator(String phone_operator) {
        this.phone_operator = phone_operator == null ? null : phone_operator.trim();
    }
	 public String getPhone_operator(){
        return phone_operator;
    }
	public void setPhone_province(String phone_province) {
        this.phone_province = phone_province == null ? null : phone_province.trim();
    }
	 public String getPhone_province(){
        return phone_province;
    }
	public void setPhone_city(String phone_city) {
        this.phone_city = phone_city == null ? null : phone_city.trim();
    }
	 public String getPhone_city(){
        return phone_city;
    }
	public void setRecord_idcard_days(String record_idcard_days) {
        this.record_idcard_days = record_idcard_days == null ? null : record_idcard_days.trim();
    }
	 public String getRecord_idcard_days(){
        return record_idcard_days;
    }
	public void setRecord_phone_days(String record_phone_days) {
        this.record_phone_days = record_phone_days == null ? null : record_phone_days.trim();
    }
	 public String getRecord_phone_days(){
        return record_phone_days;
    }
	public void setLast_appear_idcard(String last_appear_idcard) {
        this.last_appear_idcard = last_appear_idcard == null ? null : last_appear_idcard.trim();
    }
	 public String getLast_appear_idcard(){
        return last_appear_idcard;
    }
	public void setLast_appear_phone(String last_appear_phone) {
        this.last_appear_phone = last_appear_phone == null ? null : last_appear_phone.trim();
    }
	 public String getLast_appear_phone(){
        return last_appear_phone;
    }
	public void setUsed_idcards_cnt(String used_idcards_cnt) {
        this.used_idcards_cnt = used_idcards_cnt == null ? null : used_idcards_cnt.trim();
    }
	 public String getUsed_idcards_cnt(){
        return used_idcards_cnt;
    }
	public void setUsed_phones_cnt(String used_phones_cnt) {
        this.used_phones_cnt = used_phones_cnt == null ? null : used_phones_cnt.trim();
    }
	 public String getUsed_phones_cnt(){
        return used_phones_cnt;
    }
	public void setSn_score(String sn_score) {
        this.sn_score = sn_score == null ? null : sn_score.trim();
    }
	 public String getSn_score(){
        return sn_score;
    }
	public void setSn_order1_contacts_cnt(String sn_order1_contacts_cnt) {
        this.sn_order1_contacts_cnt = sn_order1_contacts_cnt == null ? null : sn_order1_contacts_cnt.trim();
    }
	 public String getSn_order1_contacts_cnt(){
        return sn_order1_contacts_cnt;
    }
	public void setSn_order1_blacklist_contacts_cnt(String sn_order1_blacklist_contacts_cnt) {
        this.sn_order1_blacklist_contacts_cnt = sn_order1_blacklist_contacts_cnt == null ? null : sn_order1_blacklist_contacts_cnt.trim();
    }
	 public String getSn_order1_blacklist_contacts_cnt(){
        return sn_order1_blacklist_contacts_cnt;
    }
	public void setSn_order2_blacklist_contacts_cnt(String sn_order2_blacklist_contacts_cnt) {
        this.sn_order2_blacklist_contacts_cnt = sn_order2_blacklist_contacts_cnt == null ? null : sn_order2_blacklist_contacts_cnt.trim();
    }
	 public String getSn_order2_blacklist_contacts_cnt(){
        return sn_order2_blacklist_contacts_cnt;
    }
	public void setSn_order2_blacklist_routers_cnt(String sn_order2_blacklist_routers_cnt) {
        this.sn_order2_blacklist_routers_cnt = sn_order2_blacklist_routers_cnt == null ? null : sn_order2_blacklist_routers_cnt.trim();
    }
	 public String getSn_order2_blacklist_routers_cnt(){
        return sn_order2_blacklist_routers_cnt;
    }
	public void setSn_order2_blacklist_contacts_pct(String sn_order2_blacklist_contacts_pct) {
        this.sn_order2_blacklist_contacts_pct = sn_order2_blacklist_contacts_pct == null ? null : sn_order2_blacklist_contacts_pct.trim();
    }
	 public String getSn_order2_blacklist_contacts_pct(){
        return sn_order2_blacklist_contacts_pct;
    }
	public void setIdcard_in_blacklist(String idcard_in_blacklist) {
        this.idcard_in_blacklist = idcard_in_blacklist == null ? null : idcard_in_blacklist.trim();
    }
	 public String getIdcard_in_blacklist(){
        return idcard_in_blacklist;
    }
	public void setPhone_in_blacklist(String phone_in_blacklist) {
        this.phone_in_blacklist = phone_in_blacklist == null ? null : phone_in_blacklist.trim();
    }
	 public String getPhone_in_blacklist(){
        return phone_in_blacklist;
    }
	public void setIn_court_blacklist(String in_court_blacklist) {
        this.in_court_blacklist = in_court_blacklist == null ? null : in_court_blacklist.trim();
    }
	 public String getIn_court_blacklist(){
        return in_court_blacklist;
    }
	public void setIn_p2p_blacklist(String in_p2p_blacklist) {
        this.in_p2p_blacklist = in_p2p_blacklist == null ? null : in_p2p_blacklist.trim();
    }
	 public String getIn_p2p_blacklist(){
        return in_p2p_blacklist;
    }
	public void setIn_bank_blacklist(String in_bank_blacklist) {
        this.in_bank_blacklist = in_bank_blacklist == null ? null : in_bank_blacklist.trim();
    }
	 public String getIn_bank_blacklist(){
        return in_bank_blacklist;
    }
	public void setLast_appear_idcard_in_blacklist(String last_appear_idcard_in_blacklist) {
        this.last_appear_idcard_in_blacklist = last_appear_idcard_in_blacklist == null ? null : last_appear_idcard_in_blacklist.trim();
    }
	 public String getLast_appear_idcard_in_blacklist(){
        return last_appear_idcard_in_blacklist;
    }
	public void setLast_appear_phone_in_blacklist(String last_appear_phone_in_blacklist) {
        this.last_appear_phone_in_blacklist = last_appear_phone_in_blacklist == null ? null : last_appear_phone_in_blacklist.trim();
    }
	 public String getLast_appear_phone_in_blacklist(){
        return last_appear_phone_in_blacklist;
    }
	public void setOnline_installment_cnt(String online_installment_cnt) {
        this.online_installment_cnt = online_installment_cnt == null ? null : online_installment_cnt.trim();
    }
	 public String getOnline_installment_cnt(){
        return online_installment_cnt;
    }
	public String getOffline_installment_cnt() {
		return offline_installment_cnt;
	}
	public void setOffline_installment_cnt(String offline_installment_cnt) {
		this.offline_installment_cnt = offline_installment_cnt;
	}
	public String getCredit_card_repayment_cnt() {
		return credit_card_repayment_cnt;
	}
	public void setCredit_card_repayment_cnt(String credit_card_repayment_cnt) {
		this.credit_card_repayment_cnt = credit_card_repayment_cnt;
	}
	public String getPayday_loan_cnt() {
		return payday_loan_cnt;
	}
	public void setPayday_loan_cnt(String payday_loan_cnt) {
		this.payday_loan_cnt = payday_loan_cnt;
	}
	public String getOnline_cash_loan_cnt() {
		return online_cash_loan_cnt;
	}
	public void setOnline_cash_loan_cnt(String online_cash_loan_cnt) {
		this.online_cash_loan_cnt = online_cash_loan_cnt;
	}
	public String getOffline_cash_loan_cnt() {
		return offline_cash_loan_cnt;
	}
	public void setOffline_cash_loan_cnt(String offline_cash_loan_cnt) {
		this.offline_cash_loan_cnt = offline_cash_loan_cnt;
	}
	public String getOthers_cnt() {
		return others_cnt;
	}
	public void setOthers_cnt(String others_cnt) {
		this.others_cnt = others_cnt;
	}
	public String getSearch_cnt() {
		return search_cnt;
	}
	public void setSearch_cnt(String search_cnt) {
		this.search_cnt = search_cnt;
	}
	public String getSearch_cnt_recent_7_days() {
		return search_cnt_recent_7_days;
	}
	public void setSearch_cnt_recent_7_days(String search_cnt_recent_7_days) {
		this.search_cnt_recent_7_days = search_cnt_recent_7_days;
	}
	public String getSearch_cnt_recent_14_days() {
		return search_cnt_recent_14_days;
	}
	public void setSearch_cnt_recent_14_days(String search_cnt_recent_14_days) {
		this.search_cnt_recent_14_days = search_cnt_recent_14_days;
	}
	public String getSearch_cnt_recent_30_days() {
		return search_cnt_recent_30_days;
	}
	public void setSearch_cnt_recent_30_days(String search_cnt_recent_30_days) {
		this.search_cnt_recent_30_days = search_cnt_recent_30_days;
	}
	public String getSearch_cnt_recent_60_days() {
		return search_cnt_recent_60_days;
	}
	public void setSearch_cnt_recent_60_days(String search_cnt_recent_60_days) {
		this.search_cnt_recent_60_days = search_cnt_recent_60_days;
	}
	public String getSearch_cnt_recent_90_days() {
		return search_cnt_recent_90_days;
	}
	public void setSearch_cnt_recent_90_days(String search_cnt_recent_90_days) {
		this.search_cnt_recent_90_days = search_cnt_recent_90_days;
	}
	public String getSearch_cnt_recent_180_days() {
		return search_cnt_recent_180_days;
	}
	public void setSearch_cnt_recent_180_days(String search_cnt_recent_180_days) {
		this.search_cnt_recent_180_days = search_cnt_recent_180_days;
	}
	public String getOrg_cnt() {
		return org_cnt;
	}
	public void setOrg_cnt(String org_cnt) {
		this.org_cnt = org_cnt;
	}
	public String getOrg_cnt_recent_7_days() {
		return org_cnt_recent_7_days;
	}
	public void setOrg_cnt_recent_7_days(String org_cnt_recent_7_days) {
		this.org_cnt_recent_7_days = org_cnt_recent_7_days;
	}
	public String getOrg_cnt_recent_14_days() {
		return org_cnt_recent_14_days;
	}
	public void setOrg_cnt_recent_14_days(String org_cnt_recent_14_days) {
		this.org_cnt_recent_14_days = org_cnt_recent_14_days;
	}
	public String getOrg_cnt_recent_30_days() {
		return org_cnt_recent_30_days;
	}
	public void setOrg_cnt_recent_30_days(String org_cnt_recent_30_days) {
		this.org_cnt_recent_30_days = org_cnt_recent_30_days;
	}
	public String getOrg_cnt_recent_60_days() {
		return org_cnt_recent_60_days;
	}
	public void setOrg_cnt_recent_60_days(String org_cnt_recent_60_days) {
		this.org_cnt_recent_60_days = org_cnt_recent_60_days;
	}
	public String getOrg_cnt_recent_90_days() {
		return org_cnt_recent_90_days;
	}
	public void setOrg_cnt_recent_90_days(String org_cnt_recent_90_days) {
		this.org_cnt_recent_90_days = org_cnt_recent_90_days;
	}
	public String getOrg_cnt_recent_180_days() {
		return org_cnt_recent_180_days;
	}
	public void setOrg_cnt_recent_180_days(String org_cnt_recent_180_days) {
		this.org_cnt_recent_180_days = org_cnt_recent_180_days;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
}