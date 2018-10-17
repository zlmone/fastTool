/**
 * auto generated
 * Copyright (C) 2013 lanmosoft.com, All rights reserved.
 */
package com.lanmosoft.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanmosoft.dao.model.Payinfo;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.OrderService;
import com.lanmosoft.service.biz.PayinfoService;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.webapp.controller.searchForm.Page;
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
public class PayinfoController extends BaseController {

	@Autowired
	PayinfoService payinfoService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/ngres/haikuanxinxi/payinfo/edit")
	public String execute(ModelMap model,final @RequestBody String content,
			String age,final HttpServletRequest request,final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
		   protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject job = jsonObj.getJSONObject("item");
			Payinfo p = new Payinfo();
			JSONObject.toBean(job,p,jsonConfig);
			if (StringUtils.isNotEmpty(p.getId())) {
				payinfoService.update(p);
			} else {
				String id = sequenceManager.generateId("payinfo");
				p.setId(id);
				initCreate(p, request);
				payinfoService.insert(p);
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

	@RequestMapping(value = "/ngres/haikuanxinxi/payinfo/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Payinfo> list = new ArrayList<Payinfo>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Payinfo p = new Payinfo();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Payinfo p : list) {
				ids.add(p.getId());
			}
			
			if(ids.size()!=0){
				WhereCondition wc = new WhereCondition();
				wc.andIn("id", ids);
				
				Payinfo p = new Payinfo();
				p.setDelstatus(DelStatus.REMOVED);//删除状态
				payinfoService.updateByCondition(wc, p);
				AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功!");
			}else{
				AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			}
			//payinfoService.deleteByCondition(wc);
			//AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/haikuanxinxi/payinfo/list")
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
			initWanNengChaXun(jsonObj, wc);// 万能查询
			//searchByDate(jsonObj, wc);//按日期查询
			//topSearch(jsonObj, wc);//头部查询
			//topSearchYN(jsonObj,wc);
			//permissionFilter(jsonObj, wc, request, response);//权限验证
			
			List list = payinfoService.query1(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);
			page.setTotalItems(payinfoService.count1(wc));
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
		@RequestMapping(value = "/ngres/haikuanxinxi/payinfo/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Payinfo k =  payinfoService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
			e.printStackTrace();
		}
		return null;
	}
		@RequestMapping(value = "/ngres/haikuanxinxi/payinfo/list/id1")
		public String executesd11(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				String uid = JSONUtils.getStr(jsonObj, "id");
				WhereCondition wc = new WhereCondition();
				wc.andEquals("uid", uid);
				wc.andEquals("status", "2");
				com.lanmosoft.dao.model.Order order = null;
				List<Payinfo> yuqilist = payinfoService.query2(wc);
				WhereCondition wc1 = new WhereCondition();
				wc1.andEquals("uid", uid);
				List<com.lanmosoft.dao.model.Order> orders = orderService.query(wc1);
				if(orders.size()>0){
					order = orders.get(0);
				}
				Map map = new HashMap();
				map.put("item", order);
				map.put("yuqilist", yuqilist);
				String s=JSONObject.fromObject(map,jsonConfig).toString();
				AjaxUtils.ajaxJson(response,s );
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}	
}
