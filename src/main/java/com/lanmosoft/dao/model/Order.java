package com.lanmosoft.dao.model;
import java.util.Date;

import com.lanmosoft.model.BaseModel;
import com.lanmosoft.webapp.controller.FileController;
/**
 * 订单管理
 * @author su.zhang
 *
 */
public class Order extends BaseModel {
	 private static final long serialVersionUID =  9074423099683535330L;
	 private String id;//id
	 private String uid;
	 private String status;//0未审核/1一审通过/2分期中/3完成/4关闭
	 private String sex;//性别
	 private String age;//年龄
	 private String cellphone;//移动电话
	 private String bank_id;//银行卡号
	 private String bank_name;//开户银行
	 private String bank_address;// 开户行
	 private String marriage;//婚姻状况
	 private String education;//最高学历
	 private String email;//邮箱
	 private String birth;//户口所在地
	 private String address;//现居地
	 private String live;//来本市年份

	 private String unit;//单位名称
	 private String unitaddress;//单位地址
	 private String unit_size; //单位规模
	 private String unit_type; //单位性质
	 private String work_life; //工作年限
	 private String department; //部门
	 private String position;//职位
	 private String month_incomes; //月收入
	 private String unitphone;//单位电话
	 private String entry_time;//入职时间
	
	 private String addtime;//申请时间
	 private String realname;//姓名
	 private String idcard;//身份证号码
	 private String zm_score;//芝麻信用分
	 private String open_id;//open_id
	 private String riskscore;//风险分数
	 private String first_relation;//第一联系人关系
	 private String first_realname;//第一联系人姓名
	 private String first_cellphone;//第一联系人电话号码
	 private String second_relation;//第二联系人关系
	 private String second_realname;//第二联系人姓名
	 private String second_cellphone;//第二联系人电话号码
	 private String third_relation;//第三联系人关系
	 private String third_realname;//第三联系人姓名
	 private String third_cellphone;//第三联系人电话号码
	 private String fourth_relation;//第四联系人关系
	 private String fourth_realname;//第四联系人姓名
	 private String fourth_cellphone;//第四联系人电话号码
	 private String leibie;//类别
	 private String sellerid;//商家id
	 private String goodsmodel;//商品类别
	 private String goodsname;//商品名称
	 private Double goodsprice;//商品售价
	 private Double firstpay;//首付
	 private String seller_note;//销售备注
	 private String sellercode;//销售代码
	 private Integer monthnumber;//分期月数
	 private Double monthpay;//月付
	 private String ordercode;//订单编号
	 private String self_check_status;//借款人手机号核实状态
	 private String self_phone_addr;//借款人手机号归属地
	 private String self_check_result;//借款人手机号核实结果
	 private String self_check_time;//借款人手机号核实时间
	 private String com_check_status;//单位电话核实状态
	 private String com_phone_addr;//单位电话归属地
	 private String com_check_result;//单位电话核实结果
	 private String com_check_time;//单位电话核实时间
	 private String rela_check_status1;//联系人手机号核实状态1
	 private String rela_phone_addr1;//联系人手机号归属地1
	 private String rela_check_result1;//联系人手机号核实结果1
	 private String rela_check_time1;//联系人手机号核实时间1
	 private String rela_check_status2;//联系人手机号核实状态2
	 private String rela_phone_addr2;//联系人手机号归属地2
	 private String rela_check_result2;//联系人手机号核实结果2
	 private String rela_check_time2;//联系人手机号核实时间2
	 private String rela_check_status3;//联系人手机号核实状态3
	 private String rela_phone_addr3;//联系人手机号归属地3
	 private String rela_check_result3;//联系人手机号核实结果3
	 private String rela_check_time3;//联系人手机号核实时间3
	 private String rela_check_status4;//联系人手机号核实状态4
	 private String rela_phone_addr4;//联系人手机号归属地4
	 private String rela_check_result4;//联系人手机号核实结果4
	 private Date rela_check_time4;//联系人手机号核实时间4
	
