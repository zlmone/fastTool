/**
 * auto generated
 * Copyright (C) 2013 lanmosoft.com, All rights reserved.
 */
package com.lanmosoft.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lanmosoft.dao.model.Account_info;
import com.lanmosoft.dao.model.Base_info;
import com.lanmosoft.dao.model.Bill_info;
import com.lanmosoft.dao.model.Call_info;
import com.lanmosoft.dao.model.ChaChong;
import com.lanmosoft.dao.model.Customer;
import com.lanmosoft.dao.model.Family_info;
import com.lanmosoft.dao.model.Filerecord;
import com.lanmosoft.dao.model.Onwindctr;
import com.lanmosoft.dao.model.Operationlog;
import com.lanmosoft.dao.model.Order;
import com.lanmosoft.dao.model.Outinfo;
import com.lanmosoft.dao.model.Payinfo;
import com.lanmosoft.dao.model.Repetition;
import com.lanmosoft.dao.model.Tellog;
import com.lanmosoft.dao.model.Time;
import com.lanmosoft.dao.model.TongJi;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.enums.Enums.DelStatus;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.Account_infoService;
import com.lanmosoft.service.biz.Base_infoService;
import com.lanmosoft.service.biz.Bill_infoService;
import com.lanmosoft.service.biz.Call_infoService;
import com.lanmosoft.service.biz.CustomerService;
import com.lanmosoft.service.biz.Family_infoService;
import com.lanmosoft.service.biz.OnwindctrService;
import com.lanmosoft.service.biz.OperationlogService;
import com.lanmosoft.service.biz.OrderService;
import com.lanmosoft.service.biz.OutinfoService;
import com.lanmosoft.service.biz.PayinfoService;
import com.lanmosoft.service.biz.TellogService;
import com.lanmosoft.service.lanmobase.SequenceManager;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.DateTools;
import com.lanmosoft.util.HttpUtils;
import com.lanmosoft.util.HttpUtils2;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.util.LanDateUtils;
import com.lanmosoft.util.RequestCloud;
import com.lanmosoft.webapp.controller.searchForm.Page;
import com.lanmosoft.webapp.webmodel.LoginModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * <p>
 * auto genegrated
 * </p>
 * <p>
 * Author: lanmo
 * </p>
 */
@Controller
public class OrderController extends BaseController {
    @Autowired
	CustomerService customerService;
	@Autowired
	OrderService orderService;
	
	@Autowired
	PayinfoService payinfoService;
	
	@Autowired
	OutinfoService outinfoService;
	
	@Autowired
	OperationlogService operationlogService;
	
	@Autowired
	OnwindctrService onwindctrService;
	
	@Autowired
	TellogService tellogService;
	
	@Autowired
	Base_infoService base_infoService;
	
	@Autowired
	Account_infoService account_infoService;
	
	@Autowired
	Call_infoService call_infoService;
	
	@Autowired
	Bill_infoService bill_infoService;
	
	@Autowired
	Family_infoService family_infoService;
	
