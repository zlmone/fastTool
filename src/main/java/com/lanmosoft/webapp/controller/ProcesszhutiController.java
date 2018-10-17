/**
 * auto generated
 * Copyright (C) 2013 lanmosoft.com, All rights reserved.
 */
package com.lanmosoft.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanmosoft.dao.model.Filerecord;
import com.lanmosoft.dao.model.Processapp;
import com.lanmosoft.dao.model.Processview;
import com.lanmosoft.dao.model.Processzhuti;
import com.lanmosoft.enums.Enums.DelStatus;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.ProcessappService;
import com.lanmosoft.service.biz.ProcessviewService;
import com.lanmosoft.service.biz.ProcesszhutiService;
import com.lanmosoft.service.lanmobase.DateUtils;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.ExcelUtil;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.webapp.controller.searchForm.Page;
import com.lanmosoft.webapp.webmodel.LoginModel;

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
public class ProcesszhutiController extends BaseController {

	@Autowired
	ProcesszhutiService processzhutiService;
	@Autowired
	ProcessappService processappService;
	@Autowired
	ProcessviewService processviewService;
	
	@RequestMapping(value = "/ngres/shenpi/processzhuti/editStatus")
	public String execute23(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject job = jsonObj.getJSONObject("item");
			Processzhuti p = new Processzhuti();
			JSONObject.toBean(job,p,jsonConfig);
			if(StringUtils.equals("1",p.getStatus())){//审批状态启用
				LoginModel login = (LoginModel)request.getSession().getAttribute("login");
				//判断审批类型是否重复
				WhereCondition wc1=new WhereCondition();
				wc1.andEquals("shenpileixing",p.getShenpileixing());
				wc1.andEquals("org_code", login.getUser().getOrg_code());
				wc1.andEquals("belong_city",p.getBelong_city());
				Processzhuti ps = new Processzhuti();
				ps.setStatus("0");
				processzhutiService.updateByCondition(wc1, ps);
			}
			
			p.setModifiedTime(DateUtils.getNowDate());
			processzhutiService.update(p);
			
			AjaxUtils.ajaxJsonSuccessMessage(response, "操作成功!");
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	

	@RequestMapping(value = "/ngres/shenpi/processzhuti/edit")
	public String execute(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject job = jsonObj.getJSONObject("item");
			Processzhuti p = new Processzhuti();
			JSONObject.toBean(job,p,jsonConfig);
			
			if(StringUtils.equals("1",p.getStatus())){//审批状态启用
				//判断有无其他一样的 启用的审批
				LoginModel login = (LoginModel)request.getSession().getAttribute("login");
				//判断审批类型是否重复
				WhereCondition wc1=new WhereCondition();
				wc1.andEquals("shenpileixing",p.getShenpileixing());
				wc1.andEquals("org_code", login.getUser().getOrg_code());
				wc1.andEquals("status",p.getStatus());
				wc1.andEquals("belong_city",p.getBelong_city());
				if (StringUtils.isNotEmpty(p.getId())) {
					wc1.andNotEquals("id", p.getId());
				}
				int count = processzhutiService.count(wc1);
				if(count>0){
					AjaxUtils.ajaxJsonWarnMessage(response, "审批类型已存在启用的！");
					return null;
				}
				
			}
			
			
			if (StringUtils.isNotEmpty(p.getId())) {
				WhereCondition wc = new WhereCondition();
				wc.andEquals("pid", p.getId());
				processappService.deleteByCondition(wc);
				//initCreate(p, request);
				p.setModifiedTime(DateUtils.getNowDate());
				processzhutiService.update(p);
			} else {
				String id = sequenceManager.generateId("processzhuti");
				p.setId(id);
				initCreate(p, request);
				processzhutiService.insert(p);
			}

			JSONArray jsonArr = jsonObj.getJSONArray("list");
			for(int i = 0; i < jsonArr.size(); i++) {
				Processapp pro = new Processapp();
				JSONObject jon = jsonArr.getJSONObject(i);
				JSONObject.toBean(jon, pro, jsonConfig);
				pro.setPid(p.getId());
				pro.setId(sequenceManager.generateId("processapp"));
				//设置序号
				pro.setXuhao(i+"");
				
				
				
				initCreate(pro, request);
				processappService.insert(pro);
			}
			AjaxUtils.ajaxJsonSuccessMessage(response, "操作成功!");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/shenpi/processzhuti/daoru")
	public String execdfute(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String uniqueIdentifier=JSONUtils.getStr(jsonObj.getJSONArray("files").getJSONObject(0), "uniqueIdentifier");
			WhereCondition wc = new WhereCondition();
			wc.andEquals("yuanshiid", request.getSession().getId()+"_"+uniqueIdentifier);
			Filerecord fr=filerecordService.query(wc).get(0);
			String fullpath =fr.getLogicfullpath();
			{
				FileInputStream fileStream = null;  
				try  
				{ 
					File f=new File(fullpath);
					fileStream = new FileInputStream(f);  
					HSSFWorkbook work = new HSSFWorkbook(fileStream);  
					HSSFSheet sheet = work.getSheetAt(0);  
					int rowSumNum = sheet.getLastRowNum();  
					HSSFRow row = null;  
					row = sheet.getRow(0);  
					List<String> headList = new ArrayList<String>();
					for(int i =0;i<50;i++){
						String a=  ExcelUtil.getRowCellStr(row, i);
						if(StringUtils.isEmpty(a)){
							break;
						}
						headList.add(a);
					}
					for (int i = 2; i <= rowSumNum; i++)  
					{  
						row = sheet.getRow(i);  
						if (row == null)  
						{  
							continue;  
						}  
						Processzhuti k  = new Processzhuti();
						k.setId(sequenceManager.generateId("processzhuti"));  
						for(int j=0;j<headList.size();j++){
							String value= ExcelUtil.getRowCellStr(row, j);
							BeanUtils.setProperty(k, headList.get(j), value);
						}
						processzhutiService.insert(k);

					}  
					fileStream.close();  
				}  
				catch (Exception e)  
				{  
					e.printStackTrace();  
				}  

			}
			AjaxUtils.ajaxJsonSuccessMessage(response, "success");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/shenpi/processzhuti/daochu")
	public String execute231(ModelMap model, String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			//			JSONObject jsonObj = JSONObject.fromObject(content);
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			//			initWanNengChaXun(jsonObj, wc);// 万能查询
			List<Processzhuti>  list = processzhutiService.query(wc);
			String filepath="/lanmo/upload/mail/"+DateUtils.currentTimeStr()+".xls";
			{  
				File f=new File(filepath);
				HSSFWorkbook work = new HSSFWorkbook();  
				HSSFSheet sheet = work.createSheet();
				HSSFRow row = null;  
				row= sheet.createRow(0);
				//  row.createCell(0).setCellValue("xingming");
				//  row.createCell(1).setCellValue("kehuleibie");
				for(int i=0;i<list.size();i++){
					row= sheet.createRow(i+1);
					//      row.createCell(0).setCellValue(list.get(i).getXingming());
					//     row.createCell(1).setCellValue(list.get(i).getKehuleibie());
				}
				work.write(new FileOutputStream(f));

			}
			OutputStream os = response.getOutputStream();
			try {  
				response.reset();  
				response.setHeader("Content-Disposition", "attachment; filename=daochu.xls");  
				response.setContentType("application/octet-stream; charset=gbk");  
				os.write(FileUtils.readFileToByteArray(new File(filepath)));  
				os.flush();  
			} finally {  
				if (os != null) {  
					os.close();  
				}  
			}  
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/shenpi/processzhuti/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			//JSONObject jsonObj = JSONObject.fromObject(content);
			//JSONArray jsonArray =jsonObj.getJSONArray("data"); 
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Processzhuti> list = new ArrayList<Processzhuti>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Processzhuti p = new Processzhuti();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
				
				//
				WhereCondition wc=new WhereCondition();
				wc.andEquals("pid",p.getId());
				processappService.deleteByCondition(wc);
				
			}
			List<String> ids = new ArrayList<String>();
			for (Processzhuti p : list) {
				ids.add(p.getId());
			}
			WhereCondition wc = new WhereCondition();
			wc.andIn("id", ids);
			processzhutiService.deleteByCondition(wc);
			AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功!");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/shenpi/processzhuti/softdelete")
	public String execute32(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Processzhuti> list = new ArrayList<Processzhuti>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Processzhuti p = new Processzhuti();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			for (Processzhuti p : list) {
				p.setDelstatus("1"); 
				processzhutiService.update(p);
			}

			AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/shenpi/processzhuti/list")
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
			
			//topSearch(jsonObj, wc);
			//searchByOwner(jsonObj, wc, request, response);//按所有者查询
			//searchByDate(jsonObj, wc);
			permissionFilter(jsonObj, wc, request, response);
			
			//地域权限
			List<String> data_city = data_city(request, response);
			wc.andIn("belong_city", data_city);
			
			
			List list = processzhutiService.query(wc);
			JSONArray ja = JSONArray.fromObject(list,jsonConfig);
			/**一对多的时候取多 example
			{
				for(int i=0;i<ja.size();i++){
					JSONObject jo = ja.getJSONObject(i);
					String jid = JSONUtils.getStr(jo, "id");
					WhereCondition wcfile= new WhereCondition();
					wcfile.andEquals("refid", jid);
					List<Filerecord>  filelist = filerecordService.query(wcfile);
					jo.put("addson", JSONArray.fromObject(filelist,jsonConfig));
				}
			}**/
			page.setTotalItems(processzhutiService.count(wc));
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
	@RequestMapping(value = "/ngres/shenpi/processzhuti/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Processzhuti k =  processzhutiService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k,jsonConfig);
			
			WhereCondition wc = new WhereCondition();
			wc.andEquals("pid", k.getId());
			wc.andEquals("delstatus", DelStatus.UNREMOVED);
			wc.setOrderBy("xuhao+1");
			List<Processapp> list = processappService.query(wc);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("item", jo);
			map.put("list", list);
			AjaxUtils.ajaxJson(response, JSONObject.fromObject(map,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/shenpi/processzhuti/queryshenpiren")
	public String executesd1090(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String leixing = jsonObj.getString("leixing");
			JSONObject job = jsonObj.getJSONObject("item");
			Map<String, Object> map = new HashMap<String, Object>();
			
			if(StringUtils.equals(leixing, "2")) {
				WhereCondition wc = new WhereCondition();
				wc.andEquals("shenpileixing", leixing);
				List<Processview> list = processviewService.query(wc);
				map.put("model", list.get(0));
			}
			AjaxUtils.ajaxJson(response, JSONObject.fromObject(map,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
}
