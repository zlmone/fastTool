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

import com.lanmosoft.dao.model.Filerecord;
import com.lanmosoft.dao.model.Ziduanshezhi;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.ZiduanshezhiService;
import com.lanmosoft.service.lanmobase.LanDateUtils;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.ExcelUtil;
import com.lanmosoft.util.FreeMarkerUtil;
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
public class ZiduanshezhiController extends BaseController {

	@Autowired
	ZiduanshezhiService ziduanshezhiService;

	@RequestMapping(value = "/ngres/gonggong/ziduanshezhi/edit")
	public String execute(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
		
			JSONArray jarr = JSONArray.fromObject(content,jsonConfig);
			List<Ziduanshezhi> ziduanlist = new ArrayList<Ziduanshezhi>();
			for(int i=0;i<jarr.size();i++){
				JSONObject jsonObj = jarr.getJSONObject(i);
				Ziduanshezhi p = new Ziduanshezhi();
				JSONObject.toBean(jsonObj,p,jsonConfig);
				p.setZiduanpaixu(""+i);
				ziduanshezhiService.update(p);
				ziduanlist.add(p);
			}
			{
				String realpath = request.getRealPath("/");
				FreeMarkerUtil.init(realpath+"/ng/ftl");
				String targetFile=realpath+"/ng/partials/kehuxinxi-list.html";
				Map<String,Object> root = new HashMap<String,Object>();
				root.put("ziduanlist", ziduanlist);
				FreeMarkerUtil.process("kehuxinxi-list.html", root , targetFile);
			}
			AjaxUtils.ajaxJsonSuccessMessage(response, "success");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
@RequestMapping(value = "/ngres/gonggong/ziduanshezhi/daoru")
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
                        Ziduanshezhi k  = new Ziduanshezhi();
                    	k.setId(sequenceManager.generateId("ziduanshezhi"));  
                        for(int j=0;j<headList.size();j++){
                        	String value= ExcelUtil.getRowCellStr(row, j);
                        	BeanUtils.setProperty(k, headList.get(j), value);
                        }
                        ziduanshezhiService.insert(k);
                       
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
	@RequestMapping(value = "/ngres/gonggong/ziduanshezhi/daochu")
	public String execute231(ModelMap model, String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
//			JSONObject jsonObj = JSONObject.fromObject(content);
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
//			initWanNengChaXun(jsonObj, wc);// 万能查询
			 List<Ziduanshezhi>  list = ziduanshezhiService.query(wc);
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
	@RequestMapping(value = "/ngres/gonggong/ziduanshezhi/delete")
	public String execute3(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Ziduanshezhi> list = new ArrayList<Ziduanshezhi>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Ziduanshezhi p = new Ziduanshezhi();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			List<String> ids = new ArrayList<String>();
			for (Ziduanshezhi p : list) {
				ids.add(p.getId());
			}
			WhereCondition wc = new WhereCondition();
			wc.andIn("id", ids);
			ziduanshezhiService.deleteByCondition(wc);
			AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/gonggong/ziduanshezhi/softdelete")
	public String execute32(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(content);
			List<Ziduanshezhi> list = new ArrayList<Ziduanshezhi>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Ziduanshezhi p = new Ziduanshezhi();
				JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),p,jsonConfig);
				list.add(p);
			}
			for (Ziduanshezhi p : list) {
				p.setDelstatus("1"); 
				ziduanshezhiService.update(p);
			}
			
			AjaxUtils.ajaxJson(response, "");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/gonggong/ziduanshezhi/list")
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
			wc.setLength(page.getItemsperpage());
			wc.setOffset((page.getCurrentPage() - 1) * page.getItemsperpage());
			wc.setOrderBy(orderGuize);
			initWanNengChaXun(jsonObj, wc);// 万能查询
			List list = ziduanshezhiService.query(wc);
			JSONArray ja = JSONArray.fromObject(list);
			/**一对多的时候取多 example**/
			{
				for(int i=0;i<ja.size();i++){
					JSONObject jo = ja.getJSONObject(i);
					String jid = JSONUtils.getStr(jo, "id");
					WhereCondition wcfile= new WhereCondition();
					wcfile.andEquals("refid", jid);
					List<Filerecord>  filelist = filerecordService.query(wcfile);
					jo.put("addson", JSONArray.fromObject(filelist));
				}
			}
			page.setTotalItems(ziduanshezhiService.count(wc));
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
		@RequestMapping(value = "/ngres/gonggong/ziduanshezhi/list/id")
	public String executesd1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Ziduanshezhi k =  ziduanshezhiService.loadById(JSONUtils.getStr(jsonObj, "id"));
			JSONObject jo= JSONObject.fromObject(k);
			/**一对多的时候取多 example**/
			{
					String jid = JSONUtils.getStr(jo, "id");
					WhereCondition wcfile= new WhereCondition();
					wcfile.andEquals("refid", jid);
					List<Filerecord>  filelist = filerecordService.query(wcfile);
					jo.put("addson", JSONArray.fromObject(filelist));
			}
			AjaxUtils.ajaxJson(response, jo.toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
}