	@RequestMapping(value = "/ngres/dingdanguanli/order/edit")
	public String execute(ModelMap model,final @RequestBody String content,
			String age,final HttpServletRequest request,final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
		   protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject job = jsonObj.getJSONObject("item");
			String type = null;
			String action = null;
			if(job.containsKey("type")){
				 type = job.getString("type");
			}
			if(job.containsKey("action")){
				 action = job.getString("action");
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			LoginModel loginModel = (LoginModel) request.getSession().getAttribute("login");
			User user = loginModel.getUser();
			Order p = new Order();
			Outinfo outinfo = new Outinfo();
			JSONObject.toBean(job, p, jsonConfig);
			InetAddress address = InetAddress.getLocalHost();
			Order order = orderService.loadById(p.getId());
			if (StringUtils.isNotEmpty(p.getId())) {
				if(StringUtils.equals(type, "1")){
					WhereCondition wc = new WhereCondition();
					wc.andEquals("order_id", p.getId());
					List<Outinfo> outinfos = outinfoService.query(wc);
					if(outinfos.size()>0){
						outinfo = outinfos.get(0);
					}
					if(StringUtils.isEmpty(order.getJielun())){
						AjaxUtils.ajaxJsonErrorMessage(response, "请先选择初审结论");
						return;
					}else{
						if(StringUtils.equals(order.getJielun(), "2")){
							if(StringUtils.isEmpty(outinfo.getBusiness_status())){//工商网查询判断
								AjaxUtils.ajaxJsonErrorMessage(response, "请先完成工商网查询");
								return;
							}else{
								if(StringUtils.equals(outinfo.getBusiness_status(), "2")){
									if(StringUtils.isEmpty(outinfo.getBusiness_note())){
										AjaxUtils.ajaxJsonErrorMessage(response, "请先完善工商网查询结果");
										return;
									}
								}
							}
							if(StringUtils.isEmpty(outinfo.getNetwork_status())){//网络查询查询判断
								AjaxUtils.ajaxJsonErrorMessage(response, "请先完成网络查询");
								return;
							}else{
								if(StringUtils.equals(outinfo.getNetwork_status(), "2")){
									if(StringUtils.isEmpty(outinfo.getNetwork_note())){
										AjaxUtils.ajaxJsonErrorMessage(response, "请先完善网络查询结果");
										return;
									}
								}
							}
							if(StringUtils.isEmpty(outinfo.getManlaw_status())){//人法网查询查询判断
								AjaxUtils.ajaxJsonErrorMessage(response, "请先完成人法网查询");
								return;
							}else{
								if(StringUtils.equals(outinfo.getManlaw_status(), "2")){
									if(StringUtils.isEmpty(outinfo.getManlaw_note())){
										AjaxUtils.ajaxJsonErrorMessage(response, "请先完善人法网查询结果");
										return;
									}
								}
							}
							if(StringUtils.isEmpty(outinfo.getDiscredit_status())){//失信网查询判断
								AjaxUtils.ajaxJsonErrorMessage(response, "请先完成失信网查询");
								return;
							}else{
								if(StringUtils.equals(outinfo.getDiscredit_status(), "2")){
									if(StringUtils.isEmpty(outinfo.getDiscredit_note())){
										AjaxUtils.ajaxJsonErrorMessage(response, "请先完善失信网查询结果");
										return;
									}
								}
							}
							if(StringUtils.equals(order.getSelf_check_status(), "0")){//借款人手机号核实判断
								AjaxUtils.ajaxJsonErrorMessage(response, "请先完成借款人手机号核实");
								return;
							}else{
								if(StringUtils.equals(order.getSelf_check_status(), "1")){
									if(StringUtils.isEmpty(order.getSelf_check_result())){
										AjaxUtils.ajaxJsonErrorMessage(response, "请先完善借款人手机号核实结果");
										return;
									}
								}
							}
							if(StringUtils.equals(order.getCom_check_status(), "0")){//单位电话核实判断
								AjaxUtils.ajaxJsonErrorMessage(response, "请先完成单位电话核实");
								return;
							}else{
								if(StringUtils.equals(order.getCom_check_status(), "1")){
									if(StringUtils.isEmpty(order.getCom_check_result())){
										AjaxUtils.ajaxJsonErrorMessage(response, "请先完善单位电话核实结果");
										return;
									}
								}
							}
							if(StringUtils.isEmpty(order.getJielun())){//结论判断
								AjaxUtils.ajaxJsonErrorMessage(response, "请先完善审核结论");
							}else{
								if(StringUtils.equals(order.getJielun(), "0")){
									if(StringUtils.isEmpty(order.getNote1())){
										AjaxUtils.ajaxJsonErrorMessage(response, "请填写回退补件具体结果");
										return;
									}
								}else if(StringUtils.equals(order.getJielun(), "1")){
									if(StringUtils.isEmpty(order.getFirstreason())){
										AjaxUtils.ajaxJsonErrorMessage(response, "请选择拒绝父原因");
										return;
									}
									if(StringUtils.isEmpty(order.getJujue_reason())){
										AjaxUtils.ajaxJsonErrorMessage(response, "请选择拒绝子原因");
										return;
									}
								}
							}
							Operationlog operationlog = new Operationlog();
							String id = sequenceManager.generateId("operationlog");
							operationlog.setId(id);
							operationlog.setIPAddress(address.getHostAddress());
							operationlog.setAddTime(dateFormat2.format(new Date()));
							operationlog.setOperator(user.getName());
							operationlog.setContent(user.getName()+"拒绝了"+order.getRealname()+"的订单");
							operationlogService.insert(operationlog);
						}
						if(StringUtils.equals(order.getJielun(), "0")){
							Operationlog operationlog = new Operationlog();
							String id = sequenceManager.generateId("operationlog");
							operationlog.setId(id);
							operationlog.setIPAddress(address.getHostAddress());
							operationlog.setAddTime(dateFormat2.format(new Date()));
							operationlog.setOperator(user.getName());
							operationlog.setContent(user.getName()+"回退补件了"+order.getRealname()+"的订单");
							operationlogService.insert(operationlog);
							p.setShenpiStatus("3");
						}else{
							Operationlog operationlog = new Operationlog();
							String id = sequenceManager.generateId("operationlog");
							operationlog.setId(id);
							operationlog.setIPAddress(address.getHostAddress());
							operationlog.setAddTime(dateFormat2.format(new Date()));
							operationlog.setOperator(user.getName());
							operationlog.setContent(user.getName()+"通过了"+order.getRealname()+"的订单");
							operationlogService.insert(operationlog);
							p.setShenpiStatus("2");
						}
						p.setShenpiTime(dateFormat2.format(new Date()));
						if(StringUtils.equals(p.getJielun(), "2")){
							p.setTongguoTime(dateFormat.format(new Date()));
						}
					}
				}else if(StringUtils.equals(type, "2")){
					Operationlog operationlog = new Operationlog();
					String id = sequenceManager.generateId("operationlog");
					operationlog.setId(id);
					operationlog.setIPAddress(address.getHostAddress());
					operationlog.setAddTime(dateFormat2.format(new Date()));
					operationlog.setOperator(user.getName());
					operationlog.setContent(user.getName()+"进件挂起"+order.getRealname()+"的订单");
					operationlogService.insert(operationlog);
						p.setShenpiStatus("4");
				}
				Operationlog operationlog = new Operationlog();
				String id = sequenceManager.generateId("operationlog");
				operationlog.setId(id);
				operationlog.setIPAddress(address.getHostAddress());
				operationlog.setAddTime(dateFormat2.format(new Date()));
				operationlog.setOperator(user.getName());
				if(StringUtils.equals(action, "1")){//操作订单
					operationlog.setContent(user.getName()+"操作"+order.getRealname()+"的订单");
					operationlogService.insert(operationlog);
				}
				orderService.update(p);
			} else {
				String id = sequenceManager.generateId("order");
				p.setId(id);
				initCreate(p, request);
				orderService.insert(p);
			}
			AjaxUtils.ajaxJsonSuccessMessage(response, "操作成功!");
		} catch (Exception e) {// TODO
			arg0.setRollbackOnly();
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
			}
		});
		return null;
	}


	
	@RequestMapping(value = "/ngres/dingdanguanli/order/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Order> list = new ArrayList<Order>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Order p = new Order();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Order p : list) {
				ids.add(p.getId());
			}
			
			if(ids.size()!=0){
				WhereCondition wc = new WhereCondition();
				wc.andIn("id", ids);
				
				Order p = new Order();
				p.setDelstatus(DelStatus.REMOVED);//删除状态
				orderService.updateByCondition(wc, p);
				AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功!");
			}else{
				AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			}
			//orderService.deleteByCondition(wc);
			//AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/dingdanguanli/order/list")
	public String execute1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginModel loginModel = (LoginModel) request.getSession().getAttribute("login");
			User user = loginModel.getUser();
			JSONObject jsonObj = JSONObject.fromObject(content);
			String type = null;
			// 分页对象
			Page page = getPage(jsonObj);
			// 服务端排序规则
			String orderGuize = getOrderGuize(JSONUtils.getStr(jsonObj, "orderGuize"));
			if(jsonObj.containsKey("type")){
				type = JSONUtils.getStr(jsonObj, "type");
			}
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			wc.setLength(page.getItemsperpage());
			wc.setOffset((page.getCurrentPage() - 1) * page.getItemsperpage());
			wc.setOrderBy(orderGuize);
			if(StringUtils.equals(user.getCategory_id(), "2")){
				wc.andEquals("user_id", user.getId());
			}
			if(StringUtils.equals(type, "2")){
				List<String> list2 = new ArrayList<String>();
				list2.add("2");
				wc.andIn("shenpiStatus", list2);
			}else if(StringUtils.equals(type, "1")){
				List<String> list2 = new ArrayList<String>();
				list2.add("0");
				list2.add("1");
				list2.add("3");
				list2.add("4");
				wc.andIn("shenpiStatus", list2);
			}else if(StringUtils.equals(type, "3")){
				List<String> list2 = new ArrayList<String>();
				list2.add("2");
				wc.andIn("shenpiStatus", list2);
				wc.andEquals("jielun", "2");
			}
			initWanNengChaXun(jsonObj, wc);// 万能查询
			//searchByDate(jsonObj, wc);//按日期查询
			//topSearch(jsonObj, wc);//头部查询
			//topSearchYN(jsonObj,wc);
			//permissionFilter(jsonObj, wc, request, response);//权限验证
			
			List list = orderService.query(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);
			page.setTotalItems(orderService.count(wc));
			Map map = new HashMap();
			map.put("page", page);
			map.put("list", ja);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response,s );
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/ngres/dingdanguanli/order/list2")
	public String execute1343(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			//JSONObject jsonObj = JSONObject.fromObject(content);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String time = dateFormat.format(new Date());
			String time1 = LanDateUtils.getNext_Day(time, -1);
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			wc.andGreaterEquals("addtime", time1);
			wc.andLessEquals("addtime", time);
			//initWanNengChaXun(jsonObj, wc);// 万能查询
			//searchByDate(jsonObj, wc);//按日期查询
			//topSearch(jsonObj, wc);//头部查询
			//topSearchYN(jsonObj,wc);
			//permissionFilter(jsonObj, wc, request, response);//权限验证
			
			List list = orderService.query(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);

			Map map = new HashMap();
			map.put("list", ja);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response,s );
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/ngres/dingdanguanli/order/list1")
	public String execute123(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(content);
			List<Repetition> list1 = new ArrayList<Repetition>();//身份证号码+姓名重复
			List<Repetition> list2 = new ArrayList<Repetition>();//本人手机号码重复
			List<Repetition> list3 = new ArrayList<Repetition>();//单位名称重复
			List<Repetition> list4 = new ArrayList<Repetition>();//4个联系人手机号码重复
			List<Repetition> list5 = new ArrayList<Repetition>();//4个联系人姓名+关系
			List<Repetition> list6 = new ArrayList<Repetition>();//单位座机重复
			String id = jsonObject.getString("id");
			Order order = orderService.loadById(id);
			ChaChong c = new ChaChong();
			WhereCondition wc = new WhereCondition();
			List<Order> lists = orderService.query(wc);
			for(Order order2:lists){
				if(!StringUtils.equals(order.getId(), order2.getId())){
					if(StringUtils.equals(order.getIdcard(), order2.getIdcard())&&StringUtils.equals(order.getRealname(), order2.getRealname())){
						Repetition r = new Repetition();
						r.setId(order2.getId());
						r.setName(order2.getRealname());
						r.setAddtime(order2.getAddtime());
						list1.add(r);
						c.setIdcard(order.getIdcard());
						c.setName(order.getRealname());
					}
				}
			}
			for(Order order2:lists){
				if(!StringUtils.equals(order.getId(), order2.getId())){
					if(StringUtils.equals(order.getCellphone(), order2.getCellphone())){
						Repetition r = new Repetition();
						r.setId(order2.getId());
						r.setName(order2.getRealname());
						r.setAddtime(order2.getAddtime());
						list2.add(r);
						c.setCellphone(order.getCellphone());
					}
				}
			}
			for(Order order2:lists){
				if(!StringUtils.equals(order.getId(), order2.getId())){
					if(StringUtils.equals(order.getUnit(), order2.getUnit())){
						Repetition r = new Repetition();
						r.setId(order2.getId());
						r.setName(order2.getRealname());
						r.setAddtime(order2.getAddtime());
						list3.add(r);
						c.setCompanyName(order.getUnit());
					}
				}
			}
			for(Order order2:lists){
				if(!StringUtils.equals(order.getId(), order2.getId())){
					if(StringUtils.equals(order.getFirst_cellphone(), order2.getFirst_cellphone())){
						Repetition r = new Repetition();
						r.setId(order2.getId());
						r.setName(order2.getRealname());
						r.setAddtime(order2.getAddtime());
						list4.add(r);
						c.setFirst_name1(order.getFirst_realname());
						c.setFirst_relation1(order.getFirst_relation());
						c.setFirst_phone1(order.getFirst_cellphone());
					}
					if(StringUtils.equals(order.getSecond_cellphone(), order2.getSecond_cellphone())){
						Repetition r = new Repetition();
						r.setId(order2.getId());
						r.setName(order2.getRealname());
						r.setAddtime(order2.getAddtime());
						list4.add(r);
						c.setSecond_name1(order.getSecond_realname());
						c.setSecond_relation1(order.getSecond_relation());
						c.setSecond_phone1(order.getSecond_cellphone());
					}
					if(StringUtils.equals(order.getThird_cellphone(), order2.getThird_cellphone())){
						Repetition r = new Repetition();
						r.setId(order2.getId());
						r.setName(order2.getRealname());
						r.setAddtime(order2.getAddtime());
						list4.add(r);
						c.setThird_name1(order.getThird_realname());
						c.setThird_phone1(order.getThird_cellphone());
						c.setThird_relation1(order.getThird_relation());
					}
					if(StringUtils.equals(order.getFourth_cellphone(), order2.getFourth_cellphone())){
						Repetition r = new Repetition();
						r.setId(order2.getId());
						r.setName(order2.getRealname());
						r.setAddtime(order2.getAddtime());
						list4.add(r);
						c.setFourth_name1(order.getFourth_realname());
						c.setFourth_relation1(order.getFourth_relation());
						c.setFourth_phone1(order.getFourth_cellphone());
					}
				}
			}
			for(Order order2:lists){
				if(!StringUtils.equals(order.getId(), order2.getId())){
					if((StringUtils.equals(order.getFirst_realname(), order2.getFirst_realname())&&StringUtils.equals(order.getFirst_relation(), order2.getFirst_relation()))){
						Repetition r = new Repetition();
						r.setId(order2.getId());
						r.setName(order2.getRealname());
						r.setAddtime(order2.getAddtime());
						list5.add(r);
						c.setFirst_name2(order.getFirst_realname());
						c.setFirst_relation2(order.getFirst_relation());
					}
					if((StringUtils.equals(order.getSecond_realname(), order2.getSecond_realname())&&StringUtils.equals(order.getSecond_relation(), order2.getSecond_relation()))){
							Repetition r = new Repetition();
							r.setId(order2.getId());
							r.setName(order2.getRealname());
							r.setAddtime(order2.getAddtime());
							list5.add(r);
							c.setSecond_name2(order.getSecond_realname());
							c.setSecond_relation2(order.getSecond_relation());
						}
					if((StringUtils.equals(order.getThird_realname(), order2.getThird_realname())&&StringUtils.equals(order.getThird_relation(), order2.getThird_relation()))){
							Repetition r = new Repetition();
							r.setId(order2.getId());
							r.setName(order2.getRealname());
							r.setAddtime(order2.getAddtime());
							list5.add(r);
							c.setThird_name2(order.getThird_realname());
							c.setThird_relation2(order.getThird_relation());
						}
					if((StringUtils.equals(order.getFourth_realname(), order2.getFourth_realname())&&StringUtils.equals(order.getFourth_relation(), order2.getFourth_relation()))){
							Repetition r = new Repetition();
							r.setId(order2.getId());
							r.setName(order2.getRealname());
							r.setAddtime(order2.getAddtime());
							list5.add(r);
							c.setFourth_name2(order.getFourth_realname());
							c.setFourth_relation2(order.getFourth_relation());
						}
				}
			}
			for(Order order2:lists){
				if(!StringUtils.equals(order.getId(), order2.getId())){
					if(StringUtils.equals(order.getUnitphone(), order2.getUnitphone())){
						Repetition r = new Repetition();
						r.setId(order2.getId());
						r.setName(order2.getRealname());
						r.setAddtime(order2.getAddtime());
						list6.add(r);
						c.setUnitphone(order.getUnitphone());
					}
				}
			}
			Map map = new HashMap();
			map.put("chachong", c);
			map.put("idnamelist", list1);
			map.put("phonelist", list2);
			map.put("unitlist", list3);
			map.put("fphonelist", list4);
			map.put("frenamelist", list5);
			map.put("uphonelist", list6);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response,s );
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
			e.printStackTrace();
		}
		return null;
	}
	
	
		@RequestMapping(value = "/ngres/dingdanguanli/order/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Order k =  orderService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
			e.printStackTrace();
		}
		return null;
	}
		
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/addgoodsinfo")
		public String executesd13a2(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
				try {
					
				} catch (Exception e) {
					AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
					e.printStackTrace();
				}
				return null;
			}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/add2")
		public String executesd111(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
			    JSONObject jsonObj = JSONObject.fromObject(content);
			    String id = sequenceManager.generateId("tc_member_loan");
			    String goodsmodel = jsonObj.getString("goodsmodel");
			    String goodsname = jsonObj.getString("goodsname");
			    String goodsprice = jsonObj.getString("goodsprice");
			    String firstpay = jsonObj.getString("firstpay");
			    String seller_note = jsonObj.getString("seller_note");
			    String sellercode = jsonObj.getString("sellercode");
				JSONObject jsonObj1 = JSONObject.fromObject(jsonObj).getJSONObject("monthnumber");
				String monthnumber1 = jsonObj1.getString("value");
				String monthnumber = monthnumber1.substring(0, 1);
				String merchant = jsonObj.getString("merchant");
				
				Order order = new Order();
				order.setId(id);
				order.setGoodsmodel(goodsmodel);
				order.setGoodsname(goodsname);
				order.setGoodsprice(Double.valueOf(goodsprice));
				order.setFirstpay(Double.valueOf(firstpay));
				order.setSeller_note(seller_note);
				order.setSellercode(sellercode);
				order.setMonthnumber(Integer.parseInt(monthnumber));
				order.setMerchant(merchant);
				orderService.insert(order);
				
				Map map = new HashMap();
				map.put("id", id);
				map.put("status","success");
            	AjaxUtils.ajaxJson(response, JSONObject.fromObject(map,jsonConfig).toString());
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/add")
		public String executesd132(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {	
				
				JSONObject jsonObj = JSONObject.fromObject(content);
				JSONObject object = jsonObj.getJSONObject("item");
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String realname = object.getString("realname");
					String idcard = object.getString("idcard");
					String cellphone = object.getString("cellphone");
					String token = HttpUtils2.getToken();
					String id = sequenceManager.generateId("tc_member_loan");
				{//大圣在线风控数据,火眼黑名单2.0数据
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("name", realname);
					params.put("idCard", idcard);
					params.put("phone", cellphone);
					params.put("timestamp", System.currentTimeMillis());
					HttpUtils2 httpUtils2 = new HttpUtils2();
					String s = RequestCloud.sendPost("https://api.dsdatas.com/d/dhbGetSauronC", JSONObject.fromObject(params).toString(), token);
					JSONObject jsonObject = JSONObject.fromObject(s);
					Onwindctr onwindctr = new Onwindctr();
					jsonObject.toBean(jsonObject.getJSONObject("res"), onwindctr, jsonConfig);
					
					Map<String, Object> params1 = new HashMap<String, Object>();
					params1.put("name", realname);
					params1.put("idCard", idcard);
					params1.put("mobile", cellphone);
					params1.put("timestamp", System.currentTimeMillis());
					String s1 = RequestCloud.sendPost("https://api.dsdatas.com/blackData/fireEyesBlackv2", JSONObject.fromObject(params1).toString(), token);
					JSONObject jsonObject2 = JSONObject.fromObject(s1);
					JSONObject jsonObject3 = jsonObject2.getJSONObject("res");
					String result = jsonObject3.getString("result");
					String status = jsonObject3.getString("status");
					
					if(StringUtils.isEmpty(onwindctr.getId())){
						String id1 = sequenceManager.generateId("onwindctr");
						onwindctr.setId(id1);
						onwindctr.setOrder_id(id);
						onwindctr.setAddTime(dateFormat.format(new Date()));
						onwindctr.setResult(result);
						onwindctr.setStatus(status);
						onwindctrService.insert(onwindctr);
					}
				}	
					Order order = new Order();
					order.setId(id);
					order.setUid(id);
					order.setAddtime(dateFormat.format(new Date()));
					order.setRealname(realname);
					order.setIdcard(idcard);
					order.setCellphone(cellphone);
					order.setShenpiStatus("0");
					order.setIsWind("0");
					orderService.insert(order);
					model.put("status", "success");
					model.put("id", "id");
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		public String executesd112(ModelMap model, @RequestBody String content,String id,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
			    String name = jsonObj.getString("name");
				JSONObject jsonObj1 = JSONObject.fromObject(jsonObj).getJSONObject("sex");
				String sex = jsonObj1.getString("value");
				String age = jsonObj.getString("age");
				System.out.println(age);
			    String idcard = jsonObj.getString("idcard");
			    String phone = jsonObj.getString("phone");
			    String email = jsonObj.getString("email");
			    JSONObject jsonObj2 = JSONObject.fromObject(jsonObj).getJSONObject("marriage");
			    String marriage = jsonObj2.getString("value");
			    JSONObject jsonObj3 = JSONObject.fromObject(jsonObj).getJSONObject("education");
			    String education = jsonObj3.getString("value"); 
			    String birth = jsonObj.getString("birth"); //户口所在地
			    String address = jsonObj.getString("address"); //现居地
			    String live = jsonObj.getString("live");
			    JSONObject jsonObj4 = JSONObject.fromObject(jsonObj).getJSONObject("bank");
			    String bank_id = jsonObj4.getString("id");
			    String bank_name = jsonObj4.getString("name");
			    String bank_address = jsonObj4.getString("address");
			    
			    Order order = new Order();
			    order.setId(id);
			    order.setRealname(name);
			    order.setSex(sex);
			    order.setAge(age);
			    order.setIdcard(idcard);
			    order.setCellphone(phone);
			    order.setEmail(email);
			    order.setMarriage(marriage);
			    order.setEducation(education);
			    order.setBirth(birth);
			    order.setAddress(address);
			    order.setLive(live);
			    order.setBank_id(bank_id);
			    order.setBank_name(bank_name);
			    order.setBank_address(bank_address);
			    orderService.update(order);
			    
			    Map map = new HashMap();
				map.put("id", id);
				map.put("status","success");
            	AjaxUtils.ajaxJson(response, JSONObject.fromObject(map,jsonConfig).toString());
			    
				String status="error";
				Map<String, Object> params = new HashMap<String, Object>();
		    	params.put("channel_type", "YYS");
		    	params.put("channel_code", "100000");
		    	params.put("real_name", order.getRealname());
		    	params.put("identity_code", order.getIdcard());
		    	params.put("user_mobile", order.getCellphone());
		    	//params.put("task_id", "TASKYYS100000201805241406310720980426");
		    	String s = HttpUtils.doPost("https://api.shujumohe.com/octopus/task.unify.create/v3?partner_code=chaorendai_mohe&partner_key=53675f6abacb44319d50d7f84a2d5a85", params);
				JSONObject jsonObject = JSONObject.fromObject(s, jsonConfig);
		    	String code = jsonObject.getString("code");
		    	String message = jsonObject.getString("message");
		    	if(StringUtils.equals("0", code)){
		    		status="success";
		    		String task_id = jsonObject.getString("task_id");
		    		order.setTongdun_task_id(task_id);
		    		orderService.update(order);
		    	}else{
		    		status="error";
		    	}
				model.put("status", status);
				model.put("message", message);
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/register")
		public String executesd11213(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				String id = sequenceManager.generateId("customer");
			    String phone = jsonObj.getString("phone");
			    String password = jsonObj.getString("password");
			   
			    Customer customer = new Customer();
			    customer.setId(id);
			    customer.setLogin_name(phone);
			    customer.setLogin_password(password);
			    customerService.insert(customer);
			    
				
				
				
				
			    Map map = new HashMap();
				map.put("id", id);
				map.put("status","success");
            	AjaxUtils.ajaxJson(response, JSONObject.fromObject(map,jsonConfig).toString());

			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		
		
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/add11")
		public String executesd1121(ModelMap model, @RequestBody String content,String id,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
			    String name = jsonObj.getString("name"); //单位名称
			    String address = jsonObj.getString("address"); //单位地址
			    JSONObject jsonObj2 = JSONObject.fromObject(jsonObj).getJSONObject("size");
			    String size = jsonObj2.getString("value"); //公司规模
			    JSONObject jsonObj3 = JSONObject.fromObject(jsonObj).getJSONObject("type");
			    String type = jsonObj2.getString("value"); //公司性质
			    String jobTime = jsonObj.getString("jobTime"); //工作年限
			    String division = jsonObj.getString("division"); //所在的部门
                String job = jsonObj.getString("job"); //职位
                String money = jsonObj.getString("money"); //月收入
                String tell = jsonObj.getString("tell"); //单位座机
                String jobStart = jsonObj.getString("jobStart");
            
                Order order = new Order();
                order.setId(id);
                order.setUnit(name);
                order.setUnitaddress(address);
                order.setUnit_size(size);
                order.setUnit_type(type);
                order.setWork_life(jobTime);
                order.setDepartment(division);
                order.setPosition(job);
                order.setMonth_incomes(money);
                order.setUnitphone(tell);
                order.setEntry_time(jobStart);
			    orderService.update(order);
			    
			    Map map = new HashMap();
				map.put("id", id);
				map.put("status","success");
            	AjaxUtils.ajaxJson(response, JSONObject.fromObject(map,jsonConfig).toString());

			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/add111")
		public String executesd11211(ModelMap model, @RequestBody String content,String id,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				JSONObject jsonObj1 = JSONObject.fromObject(jsonObj).getJSONObject("spouse");
				String spouse_name = jsonObj1.getString("name"); //配偶姓名
				String spouse_phone = jsonObj1.getString("phone"); //配偶电话
				JSONObject jsonObj2 = JSONObject.fromObject(jsonObj).getJSONObject("family");
				String family_name = jsonObj2.getString("name"); //亲属姓名
				String family_phone = jsonObj2.getString("phone"); //亲属电话
				JSONObject jsonObj3 = JSONObject.fromObject(jsonObj).getJSONObject("colleague");
				String colleague_name = jsonObj3.getString("name"); //同事姓名
				String colleague_phone = jsonObj3.getString("phone"); //同事电话
				JSONObject jsonObj4 = JSONObject.fromObject(jsonObj).getJSONObject("friend");
				String friend_name = jsonObj4.getString("name"); //同事姓名
				String friend_phone = jsonObj4.getString("phone"); //同事电话
				
                Order order = new Order();
                order.setId(id);
                //第一联系人为其配偶
                order.setFirst_realname(spouse_name);
                order.setFirst_cellphone(spouse_phone);
                //第二联系人为其亲属
                order.setSecond_realname(family_name);
                order.setSecond_cellphone(family_phone);
                //第三联系人为其同事
                order.setThird_realname(colleague_name);
                order.setThird_cellphone(colleague_phone);
                //第四联系人为其朋友
                order.setFourth_realname(friend_name);
                order.setFourth_cellphone(friend_phone);
                orderService.update(order); 
                
            
			    
			    Map map = new HashMap();
				map.put("id", id);
				map.put("status","success");
            	AjaxUtils.ajaxJson(response, JSONObject.fromObject(map,jsonConfig).toString());

			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		@RequestMapping(value="/ngres/dingdanguanli/order/multiUpload")
		public String multiFileUpload(ModelMap model, @RequestBody String content,String id,
				HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException{
		try{
			
			JSONObject jsonObj = JSONObject.fromObject(content);
			String idcardLeft = jsonObj.getString("idcardLeft");
			String id1 = sequenceManager.generateId("filerecord");
			Filerecord filerecord = new Filerecord();
			filerecord.setId(id1);
			filerecord.setRefid(id);
			filerecord.setLeixing("1");
			filerecord.setHouzhuiming(".png");
			filerecordService.insert(filerecord);
			String fullpath = "D:\\picture\\"+id1;
			String idcardLeftPath = FileController.GenerateImage(idcardLeft.substring(23), fullpath);
			
			String idcardRight = jsonObj.getString("idcardLeft");
			String id2 = sequenceManager.generateId("filerecord");
			Filerecord filerecord1 = new Filerecord();
			filerecord1.setId(id2);
			filerecord1.setRefid(id);
			filerecord1.setLeixing("2");
			filerecord1.setHouzhuiming(".png");
			filerecordService.insert(filerecord1);
			String fullpath1 = "D:\\picture\\"+id2;
			String idcardRightPath = FileController.GenerateImage(idcardRight.substring(23), fullpath1); 
			
			String oneselfLeft = jsonObj.getString("oneselfLeft");
			String id3 = sequenceManager.generateId("filerecord");
			Filerecord filerecord2 = new Filerecord();
			filerecord2.setId(id3);
			filerecord2.setRefid(id);
			filerecord2.setLeixing("3");
			filerecord2.setHouzhuiming(".png");
			filerecordService.insert(filerecord2);
			String fullpath2 = "D:\\picture\\"+id3;
			String oneselfLeftPath = FileController.GenerateImage(oneselfLeft.substring(23), fullpath2);    
			
			String oneselfRight = jsonObj.getString("oneselfRight");
			String id4 = sequenceManager.generateId("filerecord");
			Filerecord filerecord3 = new Filerecord();
			filerecord3.setId(id4);
			filerecord3.setRefid(id);
			filerecord3.setLeixing("4");
			filerecord3.setHouzhuiming(".png");
			filerecordService.insert(filerecord3);
			String fullpath3 = "D:\\picture\\"+id4;
			String oneselfRightPath = FileController.GenerateImage(oneselfRight.substring(23), fullpath3); 
			
			Order order = new Order();
			order.setId(id);
			order.setIdcardLeftPath(idcardLeftPath);
			order.setIdcardRightPath(idcardRightPath); 
			order.setOneselfLeftPath(oneselfLeftPath);
			order.setOneselfRightPath(oneselfRightPath);
		    orderService.update(order);
			
		    Map map = new HashMap();
			map.put("id", id);
			map.put("status","success");
        	AjaxUtils.ajaxJson(response, JSONObject.fromObject(map,jsonConfig).toString());
			

		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
			e.printStackTrace();
		}
		return null;
			
			
			
			
			
		
		}
		
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/tiaozhuan")
		public String executesd1312(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			return "/weixin/loan_info";
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/tiaozhuan1")
		public String executesd13121(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			return "/weixin/personal_info";
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/tiaozhuan2")
		public String executesd131211(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			return "/weixin/work_info";
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/tiaozhuan3")
		public String executesd1312111(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			return "/weixin/contact_info";
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/tiaozhuan4")
		public String executesd13121111(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			return "/weixin/update";
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/tiaozhuan5")
		public String executesd131211111(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			return "/weixin/applicatio_success";
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/tiaozhuan6")
		public String executesd13121111111(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			return "/weixin/regist";
		}
		@RequestMapping(value = "/ngres/dingdanguanli/order/lunxun")
		public String executesd1112(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				JSONObject object = jsonObj.getJSONObject("item");
				String id = object.getString("id");
				String task_id = object.getString("task_id");
				String service_pass = object.getString("service_pass");
				String status="success";
				Order order = orderService.loadById(id);
	    		Map<String, Object> params = new HashMap<String, Object>();
	        	params.put("task_id", task_id);
	        	//params.put("channel_code", "100000");
	        	//params.put("real_name", "徐少华");
	        	//params.put("identity_code", "341224198703012321"); 	
	        	params.put("user_name", order.getCellphone());
	        	params.put("user_pass", service_pass);
	        	params.put("task_stage", "INIT");
	        	params.put("request_type", "submit");
	        	//params.put("task_id", "TASKYYS100000201805241406310720980426");
	        	String s1 = HttpUtils.doPost("https://api.shujumohe.com/octopus/yys.unify.acquire/v3?partner_code=chaorendai_mohe&partner_key=53675f6abacb44319d50d7f84a2d5a85", params);
	    		JSONObject jsonObject2 = JSONObject.fromObject(s1, jsonConfig);
	    		String code1 = jsonObject2.getString("code");
	    		String message1 = jsonObject2.getString("message");
	    		if(StringUtils.equals(code1, "101")||StringUtils.equals(code1, "123")){//跳转图片验证码页面
	    			JSONObject data = jsonObject2.getJSONObject("data");
	    			String auth_code = data.getString("auth_code");
	    			model.put("service_pass", service_pass);
	    			model.put("task_id", task_id);
	    			model.put("id", id);
	    			model.put("auth_code", auth_code);
	    			model.put("status", status);
	    			return "";
	    		}else if(StringUtils.equals(code1, "105")){//跳转手机验证码输入页面
	    			model.put("service_pass", service_pass);
	    			model.put("task_id", task_id);
	    			model.put("id", id);
	    			model.put("status", status);
	    			return "";
	    		}else if(StringUtils.equals(code1, "100")){//继续轮询
	    			return null;
	    		}else{
	    			status="error";
	    			model.put("status", status);
	    			model.put("message", message1);
	    			}
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/callback")//回调函数
		public String executesd1132(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				String notify_type=request.getParameter("notify_type");
				String notify_event = request.getParameter("notify_event");
				String notify_data = request.getParameter("notify_data");
				Order order = new Order();
				if(StringUtils.equals("ACQUIRE", notify_type)){
					JSONObject jsonObject = JSONObject.fromObject(notify_data);
					String task_id = jsonObject.getString("task_id");
					WhereCondition wc = new WhereCondition();
					wc.andEquals("tongdun_task_id", task_id);
					List<Order> list = orderService.query(wc);
					if(CollectionUtils.isNotEmpty(list)){
						order = list.get(0);
					}
					if(StringUtils.equals("CREATED", notify_event)){
						order.setTask_status("0");
					}else if(StringUtils.equals("SUCCESS", notify_event)){
						order.setTask_status("1");
					}else if(StringUtils.equals("FAILURE", notify_event)){
						order.setTask_status("2");
					}else if(StringUtils.equals("TIMEOUT", notify_event)){
						order.setTask_status("3");
					}
					orderService.update(order);
				}
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		public void storagedata(String task_id,String order_id){
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String, Object> params = new HashMap<String, Object>();
	        	params.put("task_id", task_id);
	        	String s1 = HttpUtils.doPost("https://api.shujumohe.com/octopus/task.unify.query/v4?partner_code=chaorendai_mohe&partner_key=53675f6abacb44319d50d7f84a2d5a85", params);
	    		JSONObject jsonObject2 = JSONObject.fromObject(s1);
	    		String code = jsonObject2.getString("code");
	    		if(StringUtils.equals(code, "0")){
	    			if(jsonObject2.containsKey("data")){
	    				JSONObject object = jsonObject2.getJSONObject("data");
	    				if(object.containsKey("task_data")){
	    					JSONObject jsonObject = object.getJSONObject("task_data");
	    					if(jsonObject.containsKey("base_info")){//运营商个人信息
	    						JSONObject jsonObject3 = jsonObject.getJSONObject("base_info");
	    						Base_info base_info = new Base_info();
	    						String id = sequenceManager.generateId("base_info");
	    						JSONObject.toBean(jsonObject3, Base_info.class);
	    						base_info.setId(id);
	    						base_info.setOrder_id(order_id);
	    						base_info.setAddTime(dateFormat.format(new Date()));
	    						base_infoService.insert(base_info);
	    					}
	    					if(jsonObject.containsKey("account_info")){//运营商账户信息
	    						JSONObject jsonObject3 = jsonObject.getJSONObject("account_info");
	    						Account_info account_info = new Account_info();
	    						String id = sequenceManager.generateId("account_info");
	    						JSONObject.toBean(jsonObject3, Account_info.class);
	    						account_info.setId(id);
	    						account_info.setOrder_id(order_id);
	    						account_info.setAddTime(dateFormat.format(new Date()));
	    						account_infoService.insert(account_info);
	    					}
	    					if(jsonObject.containsKey("call_info")){//运营商通话详单
	    						JSONObject jsonObject3 = jsonObject.getJSONObject("call_info");
	    						JSONArray jsonArray = jsonObject3.getJSONArray("call_record");
	    						for(int i =0;i<jsonArray.size();i++){
	    							Call_info call_info = new Call_info();
	    							String id = sequenceManager.generateId("call_info");
	    							JSONObject.toBean(jsonArray.getJSONObject(i), Call_info.class);
	    							call_info.setId(id);
	    							call_info.setOrder_id(order_id);
	    							call_info.setAddTime(dateFormat.format(new Date()));
	    							call_infoService.insert(call_info);
	    						}
	    					}
	    					if(jsonObject.containsKey("bill_info")){//运营商账单信息
	    						JSONObject jsonObject3 = jsonObject.getJSONObject("bill_info");
	    						JSONArray jsonArray = jsonObject3.getJSONArray("bill_record");
	    						for(int i =0;i<jsonArray.size();i++){
	    							Bill_info bill_info = new Bill_info();
	    							String id = sequenceManager.generateId("bill_info");
	    							JSONObject.toBean(jsonArray.getJSONObject(i), Bill_info.class);
	    							bill_info.setId(id);
	    							bill_info.setOrder_id(order_id);
	    							bill_info.setAddTime(dateFormat.format(new Date()));
	    							bill_infoService.insert(bill_info);
	    						}
	    					}
	    					if(jsonObject.containsKey("family_info")){//运营商亲情网信息
	    						JSONArray jsonArray = jsonObject.getJSONArray("family_info");
	    						for(int i =0;i<jsonArray.size();i++){
	    							Family_info family_info = new Family_info();
	    							String id = sequenceManager.generateId("family_info");
	    							JSONObject.toBean(jsonArray.getJSONObject(i), Family_info.class);
	    							family_info.setId(id);
	    							family_info.setOrder_id(order_id);
	    							family_info.setAddTime(dateFormat.format(new Date()));
	    							family_infoService.insert(family_info);
	    						}
	    					}
	    				}
	    			}
	    		}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/list/jiegua")
		public String executesd134(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				JSONObject job = jsonObj.getJSONObject("item");
				Order p = new Order();
				Order p1 = new Order();
				JSONObject.toBean(job, p, jsonConfig);
				if(StringUtils.isNotEmpty(p.getId())){
					p1 = orderService.loadById(p.getId());
					p1.setShenpiStatus("1");
				}
				orderService.update(p1);
				AjaxUtils.ajaxJsonSuccessMessage(response, "修改成功!");
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/list/zhuanyi")
		public String executesd1332(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				JSONObject job = jsonObj.getJSONObject("item");
				String user_id=job.getString("user_id");
				String user_name=job.getString("user_name");
				JSONArray arrays = job.getJSONArray("list");
				for(int i=0;i<arrays.size();i++){
					Order p = new Order();
					JSONObject.toBean(arrays.getJSONObject(i), p, jsonConfig);
					p.setUser_id(user_id);
					p.setUser_name(user_name);
					orderService.update(p);
				}
				AjaxUtils.ajaxJsonSuccessMessage(response, "修改成功!");
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/list/id1")
		public String executesd13(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				Order k =  orderService.loadById(JSONUtils.getStr(jsonObj, "id"));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				WhereCondition wc = new WhereCondition();
				wc.andEquals("uid", k.getUid());
				List<Payinfo> lists = payinfoService.query(wc);
				for(Payinfo payinfo :lists){
					//System.out.println(payinfo.getOverdue());
					if(!StringUtils.equals(payinfo.getOverdue(), "0.00")&&!StringUtils.equals(payinfo.getStatus(), "1")){
						Date d1 = df.parse(df.format(new Date()));  
					      Date d2 = df.parse(payinfo.getRepayment_time());  
					      long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别  
					      long days = diff / (1000 * 60 * 60 * 24);
					      payinfo.setOverdays(String.valueOf(days));
					}
				}
				Map map = new HashMap();
				map.put("item", k);
				map.put("paylist", lists);
				JSONObject jo= JSONObject.fromObject(map,jsonConfig);
				AjaxUtils.ajaxJson(response, jo.toString());
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/list/online")
		public String executesd1342(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				String id = jsonObj.getString("id");
				WhereCondition wc = new WhereCondition();
				wc.andEquals("order_id", id);
				List<Onwindctr> lists = onwindctrService.query(wc);
				Onwindctr k = new Onwindctr();
				if(!CollectionUtils.isEmpty(lists)){
					k= lists.get(0);
				}
				Map map = new HashMap();
				map.put("item", k);
				JSONObject jo= JSONObject.fromObject(map,jsonConfig);
				AjaxUtils.ajaxJson(response, jo.toString());
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/list/tellog")
		public String executesd1352(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				String id = jsonObj.getString("id");
				Order order = orderService.loadById(id);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				WhereCondition wc = new WhereCondition();
				wc.andEquals("order_id", order.getId());
				List<Tellog> lists = tellogService.query(wc);
				Tellog tellog = new Tellog();
				if(lists.size()<=0){
					
					//三网手机实名制
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("key", "507e2a29e675797adaa0cb6834967707");
					params.put("realname", URLEncoder.encode(order.getRealname(), "UTF-8"));
					params.put("idcard", order.getIdcard());
					params.put("mobile", order.getCellphone());
					String s = HttpUtils.doPost("http://v.juhe.cn/telecom/query", params);
					JSONObject jsonObject = JSONObject.fromObject(s);
					JSONObject jsonObject2 = jsonObject.getJSONObject("result");
					
					//手机在网状态
					Map<String, Object> params1 = new HashMap<String, Object>();
					params1.put("key", "a749d5e10ddbbf4332071c9824dc8759");
					params1.put("mobile", order.getCellphone());
					String s1 = HttpUtils.doPost("http://v.juhe.cn/mobile_status/query", params1);
					JSONObject jsonObject1 = JSONObject.fromObject(s1);
					JSONObject jsonObject21 = jsonObject1.getJSONObject("result");
					
					//手机在网时长
					Map<String, Object> params2 = new HashMap<String, Object>();
					params2.put("key", "99d1e67c2f3452246db18b2d458c7b9b");
					params2.put("mobile", order.getCellphone());
					String s2 = HttpUtils.doPost("http://v.juhe.cn/mobileOnline/query", params2);
					JSONObject jsonObject23 = JSONObject.fromObject(s2);
					JSONObject jsonObject22 = jsonObject23.getJSONObject("result");
					String code = jsonObject22.getString("code");
					
					//手机消费档次
					Map<String, Object> params3 = new HashMap<String, Object>();
					params3.put("key", "d4d3718cf4c83e7b751ddfd063b9a898");
					params3.put("mobile", order.getCellphone());
					String s3 = HttpUtils.doPost("http://v.juhe.cn/mobile_consume/query", params3);
					JSONObject jsonObject24 = JSONObject.fromObject(s3);
					JSONObject jsonObject25 = jsonObject24.getJSONObject("result");
					String isp = jsonObject25.getString("isp");
					String code1 = jsonObject25.getString("code");
					
					String id1 = sequenceManager.generateId("tellog");
					tellog.setId(id1);
					tellog.setAddTime(dateFormat.format(new Date()));
					tellog.setRealSystem(jsonObject2.getString("resmsg"));
					tellog.setOnlinestate(jsonObject21.getString("state"));
					tellog.setOnlinedes(jsonObject21.getString("description"));
					if(StringUtils.equals("03", code)){
						tellog.setTimedes("0至3个月");
					}else if(StringUtils.equals("04", code)){
						tellog.setTimedes("3至6个月");
					}else if(StringUtils.equals("1", code)){
						tellog.setTimedes("6至12个月");
					}else if(StringUtils.equals("2", code)){
						tellog.setTimedes("1至2年");
					}else if(StringUtils.equals("3", code)){
						tellog.setTimedes("2年获2年以上");
					}else{
						tellog.setTimedes("T-1月前已离网");
					}
					tellog.setTimestate(code);
					if(StringUtils.equals("电信", isp)){
						if(StringUtils.equals("010", code1)){
							tellog.setConsume_desc("0至20元");
						}else if(StringUtils.equals("011", code1)){
							tellog.setConsume_desc("20至50元");
						}else if(StringUtils.equals("02", code1)){
							tellog.setConsume_desc("50至100元");
						}else if(StringUtils.equals("3", code1)){
							tellog.setConsume_desc("100至200元");
						}else{
							tellog.setConsume_desc("200元以上");
						}
					}else if(StringUtils.equals("移动", isp)){
						if(StringUtils.equals("01", code1)){
							tellog.setConsume_desc("0至50元");
						}else if(StringUtils.equals("02", code1)){
							tellog.setConsume_desc("50至100元");
						}else if(StringUtils.equals("3", code1)){
							tellog.setConsume_desc("100至200元");
						}else{
							tellog.setConsume_desc("200元以上");
						}
					}else if(StringUtils.equals("联通", isp)){
						if(StringUtils.equals("001", code1)){
							tellog.setConsume_desc("0至40元");
						}else if(StringUtils.equals("002", code1)){
							tellog.setConsume_desc("40至80元");
						}else if(StringUtils.equals("003", code1)){
							tellog.setConsume_desc("80至160元");
						}else{
							tellog.setConsume_desc("160元以上");
						}
					}
					tellog.setConsume_code(code1);
					tellog.setConsume_isp(isp);
					tellog.setOrder_id(order.getId());
					tellogService.insert(tellog);
					Order order2 = new Order();
					order2.setId(order.getId());
					order2.setIsWind("1");
					orderService.update(order2);
				}else{
					tellog = lists.get(0);
				}
				JSONObject jo= JSONObject.fromObject(tellog,jsonConfig);
				AjaxUtils.ajaxJson(response, jo.toString());
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/ngres/dingdanguanli/order/tongji")
		public String executesd133(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				
				JSONObject jsonObject = JSONObject.fromObject(content, jsonConfig);
				String time1 = jsonObject.getString("time");
				String type = jsonObject.getString("type");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				 List<String> times= DateTools.getAllDaysMonthByDate(dateFormat.parse(time1));
				 List<TongJi> lists = new ArrayList<TongJi>();
				 List<Time> times2 = new ArrayList<Time>();
 				 if(StringUtils.equals(type, "1")){
				 for(String time:times){
					 WhereCondition wc = new WhereCondition();
					 wc.andEquals("tongguoTime", time);
					 wc.andEquals("jielun", "2");
					 
					 TongJi tongji = orderService.tongji(wc);
					 if(tongji==null){
						 TongJi tongJi2 = new TongJi();
						 tongJi2.setRiqi(time.substring(5, time.length()));
						 tongJi2.setJine("0");
						 tongJi2.setAmount("0");
						 lists.add(tongJi2);
					 }else{
						 tongji.setRiqi(time.substring(5, time.length()));
						 lists.add(tongji);
					 } 
				 }
			}else if(StringUtils.equals(type,"2")){
				String startTime = times.get(0);
				String endTime = times.get(times.size()-1);
				String firsttime = dateFormat.format(LanDateUtils.getNext_Day(dateFormat.parse(startTime), 7));//第一周结束时间
				String secondtime = dateFormat.format(LanDateUtils.getNext_Day(dateFormat.parse(firsttime), 7));//第二周结束时间
				String thirdtime = dateFormat.format(LanDateUtils.getNext_Day(dateFormat.parse(secondtime), 7));//第三周结束时间
				String fourthtime = dateFormat.format(LanDateUtils.getNext_Day(dateFormat.parse(thirdtime), 7));//第四周结束时间
				WhereCondition wc1 = new WhereCondition();
				wc1.andGreaterEquals("tongguoTime", startTime);
				wc1.andLessEquals("tongguoTime", firsttime);
				wc1.andEquals("jielun", "2");
				TongJi tongJi1 = orderService.tongji1(wc1);
				if(tongJi1==null){
					 TongJi tongJi22 = new TongJi();
					 tongJi22.setRiqi("第一周");
					 tongJi22.setJine("0");
					 tongJi22.setAmount("0");
					 lists.add(tongJi22);
				 }else{
					 tongJi1.setRiqi("第一周");
					 if(tongJi1.getJine()==null){
						 tongJi1.setJine("0");
					 }
					 lists.add(tongJi1);
				 }
				WhereCondition wc2 = new WhereCondition();
				wc2.andGreaterThan("tongguoTime", firsttime);
				wc2.andLessEquals("tongguoTime", secondtime);
				wc2.andEquals("jielun", "2");
				TongJi tongJi2 = orderService.tongji1(wc2);
				if(tongJi2==null){
					 TongJi tongJi22 = new TongJi();
					 tongJi22.setRiqi("第二周");
					 tongJi22.setJine("0");
					 tongJi22.setAmount("0");
					 lists.add(tongJi22);
				 }else{
					 tongJi2.setRiqi("第二周");
					 if(tongJi2.getJine()==null){
						 tongJi2.setJine("0");
					 }
					 lists.add(tongJi2);
				 }
				WhereCondition wc3 = new WhereCondition();
				wc3.andGreaterThan("tongguoTime", secondtime);
				wc3.andLessEquals("tongguoTime", thirdtime);
				wc3.andEquals("jielun", "2");
				TongJi tongJi3 = orderService.tongji1(wc3);
				if(tongJi3==null){
					 TongJi tongJi22 = new TongJi();
					 tongJi22.setRiqi("第三周");
					 tongJi22.setJine("0");
					 tongJi22.setAmount("0");
					 lists.add(tongJi22);
				 }else{
					 tongJi3.setRiqi("第三周");
					 if(tongJi3.getJine()==null){
						 tongJi3.setJine("0");
					 }
					 lists.add(tongJi3);
				 }
				WhereCondition wc4 = new WhereCondition();
				wc4.andGreaterThan("tongguoTime", thirdtime);
				wc4.andLessEquals("tongguoTime", fourthtime);
				wc4.andEquals("jielun", "2");
				TongJi tongJi4 = orderService.tongji1(wc4);
				if(tongJi4==null){
					 TongJi tongJi22 = new TongJi();
					 tongJi22.setRiqi("第四周");
					 tongJi22.setJine("0");
					 tongJi22.setAmount("0");
					 lists.add(tongJi22);
				 }else{
					 tongJi4.setRiqi("第四周");
					 if(tongJi4.getJine()==null){
						 tongJi4.setJine("0");
					 }
					 lists.add(tongJi4);
				 }
				int num = LanDateUtils.compare_date(fourthtime, endTime);
				if(num==-1){
					WhereCondition wc5 = new WhereCondition();
					wc5.andGreaterThan("tongguoTime", fourthtime);
					wc5.andLessEquals("tongguoTime", endTime);
					wc5.andEquals("jielun", "2");
					TongJi tongJi5 = orderService.tongji1(wc5);
					if(tongJi5==null){
						 TongJi tongJi22 = new TongJi();
						 tongJi22.setRiqi("第五周");
						 tongJi22.setJine("0");
						 tongJi22.setAmount("0");
						 lists.add(tongJi22);
					 }else{
						 tongJi5.setRiqi("第五周");
						 if(tongJi5.getJine()==null){
							 tongJi5.setJine("0");
						 }
						 lists.add(tongJi5);
					 }
				}
			}else{
				String year = time1.substring(0,4);
				for(int i = 1;i<13;i++){
		    		String time = year+"-"+i+"-01";
		    		Date d = DateTools.paraseStringToDate(time);
		    		List<String> list = DateTools.getAllDaysMonthByDate(d);
		    		Time time2 = new Time();
		    		time2.setStartTime(list.get(0));
		    		time2.setEndTime(list.get(list.size()-1));
		    		time2.setMonth(i);
		    		times2.add(time2);
		    	}
				for(Time time:times2){
					WhereCondition wc = new WhereCondition();
					wc.andGreaterEquals("tongguoTime", time.getStartTime());
					wc.andLessEquals("tongguoTime", time.getEndTime());
					wc.andEquals("jielun", "2");
					TongJi tongJi = orderService.tongji1(wc);
					if(tongJi==null){
						 TongJi tongJi2 = new TongJi();
						 tongJi2.setRiqi(String.valueOf(time.getMonth())+"月");
						 tongJi2.setJine("0");
						 tongJi2.setAmount("0");
						 lists.add(tongJi2);
					 }else{
						 tongJi.setRiqi(String.valueOf(time.getMonth())+"月");
						 lists.add(tongJi);
					 } 
				}
			}
				Map map = new HashMap();
				map.put("tongjilist", lists);
				JSONObject jo= JSONObject.fromObject(map,jsonConfig);
				AjaxUtils.ajaxJson(response, jo.toString());
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}
		public static void main(String[] args) throws ParseException {
			String time = "2018-05-01";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date time1 = format.parse(time);
			Date time2 = LanDateUtils.getNext_Day(time1, 7);
			System.out.println(format.format(time2));
		}
}
