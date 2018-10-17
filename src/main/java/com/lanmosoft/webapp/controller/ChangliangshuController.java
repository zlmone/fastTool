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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import com.lanmosoft.dao.model.Changliangshu;
import com.lanmosoft.dao.model.Filerecord;
import com.lanmosoft.enums.Enums.DelStatus;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.model.Ztree;
import com.lanmosoft.service.biz.ChangliangshuService;
import com.lanmosoft.service.lanmobase.ChangliangshuManager;
import com.lanmosoft.service.lanmobase.LanDateUtils;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.ExcelUtil;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.webapp.controller.searchForm.Page;
import com.lanmosoft.webapp.webmodel.TreeModel;

/**
 * <p>
 * auto genegrated
 * </p>
 * <p>
 * Author: lanmo
 * </p>
 */
@Controller
public class ChangliangshuController extends BaseController {

	@Autowired
	ChangliangshuService changliangshuService;

	@Autowired
	ChangliangshuManager changliangshuManager;

	@RequestMapping(value = "/ngres/changliangshu/edit")
	public String execute(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Changliangshu p = new Changliangshu();
			JSONObject.toBean(jsonObj,p,jsonConfig);
			String id = sequenceManager.generateId("changliangshu");
			if (StringUtils.isNotEmpty(p.getId())) {
				p.setModifiedTime(LanDateUtils.getNowDate());
				changliangshuManager.updateChangliangshu(p);
			} else {
				p.setId(id);
				p.setKeyongzhuangtai("1");
				p.setDelstatus(DelStatus.UNREMOVED);
				p.setCreateTime(LanDateUtils.getNowDate());
				changliangshuManager.addChangliangshu(p);
			}
			AjaxUtils.ajaxJson(response, JSONObject.fromObject(p).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/changliangshu/daoru")
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
						Changliangshu k  = new Changliangshu();
						k.setId(sequenceManager.generateId("changliangshu"));  
						for(int j=0;j<headList.size();j++){
							String value= ExcelUtil.getRowCellStr(row, j);
							BeanUtils.setProperty(k, headList.get(j), value);
						}
						changliangshuService.insert(k);

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
	@RequestMapping(value = "/ngres/changliangshu/daochu")
	public String execute231(ModelMap model, String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			//			JSONObject jsonObj = JSONObject.fromObject(content);
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			//			initWanNengChaXun(jsonObj, wc);// 万能查询
			List<Changliangshu>  list = changliangshuService.query(wc);
			String filepath="/lanmo/upload/mail/"+LanDateUtils.currentTimeStr()+".xls";
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
	@RequestMapping(value = "/ngres/changliangshu/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Changliangshu> list = new ArrayList<Changliangshu>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Changliangshu p = new Changliangshu();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Changliangshu p : list) {
				ids.add(p.getId());
			}
			WhereCondition wc = new WhereCondition();
			wc.andFullLike("quanlujingid", ids.get(0));
			changliangshuService.deleteByCondition(wc);
			AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/changliangshu/softdelete")
	public String execute32(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Changliangshu> list = new ArrayList<Changliangshu>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Changliangshu p = new Changliangshu();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			for (Changliangshu p : list) {
				p.setDelstatus(DelStatus.REMOVED); 
				changliangshuService.update(p);
			}
			AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/changliangshu/list")
	public String execute1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			// 分页对象
			Page page = (Page) JSONObject.toBean(jsonObj.getJSONObject("page"),
					Page.class);
			// 服务端排序规则
			String orderGuize = getOrderGuize(JSONUtils.getStr(jsonObj, "orderGuize"));
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			wc.andEquals("delstatus", DelStatus.UNREMOVED);
			wc.setLength(page.getItemsperpage());
			wc.setOffset((page.getCurrentPage() - 1) * page.getItemsperpage());
			wc.setOrderBy(orderGuize);
//			initWanNengChaXun(jsonObj, wc);// 万能查询
			//			List list = ChangliangshuService.query(wc);
			String fujiedian = JSONUtils.getStr(jsonObj, "fujiedian");
			String changliangshuleixing = JSONUtils.getStr(jsonObj, "changliangshuleixing");
			wc.andEquals("changliangshuleixing", changliangshuleixing);
			List<String> changliangshuleixingList = new ArrayList<String>();
		
			List list = changliangshuManager.listChangliangshu(wc,fujiedian, changliangshuleixingList, false, false);
			JSONArray ja = JSONArray.fromObject(list);
			page.setTotalItems(changliangshuService.count(wc));
			Map map = new HashMap();
			map.put("page", page);
			map.put("list", ja);
			String s=JSONObject.fromObject(map).toString();
			AjaxUtils.ajaxJson(response,s );
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/ngres/changliangshu/listztree")
	public String execute2w31(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String changliangshuleixing = JSONUtils.getStr(jsonObj, "changliangshuleixing");
			WhereCondition wc = new WhereCondition();
			wc.andEquals("delstatus", DelStatus.UNREMOVED).andEquals("changliangshuleixing", changliangshuleixing);
			List<Changliangshu>  list = changliangshuService.query(wc);
			List<Ztree> ztreeList = new ArrayList<Ztree>();
			for(Changliangshu c:list){
				Ztree z = new Ztree();
				z.setId(c.getId());
				z.setName(c.getMingcheng());
				z.setPid(c.getFujiedian());
				z.setIdpath(c.getQuanlujingid());
				z.setNamepath(c.getQuanlujingming());
				ztreeList.add(z);
			}
			String s=JSONArray.fromObject(ztreeList, jsonConfig).toString();
			AjaxUtils.ajaxJson(response,s );
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/changliangshu/select")
	public String execute1121(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject json = jsonObj.getJSONObject("model");
			Changliangshu p = new Changliangshu();
			JSONObject.toBean(json,p,jsonConfig);
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			wc.andEquals("delstatus", DelStatus.UNREMOVED);
			wc.andEquals("fujiedian", p.getId());
			List<Changliangshu> list = changliangshuService.query(wc);

			JSONArray ja = JSONArray.fromObject(list);
			Map map = new HashMap();
			map.put("list", ja);
			String s=JSONObject.fromObject(map).toString();
			AjaxUtils.ajaxJson(response,s );
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/changliangshu/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Changliangshu k =  changliangshuService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k);
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
	@RequestMapping(value = "/ngres/changliangshu/zhuanyi")
	public String execuwertesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			changliangshuManager.moveChangliangshu(JSONUtils.getStr(jsonObj, "source"), JSONUtils.getStr(jsonObj, "target")); 
			AjaxUtils.ajaxJsonSuccessMessage(response, "success");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/changliangshu/status")
	public String execuwertessdd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String id = JSONUtils.getStr(jsonObj, "id");
			String status = JSONUtils.getStr(jsonObj, "status");
			if(StringUtils.equals(status, "1")){
				changliangshuManager.activeChangliangshu(id);
			}
			if(StringUtils.equals(status, "2")){
				changliangshuManager.passiveChangliangshu(id);
			}
			if(StringUtils.equals(status, "3")){
				changliangshuManager.softDeleteChangliangshu(id);
			}

			AjaxUtils.ajaxJsonSuccessMessage(response, "success");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/changliangshu/listtree")
	public String executsdesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String changliangshuleixing = JSONUtils.getStr(jsonObj, "changliangshuleixing");
			WhereCondition wc = new WhereCondition();
			wc.andEquals("delstatus", DelStatus.UNREMOVED).andEquals("changliangshuleixing", changliangshuleixing);
			List<String> ChangliangshuleixingList = new ArrayList<String>();
		
			List<Changliangshu> list = changliangshuManager.listChangliangshu(wc,null, ChangliangshuleixingList, true, false);
			List<TreeModel> treeList = changliangshuManager.refactorChangliangshu(list);
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(treeList,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
}
