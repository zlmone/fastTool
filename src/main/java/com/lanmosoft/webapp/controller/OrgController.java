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

import com.lanmosoft.dao.model.Org;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.enums.Enums;
import com.lanmosoft.enums.Enums.OrgType;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.OrgManager;
import com.lanmosoft.service.biz.OrgService;
import com.lanmosoft.service.biz.UserService;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.webapp.controller.searchForm.Page;
import com.lanmosoft.webapp.webmodel.Ztree;

/**
 * <p>
 * auto genegrated
 * </p>
 * <p>
 * Author: lanmo
 * </p>
 */
@Controller
public class OrgController extends BaseController {

	@Autowired
	OrgService orgService;
	@Autowired
	OrgManager orgManager;
	@Autowired
	UserService userService;

	List<User> postOfUserList = new ArrayList<User>();
	@RequestMapping(value = "/ngres/zuzhijiagou/org/edit")
	public String execute(ModelMap model, @RequestBody final String content,
			String age, final HttpServletRequest request, final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				try {
					JSONObject jsonObj = JSONObject.fromObject(content);
					JSONObject job = jsonObj.getJSONObject("item");
					Org p = new Org();
					JSONObject.toBean(job,p,jsonConfig);
					
					if(StringUtils.isEmpty(p.getId())){
						p.setOrg_code(getLogin(request).getUser().getOrg_code());
					}
					
					//判断组织是否重复
					if(orgManager.isExists(p)){
						String message = StringUtils.EMPTY;
						if(StringUtils.equals(OrgType.DEPARTMENT, p.getOrg_type())){
							message = "部门已存在！";
						} else{
							message = "岗位已存在！";
						}
						AjaxUtils.ajaxJsonWarnMessage(response, message);
						return;
					}
					
					if (StringUtils.isNotEmpty(p.getId())) {
						orgManager.updateOrg(p);
					} else {
						initCreate(p, request);
						p.setAccount_id(getLogin(request).getUser().getAccount_id());
						p.setAccount_name(getLogin(request).getUser().getAccount_name());
						orgManager.addOrg(p);
					}
					AjaxUtils.ajaxJsonSuccessMessage(response, "保存成功!");
				} catch (Exception e) {
					AjaxUtils.ajaxJsonErrorMessage(response, "保存失败!");
					e.printStackTrace();
				}
			}
		});
		return null;
	}

	@RequestMapping(value = "/ngres/zuzhijiagou/org/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Org> list = new ArrayList<Org>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Org p = new Org();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Org p : list) {
				ids.add(p.getId());
			}
			WhereCondition wc = new WhereCondition();
			wc.andIn("id", ids);
			orgService.deleteByCondition(wc);
			AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功！");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/ngres/zuzhijiagou/org/list")
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
			//permissionFilter(jsonObj, wc, request, response);
			List list = orgService.query(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);
			page.setTotalItems(orgService.count(wc));
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
		@RequestMapping(value = "/ngres/zuzhijiagou/org/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Org k =  orgService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/zuzhijiagou/org/list/org_type")
	public String execute6(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			WhereCondition wc = new WhereCondition();
			wc.andEquals("org_code", getLogin(request).getUser().getOrg_code());
			wc.andEquals("org_type", OrgType.DEPARTMENT);
			wc.andEquals("status", Enums.TRUE_STRING);
			List<Org> deptList = orgService.query(wc);
			wc = new WhereCondition();
			wc.andEquals("org_type", OrgType.POSITION);
			wc.andEquals("status", Enums.TRUE_STRING);
			wc.andEquals("org_code", getLogin(request).getUser().getOrg_code());
			List<Org> postList = orgService.query(wc);
			Map<String, Object> map = new HashMap<String, Object>();
			//user
			wc = new WhereCondition();
			wc.andEquals("org_code", getLogin(request).getUser().getOrg_code());
			List<User> userList = userService.query(wc);
			map.put("deptList", deptList);
			map.put("postList", postList);
			map.put("userList", userList);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response, s);
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "获取数据成功!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/zuzhijiagou/org/listDept")
	public String execute7(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			WhereCondition wc = new WhereCondition();
			wc.andEquals("org_code", getLogin(request).getUser().getOrg_code());
			wc.setOrderBy("sortNum asc");
			List<String> orgTypeList = new ArrayList<String>();
			orgTypeList.add(OrgType.DEPARTMENT);
			orgTypeList.add(OrgType.POSITION);
			List<Org> list = orgManager.listDept(wc,null, orgTypeList, true, false);
			List<Ztree> ztreeList = new ArrayList<Ztree>();
			for(Org org:list){
				Ztree zt= new Ztree();
				zt.setId(org.getId());
				zt.setPid(org.getDepartment_id());
				zt.setName(org.getName());
				zt.setType(org.getOrg_type());
				if(StringUtils.equals(OrgType.DEPARTMENT, zt.getType())){
					zt.setIconSkin("pIconDept");
				}else if(StringUtils.equals(OrgType.POSITION, zt.getType())){
					zt.setIconSkin("pIconPost");
				}
	
				zt.setDrag(true);
				ztreeList.add(zt);
			}
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(ztreeList,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "获取数据失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/zuzhijiagou/org/listDeptAndUser")
	public String execute7q(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			WhereCondition wc = new WhereCondition();
			wc.andEquals("org_code", getLogin(request).getUser().getOrg_code());
			wc.setOrderBy("sortNum asc");
			List<String> orgTypeList = new ArrayList<String>();
			orgTypeList.add(OrgType.DEPARTMENT);
			orgTypeList.add(OrgType.POSITION);
			List<Org> list = orgManager.listDept(wc,null, orgTypeList, true, false);
			List<Ztree> ztreeList = new ArrayList<Ztree>();
			for(Org org:list){
				Ztree zt= new Ztree();
				zt.setId(org.getId());
				zt.setPid(org.getDepartment_id());
				zt.setName(org.getName());
				zt.setType(org.getOrg_type());
				if(StringUtils.equals(OrgType.DEPARTMENT, zt.getType())){
					zt.setIconSkin("pIconDept");
				}else if(StringUtils.equals(OrgType.POSITION, zt.getType())){
					zt.setIconSkin("pIconPost");
				}
	
				zt.setDrag(true);
				ztreeList.add(zt);
			}
			//查询user
			wc=new WhereCondition();
			List<User> query = userService.query(wc);
			for (User user : query) {
				if(StringUtils.equals(user.getPosition_id(),null)){
					continue;
				}
				Ztree zt= new Ztree();
				zt.setId(user.getId());
				zt.setPid(user.getPosition_id());
				zt.setName(user.getName());
				zt.setType("renyuan");
				zt.setIconSkin("pIconDept");
				zt.setDrag(true);
				ztreeList.add(zt);
			}
			
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(ztreeList,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "获取数据失败!");
			e.printStackTrace();
		}
		return null;
	}
	

	@RequestMapping(value = "/ngres/zuzhijiagou/org/deleteOrg")
	public String execute8(ModelMap model, @RequestBody final String content,
			String age, HttpServletRequest request, final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				try {
					JSONObject jsonObj = JSONObject.fromObject(content);
					String id =JSONUtils.getStr(jsonObj, "id");
					String org_type = JSONUtils.getStr(jsonObj, "org_type");
					if(StringUtils.isEmpty(id)||StringUtils.isEmpty(org_type)){
						AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
						return;
					}
					WhereCondition wc = new WhereCondition();
					if(StringUtils.equals(org_type, "dept")){
						wc.andEquals("department_id", id);
					}else if (StringUtils.equals(org_type, "post")){
						wc.andEquals("id", id);
					}
					List<Org>  orgList = orgService.query(wc);
					postOfUserList.clear();
					List<User> userList = getPostOfUser(orgList);
					if(userList!=null&&userList.size()>0){
						for (User user : userList) {
							String userId = user.getId();
							userService.delete(userId);
						}
					}
					orgManager.deleteOrg(id, org_type);
					AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功!");
				} catch (Exception e) {// TODO
					arg0.setRollbackOnly();
					AjaxUtils.ajaxJsonErrorMessage(response, "删除失败!");
					e.printStackTrace();
				}
			}
		});
		return null;
	}
	public List<User> getPostOfUser(List<Org> orgList){
		if(orgList==null || orgList.size()<=0){
			return null;
		}
		WhereCondition wc = new WhereCondition();
		for(Org org:orgList){
			if(StringUtils.equals("post", org.getOrg_type())){
				wc=new WhereCondition();
				wc.andEquals("position_id", org.getId());
				postOfUserList.addAll(userService.query(wc));
			}else if(StringUtils.equals("dept", org.getOrg_type())){
				wc=new WhereCondition();
				wc.andEquals("department_id", org.getId());
				List<Org> oList = orgService.query(wc);
				if(oList!=null&&oList.size()>0){
					getPostOfUser(oList);
				}
			}
		}
		return postOfUserList;
	}
	@RequestMapping(value = "/ngres/zuzhijiagou/org/dragSqueue")
	public String execute81(ModelMap model, @RequestBody final String content,
			HttpServletRequest request,  final HttpServletResponse response) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				try {
					JSONObject jsonObj = JSONObject.fromObject(content);
					JSONArray jArr = jsonObj.getJSONArray("nodes");
					List<Org> orgList = new ArrayList<Org>();
					for(int i=0; i<jArr.size(); i++){
						String id = JSONUtils.getStr(jArr.getJSONObject(i), "id");
						Org org = new Org();
						org.setId(id);
						orgList.add(org);
					}
					orgManager.updateSort(orgList);
					AjaxUtils.ajaxJsonSuccessMessage(response, "排序成功!");
				} catch (Exception e) {
					arg0.setRollbackOnly();
					AjaxUtils.ajaxJsonErrorMessage(response, "排序失败!");
					e.printStackTrace();
				}
			}
		});
		return null;
	}
		
	@RequestMapping(value = "/ngres/zuzhijiagou/org/listPost")
	public String execute9(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			WhereCondition wc = new WhereCondition();
			wc.andEquals("org_code", getLogin(request).getUser().getOrg_code());
			wc.setOrderBy("CONVERT(name USING gbk) asc");
			List<String> orgTypeList = new ArrayList<String>();
			orgTypeList.add(OrgType.POSITION);
			List<Org> list = orgManager.listPost(wc,null, orgTypeList, true, false);
			List<Ztree> ztreeList = new ArrayList<Ztree>();
			for(Org org:list){
				Ztree zt= new Ztree();
				zt.setId(org.getId());
				zt.setPid(org.getPosition_id());
				zt.setName(org.getName()+"-"+org.getDepartment_name());
				zt.setType(org.getOrg_type());
				if(StringUtils.equals(OrgType.DEPARTMENT, zt.getType())){
					zt.setIconSkin("pIconDept");
				}else if(StringUtils.equals(OrgType.POSITION, zt.getType())){
					zt.setIconSkin("pIconPost");
				}
				zt.setDrag(false);
					
				WhereCondition wcuser = new WhereCondition();
				wcuser.andEquals("position_id", org.getId());
				List<User> users = userService.query(wcuser);
				zt.setUsers(users);
				ztreeList.add(zt);
			}
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(ztreeList,jsonConfig).toString());
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "获取数据失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/zuzhijiagou/org/queryPostByDept")
	public String execute10(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String department_id = JSONUtils.getStr(jsonObj, "department_id");
			WhereCondition wc = new WhereCondition();
			wc.andEquals("department_id", department_id);
			wc.andEquals("org_type", OrgType.POSITION);
			wc.andEquals("org_code", getLogin(request).getUser().getOrg_code());
			List<Org> postList = orgService.query(wc);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("postList", postList);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response, s);
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "获取数据失败!");
			e.printStackTrace();
		}
		return null;
	}
}
