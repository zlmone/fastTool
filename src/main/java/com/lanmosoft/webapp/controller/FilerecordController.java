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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanmosoft.dao.model.Filerecord;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.FilerecordService;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.webapp.controller.searchForm.Page;

/**
 * <p>
 * auto genegrated
 * </p>
 * <p>
 * Author: lanmo
 * </p>
 */
@Controller
public class FilerecordController extends BaseController {

	@Autowired
	FilerecordService filerecordService;
	
	@RequestMapping(value="/ngres/gonggong/filerecord/list/bugList")
	public String execute2(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String id=JSONUtils.getStr(jsonObj, "id");
			WhereCondition wc=new WhereCondition();
			wc.andEquals("refid", id);
			wc.andEquals("beizhu","bug");
			List<Filerecord> k =  filerecordService.query(wc);
			Map map = new HashMap();
			map.put("list", k);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response,s );
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/ngres/gonggong/filerecord/edit")
	public String execute(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject job = jsonObj.getJSONObject("item");
			Filerecord p = new Filerecord();
			JSONObject.toBean(job,p,jsonConfig);
			String id = sequenceManager.generateId("filerecord");
			if (StringUtils.isNotEmpty(p.getId())) {
				filerecordService.update(p);
			} else {
				p.setId(id);
				initCreate(p, request);
				filerecordService.insert(p);
			}
			AjaxUtils.ajaxJson(response, JSONObject.fromObject(p).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/ngres/gonggong/filerecord/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Filerecord> list = new ArrayList<Filerecord>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Filerecord p = new Filerecord();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Filerecord p : list) {
				ids.add(p.getId());
			}
			WhereCondition wc = new WhereCondition();
			wc.andIn("id", ids);
			filerecordService.deleteByCondition(wc);
			AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/ngres/gonggong/filerecord/list")
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
//			String qtype = JSONUtils.getStr(jsonObj, "qtype");
//			if(StringUtils.equals("sf", qtype)){
//				wc.andEquals("leixing", "wenjiangui").andEquals("creatorId", getLogin(request).getRenyuan().getId());
//			}else if(StringUtils.equals("gx", qtype)){
//				wc.andEquals("leixing", "wenjiangui").andFullLike("gongxiangid", getLogin(request).getRenyuan().getId());
//			}else if(StringUtils.equals("al", qtype)){
//				wc.andEquals("leixing", "wenjiangui").andEquals("gongxiangid", "suoyouren");
//			}else{
//				return null;
//			}
			wc.setOrderBy("id desc");  
			List<Filerecord> list = filerecordService.query(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);
			page.setTotalItems(filerecordService.count(wc));
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
		@RequestMapping(value = "/ngres/gonggong/filerecord/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Filerecord k =  filerecordService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
}
