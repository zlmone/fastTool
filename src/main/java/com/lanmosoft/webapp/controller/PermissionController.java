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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanmosoft.dao.model.Permission;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.enums.Enums.DataRole;
import com.lanmosoft.model.Function;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.res.ResManager;
import com.lanmosoft.service.biz.PermissionService;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.webapp.controller.searchForm.Page;
import com.lanmosoft.webapp.webmodel.LoginModel;

/**
 * <p>
 * auto genegrated
 * </p>
 * <p>
 * Author: lanmo
 * </p>
 */
@Controller
public class PermissionController extends BaseController {

	@Autowired
	PermissionService permissionService;
	@RequestMapping(value = "/ngres/zuzhijiagou/permission/edit")
	public String execute(ModelMap model, @RequestBody final String content,
			String age, final HttpServletRequest request, final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				try {
					JSONObject jsonObj = JSONObject.fromObject(content);
					String position_id = JSONUtils.getStr(jsonObj, "position_id");
					String position_name = JSONUtils.getStr(jsonObj, "position_name");
					JSONArray jarr = jsonObj.getJSONArray("permissionList");
					if(StringUtils.isNotEmpty(position_id)){
						WhereCondition wc=new WhereCondition();
						wc.andEquals("position_id", JSONUtils.getStr(jsonObj, "position_id"));
						permissionService.deleteByCondition(wc);
					}
					for(int i=0;i<jarr.size();i++){
						Permission p = new Permission();
						p.setId(sequenceManager.generateId("permission"));   
						p.setPosition_id(position_id);
						p.setPosition_name(position_name);
						p.setFunction_id(JSONUtils.getStr(jarr.getJSONObject(i), "id"));
						p.setFunction_name(JSONUtils.getStr(jarr.getJSONObject(i), "name"));
						p.setFunction_type(JSONUtils.getStr(jarr.getJSONObject(i), "type"));
						p.setFirstModule(JSONUtils.getStr(jarr.getJSONObject(i), "firstModule"));
						p.setSecondModule(JSONUtils.getStr(jarr.getJSONObject(i), "secondModule"));
						p.setData_role(JSONUtils.getStr(jarr.getJSONObject(i), "dataRole"));
						initCreate(p, request);
						permissionService.insert(p);
					}			
					AjaxUtils.ajaxJsonSuccessMessage(response, "授权成功!");
				} catch (Exception e) {
					AjaxUtils.ajaxJsonErrorMessage(response, "授权失败!");
					e.printStackTrace();
				}
			}
		});
		
		return null;
	}

	@RequestMapping(value = "/ngres/zuzhijiagou/permission/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Permission> list = new ArrayList<Permission>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Permission p = new Permission();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Permission p : list) {
				ids.add(p.getId());
			}
			WhereCondition wc = new WhereCondition();
			wc.andIn("id", ids);
			permissionService.deleteByCondition(wc);
			AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/ngres/zuzhijiagou/permission/list")
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
			List list = permissionService.query(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);
			page.setTotalItems(permissionService.count(wc));
			Map map = new HashMap();
			map.put("page", page);
			map.put("list", ja);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response,s );
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
		@RequestMapping(value = "/ngres/zuzhijiagou/permission/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Permission k =  permissionService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/zuzhijiagou/permission/list/role")
	public String execute6(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String position_id=JSONUtils.getStr(jsonObj, "position_id");
			WhereCondition wc=new WhereCondition();
			wc.andEquals("position_id", position_id);
			 List<Permission> permissionList=permissionService.query(wc);
			 List<Function> functionList = ResManager.getAllRes();
			 for(Function f:functionList){
				 if(CollectionUtils.isNotEmpty(permissionList)){
					 for(Permission p:permissionList){
						 if(StringUtils.equals(f.getId(), p.getFunction_id())){
							 f.setDataRole(p.getData_role());
							 f.setChecked(true);
							 break;
						 }else{
							 f.setDataRole(DataRole.POST);
							 f.setChecked(false);
						 }
					 }
				 }
			 }
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(functionList).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/zuzhijiagou/permission/res/self")
	public String exescweute23sd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginModel loginModel = (LoginModel)request.getSession().getAttribute("login");
			User user = loginModel.getUser();
			WhereCondition wc = new WhereCondition();
			wc.andEquals("position_id", user.getPosition_id());
			List<Permission> permissionList = permissionService.query(wc);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("permissionList", permissionList);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response, s);
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
}