	 private String idcardLeftPath;
	 private String idcardRightPath;
	 private String oneselfLeftPath;
	 private String oneselfRightPath; 
	 
	 private String jielun;//0,回退补件，1，拒绝,2,通过
	 private String tongguoTime;//通过时间
	 private String jujue_reason;//拒绝原因
	 private String note1;//回退补件备注
	 private String note2;//拒绝备注
	 private String note3;//通过备注
	 private String merchant;//服务网点
	 private String firstreason;//父拒绝原因
	 private String returnvisit;//回访结论
	 private String shenpiStatus;//0,待审批，1审核中，2审核完成,3回退补件,4进件挂起
	 private String shenpiTime;//审批时间
	 private String user_id;//审核人Id
	 private String user_name;//审核人姓名
	 private String isWind;//是否查询得在线风控的数据，0未取得，1取得
	 private String tongdun_task_id;//同盾任务Id
	 private String task_status;//爬取任务状态 0任务创建，1任务成功,2任务失败,3任务超时
	 
	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
	 public String getId(){
        return id;
    }
	 
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
	 public String getStatus(){
        return status;
    }
	public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
	 public String getSex(){
        return sex;
    }
	public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }
	 public String getAge(){
        return age;
    }
	public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }
	 public String getCellphone(){
        return cellphone;
    }
	public void setMarriage(String marriage) {
        this.marriage = marriage == null ? null : marriage.trim();
    }
	 public String getMarriage(){
        return marriage;
    }
	public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }
	 public String getEducation(){
        return education;
    }
	public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
	 public String getAddress(){
        return address;
    }
	
	
	public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }
	 public String getPosition(){
        return position;
    }
	public void setEntry_time(String entry_time) {
        this.entry_time = entry_time == null ? null : entry_time.trim();
    }
	 public String getEntry_time(){
        return entry_time;
    }
	
	public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }
	 public String getAddtime(){
        return addtime;
    }
	public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }
	 public String getRealname(){
        return realname;
    }
	public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }
	 public String getIdcard(){
        return idcard;
    }
	public void setZm_score(String zm_score) {
        this.zm_score = zm_score == null ? null : zm_score.trim();
    }
	 public String getZm_score(){
        return zm_score;
    }
	public void setOpen_id(String open_id) {
        this.open_id = open_id == null ? null : open_id.trim();
    }
	 public String getOpen_id(){
        return open_id;
    }
	public void setRiskscore(String riskscore) {
        this.riskscore = riskscore == null ? null : riskscore.trim();
    }
	 public String getRiskscore(){
        return riskscore;
    }
	public void setFirst_relation(String first_relation) {
        this.first_relation = first_relation == null ? null : first_relation.trim();
    }
	 public String getFirst_relation(){
        return first_relation;
    }
	public void setFirst_realname(String first_realname) {
        this.first_realname = first_realname == null ? null : first_realname.trim();
    }
	 public String getFirst_realname(){
        return first_realname;
    }
	public void setFirst_cellphone(String first_cellphone) {
        this.first_cellphone = first_cellphone == null ? null : first_cellphone.trim();
    }
	 public String getFirst_cellphone(){
        return first_cellphone;
    }
	public void setSecond_relation(String second_relation) {
        this.second_relation = second_relation == null ? null : second_relation.trim();
    }
	 public String getSecond_relation(){
        return second_relation;
    }
	public void setSecond_realname(String second_realname) {
        this.second_realname = second_realname == null ? null : second_realname.trim();
    }
	 public String getSecond_realname(){
        return second_realname;
    }
	public void setSecond_cellphone(String second_cellphone) {
        this.second_cellphone = second_cellphone == null ? null : second_cellphone.trim();
    }
	 public String getSecond_cellphone(){
        return second_cellphone;
    }
	public void setThird_relation(String third_relation) {
        this.third_relation = third_relation == null ? null : third_relation.trim();
    }
	 public String getThird_relation(){
        return third_relation;
    }
	public void setThird_realname(String third_realname) {
        this.third_realname = third_realname == null ? null : third_realname.trim();
    }
	 public String getThird_realname(){
        return third_realname;
    }
	public void setThird_cellphone(String third_cellphone) {
        this.third_cellphone = third_cellphone == null ? null : third_cellphone.trim();
    }
	 public String getThird_cellphone(){
        return third_cellphone;
    }
	public void setFourth_relation(String fourth_relation) {
        this.fourth_relation = fourth_relation == null ? null : fourth_relation.trim();
    }
	 public String getFourth_relation(){
        return fourth_relation;
    }
	public void setFourth_realname(String fourth_realname) {
        this.fourth_realname = fourth_realname == null ? null : fourth_realname.trim();
    }
	 public String getFourth_realname(){
        return fourth_realname;
    }
	public void setFourth_cellphone(String fourth_cellphone) {
        this.fourth_cellphone = fourth_cellphone == null ? null : fourth_cellphone.trim();
    }
	 public String getFourth_cellphone(){
        return fourth_cellphone;
    }
	public void setLeibie(String leibie) {
        this.leibie = leibie == null ? null : leibie.trim();
    }
	 public String getLeibie(){
        return leibie;
    }
	public void setSellerid(String sellerid) {
        this.sellerid = sellerid == null ? null : sellerid.trim();
    }
	 public String getSellerid(){
        return sellerid;
    }
	public void setGoodsmodel(String goodsmodel) {
        this.goodsmodel = goodsmodel == null ? null : goodsmodel.trim();
    }
	 public String getGoodsmodel(){
        return goodsmodel;
    }
	public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }
	 public String getGoodsname(){
        return goodsname;
    }
	public void setSellercode(String sellercode) {
        this.sellercode = sellercode == null ? null : sellercode.trim();
    }
	 public String getSellercode(){
        return sellercode;
    }
	 public void setMonthnumber(Integer monthnumber) {
          this.monthnumber = monthnumber ;
    }
	 public Integer getMonthnumber(){
        return monthnumber;
    }
	public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }
	 public String getOrdercode(){
        return ordercode;
    }
	public void setSelf_check_status(String self_check_status) {
        this.self_check_status = self_check_status == null ? null : self_check_status.trim();
    }
	 public String getSelf_check_status(){
        return self_check_status;
    }
	public void setSelf_phone_addr(String self_phone_addr) {
        this.self_phone_addr = self_phone_addr == null ? null : self_phone_addr.trim();
    }
	 public String getSelf_phone_addr(){
        return self_phone_addr;
    }
	public void setSelf_check_result(String self_check_result) {
        this.self_check_result = self_check_result == null ? null : self_check_result.trim();
    }
	 public String getSelf_check_result(){
        return self_check_result;
    }
	public void setCom_check_status(String com_check_status) {
        this.com_check_status = com_check_status == null ? null : com_check_status.trim();
    }
	 public String getCom_check_status(){
        return com_check_status;
    }
	public void setCom_phone_addr(String com_phone_addr) {
        this.com_phone_addr = com_phone_addr == null ? null : com_phone_addr.trim();
    }
	 public String getCom_phone_addr(){
        return com_phone_addr;
    }
	public void setCom_check_result(String com_check_result) {
        this.com_check_result = com_check_result == null ? null : com_check_result.trim();
    }
	 public String getCom_check_result(){
        return com_check_result;
    }
	public void setRela_check_status1(String rela_check_status1) {
        this.rela_check_status1 = rela_check_status1 == null ? null : rela_check_status1.trim();
    }
	 public String getRela_check_status1(){
        return rela_check_status1;
    }
	public void setRela_phone_addr1(String rela_phone_addr1) {
        this.rela_phone_addr1 = rela_phone_addr1 == null ? null : rela_phone_addr1.trim();
    }
	 public String getRela_phone_addr1(){
        return rela_phone_addr1;
    }
	public void setRela_check_result1(String rela_check_result1) {
        this.rela_check_result1 = rela_check_result1 == null ? null : rela_check_result1.trim();
    }
	 public String getRela_check_result1(){
        return rela_check_result1;
    }
	public void setRela_check_status2(String rela_check_status2) {
        this.rela_check_status2 = rela_check_status2 == null ? null : rela_check_status2.trim();
    }
	 public String getRela_check_status2(){
        return rela_check_status2;
    }
	public void setRela_phone_addr2(String rela_phone_addr2) {
        this.rela_phone_addr2 = rela_phone_addr2 == null ? null : rela_phone_addr2.trim();
    }
	 public String getRela_phone_addr2(){
        return rela_phone_addr2;
    }
	public void setRela_check_result2(String rela_check_result2) {
        this.rela_check_result2 = rela_check_result2 == null ? null : rela_check_result2.trim();
    }
	 public String getRela_check_result2(){
        return rela_check_result2;
    }
	public void setRela_check_status3(String rela_check_status3) {
        this.rela_check_status3 = rela_check_status3 == null ? null : rela_check_status3.trim();
    }
	 public String getRela_check_status3(){
        return rela_check_status3;
    }
	public void setRela_phone_addr3(String rela_phone_addr3) {
        this.rela_phone_addr3 = rela_phone_addr3 == null ? null : rela_phone_addr3.trim();
    }
	 public String getRela_phone_addr3(){
        return rela_phone_addr3;
    }
	public void setRela_check_result3(String rela_check_result3) {
        this.rela_check_result3 = rela_check_result3 == null ? null : rela_check_result3.trim();
    }
	 public String getRela_check_result3(){
        return rela_check_result3;
    }
	public void setRela_check_status4(String rela_check_status4) {
        this.rela_check_status4 = rela_check_status4 == null ? null : rela_check_status4.trim();
    }
	 public String getRela_check_status4(){
        return rela_check_status4;
    }
	public void setRela_phone_addr4(String rela_phone_addr4) {
        this.rela_phone_addr4 = rela_phone_addr4 == null ? null : rela_phone_addr4.trim();
    }
	 public String getRela_phone_addr4(){
        return rela_phone_addr4;
    }
	public void setRela_check_result4(String rela_check_result4) {
        this.rela_check_result4 = rela_check_result4 == null ? null : rela_check_result4.trim();
    }
	 public String getRela_check_result4(){
        return rela_check_result4;
    }
	 public void setRela_check_time4(Date rela_check_time4) {
          this.rela_check_time4 = rela_check_time4 ;
    }
	 public Date getRela_check_time4(){
        return rela_check_time4;
    }
	public Double getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(Double goodsprice) {
		this.goodsprice = goodsprice;
	}
	public Double getFirstpay() {
		return firstpay;
	}
	public void setFirstpay(Double firstpay) {
		this.firstpay = firstpay;
	}
	public Double getMonthpay() {
		return monthpay;
	}
	public void setMonthpay(Double monthpay) {
		this.monthpay = monthpay;
	}
	public String getSelf_check_time() {
		return self_check_time;
	}
	public void setSelf_check_time(String self_check_time) {
		this.self_check_time = self_check_time;
	}
	public String getCom_check_time() {
		return com_check_time;
	}
	public void setCom_check_time(String com_check_time) {
		this.com_check_time = com_check_time;
	}
	public String getRela_check_time1() {
		return rela_check_time1;
	}
	public void setRela_check_time1(String rela_check_time1) {
		this.rela_check_time1 = rela_check_time1;
	}
	public String getRela_check_time2() {
		return rela_check_time2;
	}
	public void setRela_check_time2(String rela_check_time2) {
		this.rela_check_time2 = rela_check_time2;
	}
	public String getRela_check_time3() {
		return rela_check_time3;
	}
	public void setRela_check_time3(String rela_check_time3) {
		this.rela_check_time3 = rela_check_time3;
	}
	public String getJielun() {
		return jielun;
	}
	public void setJielun(String jielun) {
		this.jielun = jielun;
	}
	public String getJujue_reason() {
		return jujue_reason;
	}
	public void setJujue_reason(String jujue_reason) {
		this.jujue_reason = jujue_reason;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	public String getNote3() {
		return note3;
	}
	public void setNote3(String note3) {
		this.note3 = note3;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getTongguoTime() {
		return tongguoTime;
	}
	public void setTongguoTime(String tongguoTime) {
		this.tongguoTime = tongguoTime;
	}
	public String getFirstreason() {
		return firstreason;
	}
	public void setFirstreason(String firstreason) {
		this.firstreason = firstreason;
	}
	public String getReturnvisit() {
		return returnvisit;
	}
	public void setReturnvisit(String returnvisit) {
		this.returnvisit = returnvisit;
	}
	public String getShenpiStatus() {
		return shenpiStatus;
	}
	public void setShenpiStatus(String shenpiStatus) {
		this.shenpiStatus = shenpiStatus;
	}
	public String getShenpiTime() {
		return shenpiTime;
	}
	public void setShenpiTime(String shenpiTime) {
		this.shenpiTime = shenpiTime;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getIsWind() {
		return isWind;
	}
	public void setIsWind(String isWind) {
		this.isWind = isWind;
	}
	public String getTongdun_task_id() {
		return tongdun_task_id;
	}
	public void setTongdun_task_id(String tongdun_task_id) {
		this.tongdun_task_id = tongdun_task_id;
	}
	public String getTask_status() {
		return task_status;
	}
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}
	public String getSeller_note() {
		return seller_note;
	}
	public void setSeller_note(String seller_note) {
		this.seller_note = seller_note;
	}
	 public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id == null ? null : bank_id.trim();
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name == null ? null : bank_name.trim();
	}
	public String getBank_address() {
		return bank_address;
	}
	public void setBank_address(String bank_address) {
		this.bank_address = bank_address == null ? null : bank_address.trim() ;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getLive() {
		return live;
	}
	public void setLive(String live) {
		this.live = live;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitaddress() {
		return unitaddress;
	}
	public void setUnitaddress(String unitaddress) {
		this.unitaddress = unitaddress;
	}
	public String getUnit_size() {
		return unit_size;
	}
	public void setUnit_size(String unit_size) {
		this.unit_size = unit_size;
	}
	public String getUnit_type() {
		return unit_type;
	}
	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}
	public String getWork_life() {
		return work_life;
	}
	public void setWork_life(String work_life) {
		this.work_life = work_life;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMonth_incomes() {
		return month_incomes;
	}
	public void setMonth_incomes(String month_incomes) {
		this.month_incomes = month_incomes;
	}
	public String getUnitphone() {
		return unitphone;
	}
	public void setUnitphone(String unitphone) {
		this.unitphone = unitphone;
	}
	public String getIdcardLeftPath() {
		return idcardLeftPath;
	}
	public void setIdcardLeftPath(String idcardLeftPath) {
		this.idcardLeftPath = idcardLeftPath;
	}
	public String getIdcardRightPath() {
		return idcardRightPath;
	}
	public void setIdcardRightPath(String idcardRightPath) {
		this.idcardRightPath = idcardRightPath;
	}
	public String getOneselfLeftPath() {
		return oneselfLeftPath;
	}
	public void setOneselfLeftPath(String oneselfLeftPath) {
		this.oneselfLeftPath = oneselfLeftPath;
	}
	public String getOneselfRightPath() {
		return oneselfRightPath;
	}
	public void setOneselfRightPath(String oneselfRightPath) {
		this.oneselfRightPath = oneselfRightPath;
	}

   
	
	
}