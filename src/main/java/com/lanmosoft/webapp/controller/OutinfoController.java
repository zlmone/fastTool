/**
 * auto generated
 * Copyright (C) 2013 lanmosoft.com, All rights reserved.
 */
package com.lanmosoft.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanmosoft.dao.model.Operationlog;
import com.lanmosoft.dao.model.Order;
import com.lanmosoft.dao.model.Outinfo;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.OperationlogService;
import com.lanmosoft.service.biz.OrderService;
import com.lanmosoft.service.biz.OutinfoService;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.webapp.controller.searchForm.Page;
import com.lanmosoft.webapp.webmodel.LoginModel;
import com.lanmosoft.enums.Enums.DelStatus;

/**
 * <p>
 * auto genegrated
 * </p>
 * <p>
 * Author: lanmo
 * </p>
 */
@Controller
public class OutinfoController extends BaseController {

	@Autowired
	OutinfoService outinfoService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OperationlogService operationlogService;
	
	@RequestMapping(value = "/ngres/waibuxinxi/outinfo/edit")
	public String execute(ModelMap model,final @RequestBody String content,
			String age,final HttpServletRequest request,final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
		   protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject job = jsonObj.getJSONObject("item");
			String action = null;
			if(job.containsKey("action")){
				action = job.getString("action");
			}
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Outinfo p = new Outinfo();
			JSONObject.toBean(job,p,jsonConfig);
			Order order = orderService.loadById(p.getOrder_id());
			LoginModel loginModel = (LoginModel) request.getSession().getAttribute("login");
			User user = loginModel.getUser();
			if (StringUtils.isNotEmpty(p.getId())) {
				outinfoService.update(p);
			} else {
				String id = sequenceManager.generateId("outinfo");
				p.setId(id);
				initCreate(p, request);
				outinfoService.insert(p);
			}
			Map map = new HashMap();
			map.put("status", "success");
			map.put("item", p);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response,s );
		} catch (Exception e) {// TODO
			arg0.setRollbackOnly();
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
			}
		});
		return null;
	}

	@RequestMapping(value = "/ngres/waibuxinxi/outinfo/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Outinfo> list = new ArrayList<Outinfo>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Outinfo p = new Outinfo();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Outinfo p : list) {
				ids.add(p.getId());
			}
			
			if(ids.size()!=0){
				WhereCondition wc = new WhereCondition();
				wc.andIn("id", ids);
				
				Outinfo p = new Outinfo();
				p.setDelstatus(DelStatus.REMOVED);//删除状态
				outinfoService.updateByCondition(wc, p);
				AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功!");
			}else{
				AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			}
			//outinfoService.deleteByCondition(wc);
			//AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/waibuxinxi/outinfo/list")
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
			initWanNengChaXuns(jsonObj, wc);// 万能查询
			//searchByDate(jsonObj, wc);//按日期查询
			//topSearch(jsonObj, wc);//头部查询
			//topSearchYN(jsonObj,wc);
			//permissionFilter(jsonObj, wc, request, response);//权限验证
			
			List list = outinfoService.query(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);
			page.setTotalItems(outinfoService.count(wc));
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
		@RequestMapping(value = "/ngres/waibuxinxi/outinfo/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Outinfo k = null;
			WhereCondition wc = new WhereCondition();
			wc.andEquals("order_id", JSONUtils.getStr(jsonObj, "id"));
			List<Outinfo> list =  outinfoService.query(wc);
			if(!CollectionUtils.isEmpty(list)){
				k=list.get(0);
			}
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
			e.printStackTrace();
		}
		return null;
	}
}
