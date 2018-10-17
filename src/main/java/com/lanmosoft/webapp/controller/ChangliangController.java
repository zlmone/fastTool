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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanmosoft.dao.model.Changliang;
import com.lanmosoft.enums.Enums;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.ChangliangService;
import com.lanmosoft.service.lanmobase.LanDateUtils;
import com.lanmosoft.service.lanmobase.SequenceManager;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.util.PinyinUtil;
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
public class ChangliangController extends BaseController {

	@Autowired
	ChangliangService changliangService;
	
	
	
	@RequestMapping(value = "/ngres/gonggong/changliang/edit")
	public String execute(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject job = jsonObj.getJSONObject("item");
			Changliang p = (Changliang) JSONObject.toBean(job,
					Changliang.class);
			if (StringUtils.isNotEmpty(p.getId())) {
				List<String> ids = new ArrayList<String>();
				ids.add(p.getId());
				WhereCondition wc = new WhereCondition();
				wc.andNotIn("id", ids);
				wc.andEquals("leibie", p.getLeibie());
				wc.andEquals("mingcheng", p.getMingcheng());
				List<Changliang> list = changliangService.query(wc);
				if(CollectionUtils.isNotEmpty(list)) {
					AjaxUtils.ajaxJsonErrorMessage(response, "名称已存在!");
				} else {
					p.setModifiedTime(LanDateUtils.getNowDate());
					if(StringUtils.isEmpty(p.getExt5())){
						wc.clear();
						wc.andEquals("leibie", p.getLeibie());
						 list = changliangService.query(wc);
						p.setExt5((list.size())+""); 
					}
					if(StringUtils.isEmpty(p.getDaima())){
						p.setDaima(PinyinUtil.convert(p.getMingcheng()));
					}
					changliangService.update(p);
					AjaxUtils.ajaxJson(response, JSONObject.fromObject(p).toString());
				}
			} else {
				WhereCondition wc = new WhereCondition();
				wc.andEquals("mingcheng", p.getMingcheng());
				wc.andEquals("leibie", p.getLeibie());
				List<Changliang> list = changliangService.query(wc);
				if(CollectionUtils.isNotEmpty(list)) {
					AjaxUtils.ajaxJsonErrorMessage(response, "名称已存在!");
				} else {
					p.setId(sequenceManager.generateId("changliang"));
					p.setCreateTime(LanDateUtils.getNowDate());
					initCreate(p, request);
					if(StringUtils.isEmpty(p.getExt5())){
						wc.clear();
						wc.andEquals("leibie", p.getLeibie());
						 list = changliangService.query(wc);
						p.setExt5((list.size()+1)+""); 
					}
					if(StringUtils.isEmpty(p.getDaima())){
						p.setDaima(PinyinUtil.convert(p.getMingcheng()));
					}
					changliangService.insert(p);
					AjaxUtils.ajaxJson(response, JSONObject.fromObject(p).toString());
				}
			}
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/ngres/gonggong/changliang/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Changliang> list = new ArrayList<Changliang>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Changliang p = (Changliang) JSONObject.toBean(
						(JSONObject.fromObject(jsonArray.get(i))),
						Changliang.class);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Changliang p : list) {
				ids.add(p.getId());
			}
			WhereCondition wc = new WhereCondition();
			wc.andIn("id", ids);
			changliangService.deleteByCondition(wc);
			AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/gonggong/changliang/softdelete")
	public String execute32(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Changliang> list = new ArrayList<Changliang>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Changliang p = (Changliang) JSONObject.toBean(
						(JSONObject.fromObject(jsonArray.get(i))),
						Changliang.class);
				list.add(p);
			}
			for (Changliang p : list) {
				p.setDelstatus("1"); 
				changliangService.update(p);
			}

			AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/gonggong/changliang/list")
	public String execute1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			// 分页对象
			Page page = (Page) JSONObject.toBean(jsonObj.getJSONObject("page"),
					Page.class);
			if(page==null){
				page = new Page();
			}
			// 服务端排序规则
			String orderGuize = getOrderGuize(JSONUtils.getStr(jsonObj, "orderGuize"));
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			wc.setLength(page.getItemsperpage());
			wc.setOffset((page.getCurrentPage() - 1) * page.getItemsperpage());
			wc.setOrderBy("ext5");
			wc.andEquals("leibie", jsonObj.getString("leibie"));
			initWanNengChaXun(jsonObj, wc);// 万能查询
			wc.andEquals("delstatus", Enums.DelStatus.UNREMOVED);
			List<Changliang> list = changliangService.query(wc);
			JSONArray ja = JSONArray.fromObject(list, jsonConfig);

			page.setTotalItems(changliangService.count(wc));
			Map map = new HashMap();
			map.put("page", page);
			map.put("list", ja);
			AjaxUtils.ajaxJson(response, JSONObject.fromObject(map).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/gonggong/changliang/list/allchangliang")
	public String execweute1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jo = new JSONObject();
			JSONArray jarr = JSONArray.fromObject(content);
			for(int i=0;i<jarr.size();i++){
				WhereCondition wc = new WhereCondition();
				wc.andEquals("leibie", jarr.getString(i));
				int leibiesize=changliangService.count(wc);
				jo.put( jarr.getString(i), leibiesize);
			}
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/gonggong/changliang/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Changliang k =  changliangService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			/**一对多的时候取多 example
			{
					String jid = JSONUtils.getStr(jo, "id");
					WhereCondition wcfile= new WhereCondition();
					wcfile.andEquals("refid", jid);
					List<Filerecord>  filelist = filerecordService.query(wcfile);
					jo.put("addson", JSONArray.fromObject(filelist));
			}**/
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
}
