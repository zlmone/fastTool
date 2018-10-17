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

import com.lanmosoft.dao.model.Operationlog;
import com.lanmosoft.dao.model.Order;
import com.lanmosoft.dao.model.Telinfo;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.OperationlogService;
import com.lanmosoft.service.biz.OrderService;
import com.lanmosoft.service.biz.TelinfoService;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.HttpUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.webapp.controller.searchForm.Page;
import com.lanmosoft.webapp.webmodel.LoginModel;
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
public class TelinfoController extends BaseController {

	@Autowired
	TelinfoService telinfoService;
	
	@Autowired
	OperationlogService operationlogService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/ngres/dianhuaxinxi/telinfo/edit")
	public String execute(ModelMap model,final @RequestBody String content,
			String age,final HttpServletRequest request,final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
		   protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			
		try {
			LoginModel loginModel = (LoginModel) request.getSession().getAttribute("login");
			User user = loginModel.getUser();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			JSONArray jsonObj = JSONArray.fromObject(content);
			List<Telinfo> tels = new ArrayList<Telinfo>();
			for(int i = 0;i<jsonObj.size();i++){
				Telinfo t = new Telinfo();
				JSONObject.toBean(jsonObj.getJSONObject(i),t,jsonConfig);
				tels.add(t);
			}
			for (Telinfo t:tels) {
				if(StringUtils.isEmpty(t.getId())){
					
					Order order = orderService.loadById(t.getOrder_id());
					
					Map<String, Object> params = new HashMap<String, Object>();
			        params.put("tel", t.getPhonenum());
					String id = sequenceManager.generateId("telinfo");
					t.setId(id);
					t.setStatus(Enums.TelStatus.WEIDIAOCHA);
					String s = HttpUtils.doPost(Enums.TEL_URL,params);
					JSONObject s1 = JSONObject.fromObject(s.substring(18), jsonConfig);
					if(s1.containsKey("province")){
						t.setAddress(s1.getString("province"));
					}
					telinfoService.insert(t);
				}
			}
			Map map = new HashMap();
			map.put("status", "success");
			map.put("tellist", tels);
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

	@RequestMapping(value = "/ngres/dianhuaxinxi/telinfo/edit1")
	public String execute2(ModelMap model,final @RequestBody String content,
			String age,final HttpServletRequest request,final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
		   protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			
				try {
					JSONObject jsonObj = JSONObject.fromObject(content);
					Telinfo p = new Telinfo();
					JSONObject.toBean(jsonObj,p,jsonConfig);
					if (StringUtils.isNotEmpty(p.getId())) {
						telinfoService.update(p);
					} else {
						String id = sequenceManager.generateId("telinfo");
						p.setId(id);
						initCreate(p, request);
						telinfoService.insert(p);
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
	
	@RequestMapping(value = "/ngres/dianhuaxinxi/telinfo/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Telinfo> list = new ArrayList<Telinfo>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Telinfo p = new Telinfo();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Telinfo p : list) {
				ids.add(p.getId());
			}
			
			if(ids.size()!=0){
				WhereCondition wc = new WhereCondition();
				wc.andIn("id", ids);
				
				Telinfo p = new Telinfo();
				p.setDelstatus(DelStatus.REMOVED);//删除状态
				telinfoService.updateByCondition(wc, p);
				AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功!");
			}else{
				AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			}
			//telinfoService.deleteByCondition(wc);
			//AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/dianhuaxinxi/telinfo/list")
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
			
			List list = telinfoService.query(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);
			page.setTotalItems(telinfoService.count(wc));
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
		@RequestMapping(value = "/ngres/dianhuaxinxi/telinfo/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Telinfo k =  telinfoService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
			e.printStackTrace();
		}
		return null;
	}
		
		@RequestMapping(value = "/ngres/dianhuaxinxi/telinfo/list/orderid")
		public String executesd12(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				JSONObject jsonObj = JSONObject.fromObject(content);
				String order_id = jsonObj.getString("id");
				WhereCondition wc = new WhereCondition();
				wc.andEquals("order_id", order_id);
				List<Telinfo> list = telinfoService.query(wc);
				Map map = new HashMap();
				map.put("tellist", list);
				String s=JSONObject.fromObject(map,jsonConfig).toString();
				AjaxUtils.ajaxJson(response,s );
			} catch (Exception e) {// TODO
				AjaxUtils.ajaxJsonErrorMessage(response, "查询失败!");
				e.printStackTrace();
			}
			return null;
		}	
		
}
