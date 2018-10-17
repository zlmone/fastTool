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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanmosoft.dao.model.Seller;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.SellerService;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.util.LanDateUtils;
import com.lanmosoft.webapp.controller.searchForm.Page;

import freemarker.template.SimpleDate;

import com.lanmosoft.enums.Enums;
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
public class SellerController extends BaseController {

	@Autowired
	SellerService sellerService;
	
	
	@RequestMapping(value = "/ngres/shanghuguanli/seller/edit")
	public String execute(ModelMap model,final @RequestBody String content,
			String age,final HttpServletRequest request,final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
		   protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject job = jsonObj.getJSONObject("item");
			Seller p = new Seller();
			JSONObject.toBean(job,p,jsonConfig);
			if (StringUtils.isNotEmpty(p.getId())) {
				sellerService.update(p);
			} else {
				String id = sequenceManager.generateId("seller");
				p.setId(id);
				Date time = new Date();
				String time1 = dateFormat.format(time);
				p.setAddtime(time1);
				String number = sequenceManager.generateNOWithLength(null, 4, "YH"+LanDateUtils.currentDateStr(), null,false);
				p.setNumber(number);
				initCreate(p, request);
				sellerService.insert(p);
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

	@RequestMapping(value = "/ngres/shanghuguanli/seller/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Seller> list = new ArrayList<Seller>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Seller p = new Seller();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Seller p : list) {
				ids.add(p.getId());
			}
			
			if(ids.size()!=0){
				WhereCondition wc = new WhereCondition();
				wc.andIn("id", ids);
				
				Seller p = new Seller();
				p.setDelstatus(DelStatus.REMOVED);//删除状态
				sellerService.updateByCondition(wc, p);
				AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功!");
			}else{
				AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			}
			//sellerService.deleteByCondition(wc);
			//AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/shanghuguanli/seller/list")
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
			wc.andEquals("delstatus", Enums.DelStatus.UNREMOVED);
			List list = sellerService.query(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);
			page.setTotalItems(sellerService.count(wc));
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
		@RequestMapping(value = "/ngres/shanghuguanli/seller/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Seller k =  sellerService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
			e.printStackTrace();
		}
		return null;
	}
}
