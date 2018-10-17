/**
 * auto generated
 * Copyright (C) 2013 lanmosoft.com, All rights reserved.
 */
package com.lanmosoft.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanmosoft.dao.model.Filerecord;
import com.lanmosoft.dao.model.Order;
import com.lanmosoft.dao.model.Org;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.enums.Enums.DelStatus;
import com.lanmosoft.enums.Enums.UserType;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.OrderService;
import com.lanmosoft.service.biz.OrgService;
import com.lanmosoft.service.biz.UserService;
import com.lanmosoft.service.lanmobase.DateUtils;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.util.PasswdUtil;
import com.lanmosoft.webapp.controller.searchForm.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>
 * auto genegrated
 * </p>
 * <p>
 * Author: lanmo
 * </p>
 */
@Controller
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	OrgService orgService;
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/ngres/zuzhijiagou/user/edit")
	public String execute(ModelMap model, @RequestBody final String content,
			String age, final HttpServletRequest request, final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				String flag = "1";
				try {
					JSONObject jsonObj = JSONObject.fromObject(content);
					JSONObject job = jsonObj.getJSONObject("item");
					User p = new User();
					JSONObject.toBean(job,p,jsonConfig);
					if(StringUtils.isNotEmpty(p.getDepartment_id())){
						Org dept = orgService.loadById(p.getDepartment_id());
						p.setDepartment_name(dept.getName());
						p.setSuperOrg_id(dept.getSuperOrg_id());
						p.setSuperOrg_name(dept.getSuperOrg_name());
						p.setFulldept_id(dept.getFulldept_id());
						p.setFulldept_name(dept.getFulldept_name());
					}
					if(StringUtils.isNotEmpty(p.getPosition_id())){
						Org post = orgService.loadById(p.getPosition_id());
						p.setPosition_name(post.getName());
						p.setFullpost_id(post.getFullpost_id());
						p.setFullpost_name(post.getFullpost_name());
					}


					
					if (StringUtils.isNotEmpty(p.getId())) {
						/*if(StringUtils.isNotEmpty(JSONUtils.getStr(job, "newPassword"))){
						p.setPassword(PasswdUtil.encode(JSONUtils.getStr(job, "newPassword")));	
						}*/
						User loadById = userService.loadById(p.getId());
						if(!StringUtils.equals(loadById.getPassword(),p.getPassword())){
							p.setPassword(PasswdUtil.encode(p.getPassword()));	
						}
						p.setModifiedTime(DateUtils.getNowDate());
						userService.update(p);
					} else {
						//验证账户信息

						List<String> ids = new ArrayList<String>();
						String id = sequenceManager.generateId("user");
						//分配4个订单
						if(StringUtils.equals(p.getCategory_id(), "2")){//如果是信审人员
							WhereCondition wc = new WhereCondition();
							wc.andEquals("shenpiStatus", "0");
							wc.andIsNull("user_id");
							List<Order> lists = orderService.query(wc);
							if(lists.size()>4){
								List<Order> list2 = lists.subList(0, 4);
								for(Order order:list2){
									ids.add(order.getId());
								}
								Order order = new Order();
								order.setUser_id(id);
								order.setUser_name(p.getValue_name());
								WhereCondition condition = new WhereCondition();
								condition.andIn("id", ids);
								orderService.updateByCondition(condition, order);
							}else{
								for(Order order:lists){
									ids.add(order.getId());
								}
								Order order = new Order();
								order.setUser_id(id);
								order.setUser_name(p.getValue_name());
								WhereCondition condition = new WhereCondition();
								condition.andIn("id", ids);
								orderService.updateByCondition(condition, order);
							}
						}
						User user = getLogin(request).getUser();
						p.setId(id);
						p.setPassword(PasswdUtil.encode(p.getPassword()));
						p.setDelstatus(DelStatus.UNREMOVED);
						p.setCreatorId(user.getId());
						p.setCreatorName(user.getName());
						Date nowDate = DateUtils.getNowDate();
						p.setCreateTime(nowDate);
						p.setModifiedTime(nowDate);
//						p.setAccount_id(login.getAccount_id());
//						p.setAccount_name(login.getAccount_name());
						userService.insert(p);
					}
					
					String string = jsonObj.getString("status");
					
					/*JSONArray jsonArray = jsonObj.getJSONArray("list");
					for (int i = 0; i < jsonArray.size(); i++) {
						Woke_region wo = new Woke_region();
						JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),wo,jsonConfig);
						if(StringUtils.equals("0",wo.getId())){//新增
							String id = sequenceManager.generateId("worke");
							wo.setId(id);
							wo.setWork_id(p.getId());
							initCreate(wo, request);
							woke_regionService.insert(wo);
						}else{
							woke_regionService.update(wo);
						}
					}*/
					
				
					uploadImage(request, jsonObj, p);
					AjaxUtils.ajaxJsonSuccessMessage(response, "保存成功!");
				} catch (Exception e) {
					arg0.setRollbackOnly();
					AjaxUtils.ajaxJsonErrorMessage(response, "保存失败!");
					e.printStackTrace();
				}
			}
		});
		return null;
	}

	@RequestMapping(value = "/ngres/gongrenguanli/user/deletestatus")
	public String execute3a(ModelMap model, @RequestBody final String content,
			String age, HttpServletRequest request, final HttpServletResponse response) {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					try {
						JSONObject jsonObj = JSONObject.fromObject(content);
						JSONArray jsonArray=jsonObj.getJSONArray("selectedItems");
						String status = jsonObj.getString("status");
						
						JSONObject jsonObject = jsonObj.getJSONObject("item");
						User ps = new User();
						JSONObject.toBean(jsonObject,ps,jsonConfig);
						userService.update(ps);
						
					//	JSONArray jsonArray = JSONArray.fromObject(content);
					/*	List<Woke_region> list = new ArrayList<Woke_region>();
						for (int i = 0; i < jsonArray.size(); i++) {
							Woke_region p = new Woke_region();
							JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
							list.add(p);
						}
						List<String> ids = new ArrayList<String>();
						for (Woke_region p : list) {
							ids.add(p.getId());
						}
						WhereCondition wc = new WhereCondition();
						wc.andIn("id", ids);
						if(StringUtils.equals("0", status)){
							wc.setDelstatusValue(0);
						}else{
							wc.setDelstatusValue(1);
						}
						woke_regionService.deleteBystatus(wc);*/
						
						AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功！");
			
				} catch (Exception e) {
					arg0.setRollbackOnly();
					AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
					e.printStackTrace();
				}
			}
		});
		return  null;
	}
				
				
				
	
	@RequestMapping(value = "/ngres/zuzhijiagou/user/delete")
	public String execute3(ModelMap model, @RequestBody final String content,
			String age, HttpServletRequest request, final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				try {
					JSONArray jsonArray = JSONArray.fromObject(content);
					List<User> list = new ArrayList<User>();
					for (int i = 0; i < jsonArray.size(); i++) {
						User p = new User();
						JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
						list.add(p);
					}
					List<String> ids = new ArrayList<String>();
					for (User p : list) {
						ids.add(p.getId());
					}
					WhereCondition wc = new WhereCondition();
					wc.andIn("id", ids);
					userService.deleteByCondition(wc);
					AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功!");
				} catch (Exception e) {
					arg0.setRollbackOnly();
					AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
					e.printStackTrace();
				}
			}
		});
		return null;
	}

	@RequestMapping(value = "/ngres/zuzhijiagou/user/list")
	public String execute1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			// 分页对象
			Page page = getPage(jsonObj);
			// 服务端排序规则
			String orderGuize = getOrderGuize(JSONUtils.getStr(jsonObj, "orderGuize"));
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			wc.setLength(page.getItemsperpage());
			wc.setOffset((page.getCurrentPage() - 1) * page.getItemsperpage());
			wc.setOrderBy(orderGuize);
			
			
			
			//String string = jsonObj.getString("status");
			//if(StringUtils.equals("0", string)){  //正常 查询
				initWanNengChaXuns(jsonObj, wc);// 万能查询
				//wc.andNotEquals("category_id", UserType.SUPER);
				//wc.andEquals("org_code", getLogin(request).getUser().getOrg_code());
				
				permissionFilter(jsonObj, wc, request, response);
				
				List list = userService.query(wc);
				JSONArray ja = JSONArray.fromObject(list,jsonConfig);
				page.setTotalItems(userService.count(wc));
				
				Map map = new HashMap();
				map.put("page", page);
				map.put("list", ja);
				String s=JSONObject.fromObject(map,jsonConfig).toString();
				AjaxUtils.ajaxJson(response,s );
			//}
				/*else{   
				initWanNengChaXun(jsonObj, wc);// 万能查询
				String worker_type_id = jsonObj.getString("worker_type_id");//工序id
				String region_id = jsonObj.getString("region_id");//地区
				
				String qiyundi = jsonObj.getString("qiyundi");//起运地
				
				wc.setWorker_type_id(worker_type_id);
				wc.setRegion_id(region_id);
				wc.setQiyundi(qiyundi);
				
				List list = userService.query1(wc);
				JSONArray ja = JSONArray.fromObject(list,jsonConfig);
				page.setTotalItems(userService.count1(wc));
				Map map = new HashMap();
				map.put("page", page);
				map.put("list", ja);
				String s=JSONObject.fromObject(map,jsonConfig).toString();
				AjaxUtils.ajaxJson(response,s );
				
			}*/
			
		
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/ngres/zuzhijiagou/user/list1")
	public String execute11(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			
				initWanNengChaXun(jsonObj, wc);// 万能查询

				List list = userService.query(wc);
				JSONArray ja = JSONArray.fromObject(list,jsonConfig);
				
				Map map = new HashMap();
				map.put("list", ja);
				String s=JSONObject.fromObject(map,jsonConfig).toString();
				AjaxUtils.ajaxJson(response,s );
			
			
		
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	
		@RequestMapping(value = "/ngres/zuzhijiagou/user/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			User k =  userService.loadById(JSONUtils.getStr(jsonObj, "id"));
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("item", k);
			
			
			
			
			/*WhereCondition wc=new WhereCondition();
			wc.andEquals("work_id",k.getId());
			List<View_woke_region> query = view_woke_regionService.query(wc);
			
			List<Type> query2 = typeService.query(wc);
			map.put("list", query);
			map.put("typeList", query2);*/
		/*	String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response,s );*/
			
			if(StringUtils.isNotEmpty(k.getPic())){
				List<Filerecord>  filelist = new ArrayList<Filerecord>();
				Filerecord fileRecord = filerecordService.loadById(k.getPic());
				filelist.add(fileRecord);
				map.put("addson", JSONArray.fromObject(filelist));
			}
			AjaxUtils.ajaxJson(response, JSONObject.fromObject(map,jsonConfig).toString());
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "获取数据失败!");
			e.printStackTrace();
		}
		return null;
	}
		
		
		
		
	private void uploadImage(HttpServletRequest request, JSONObject jsonObj, User user) {
		try {
			JSONArray jaddson = jsonObj.getJSONArray("addson");
			List<String> idlist = new ArrayList<String>();
			if (StringUtils.isNotEmpty(user.getId())){
				WhereCondition wcff = new WhereCondition();
				wcff.andEquals("refid", user.getId()).andNotIn("id", idlist);
				filerecordService.deleteByCondition(wcff);
			}	
			if (jaddson != null && jaddson.size() > 0) {
				for (int i = 0; i < jaddson.size(); i++) {	
					String fileid = JSONUtils.getStr(jaddson.getJSONObject(i), "id");
					idlist.add(fileid);
					Filerecord fr=filerecordService.loadById(fileid);
					filerecordService.update(fr);
				}
			}
		} catch (Exception e) {
			
		}
		try {
			JSONArray ja= jsonObj.getJSONArray("files");
			if(ja!=null&&ja.size()>0){
				String sessionId=request.getSession().getId();
				for(int i=0;i<ja.size();i++){
					String uniqueIdentifier=JSONUtils.getStr(ja.getJSONObject(i), "uniqueIdentifier");
					WhereCondition wc = new WhereCondition();
					wc.andEquals("yuanshiid", sessionId+"_"+uniqueIdentifier);
					Filerecord fr=filerecordService.query(wc).get(0);
					fr.setRefid(user.getId());
					filerecordService.update(fr);
				}
			}
		} catch (Exception e) {
			
		}
		WhereCondition wcfile = new WhereCondition();
		wcfile.andEquals("refid", user.getId());
		wcfile.setOrderBy("createTime desc");
		List<Filerecord> fileRecordList = filerecordService.query(wcfile);
		if(CollectionUtils.isNotEmpty(fileRecordList)){
			user.setPic(fileRecordList.get(0).getId());
			userService.update(user);
		}
	}
}
