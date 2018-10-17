/**
 * auto generated
 * Copyright (C) 2013 lanmosoft.com, All rights reserved.
 */
package com.lanmosoft.webapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Decoder;

import com.lanmosoft.dao.model.Filerecord;
import com.lanmosoft.enums.Enums.DelStatus;
import com.lanmosoft.enums.Enums.path;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.FilerecordService;
import com.lanmosoft.service.lanmobase.DateUtils;
import com.lanmosoft.service.lanmobase.LanDateUtils;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
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
public class FileController extends BaseController {
	@Autowired
	FilerecordService filerecordService;

	@RequestMapping(value="/ngres/file/findAll/byIdAndBeizhu")
	public String executa(ModelMap model, @RequestBody String content,HttpServletResponse response,
			HttpServletRequest request) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String str = JSONUtils.getStr(jsonObj, "id");
			String beizhu = JSONUtils.getStr(jsonObj, "beizhu");
			WhereCondition wc=new WhereCondition();
			wc.andEquals("refid", str);
			wc.andEquals("beizhu",beizhu);
			List<Filerecord> query = filerecordService.query(wc);  
			Map map = new HashMap();
			map.put("list", query);
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response,s );
			
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.ajaxJsonErrorMessage(response, "error");
		}
		return null;
	}
	
	
	@RequestMapping(value = "/ngres/downloadfile/{id}")
	@ResponseBody
	public void exe23wcute12w2111332(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, @PathVariable String id) throws Exception{
		if(StringUtils.equals(id, "0")){
			OutputStream os = response.getOutputStream();
			try {  
				response.reset();  
				response.setContentType( "image/gif");
				os.write(FileUtils.readFileToByteArray(new File(path.f+"/lanmo/upload/common/noimage.jpg")));  
				os.flush();  
			} finally {  
				if (os != null) {  
					os.close();  
				}  
			}  
		}
		Filerecord f= filerecordService.loadById(id);
		String ctype="application/x-msdownload";
		if(StringUtils.equals(f.getHouzhuiming().toLowerCase(), "jpg")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "jpeg")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "gif")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "png")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "bmp")){
			ctype="image/gif";
		}
		if(f!=null) {
			String base="/lanmo/upload/mail/qingpu";
			String fullpath=f.getLogicfullpath();
			OutputStream os = response.getOutputStream();
			String agent = request.getHeader("USER-AGENT");    
			String downLoadName = null;  
			String filename=f.getYuanshimingcheng();
			if (null != agent && -1 != agent.indexOf("MSIE"))   //IE  
			{    
			  downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");   
			}    
			  else if (null != agent && -1 != agent.indexOf("Mozilla")) //Firefox  
			{        
			  downLoadName = new String(filename.getBytes("UTF-8"),"iso-8859-1");     
			}    
			else     
			{  
			  downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");   
			}    
			try {  
				response.reset();  
     			response.setHeader("Content-Disposition", "attachment; filename="+downLoadName);  
     			response.setContentType("application/octet-stream; charset=gbk");  
     			os.write(FileUtils.readFileToByteArray(new File(fullpath)));  
     			os.flush();  
			} finally {  
				if (os != null) {  
					os.close();  
				}  
			}  
		}
	}
	
	
	
	@RequestMapping(value = "/ngres/filerecord/edit")
	public String execute(ModelMap model, @RequestBody String content,
			String age, HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Filerecord p = new Filerecord();
			JSONObject.toBean(jsonObj,p,jsonConfig);
			if (StringUtils.isNotEmpty(p.getId())) {
				filerecordService.update(p);
			} else {
				String id = sequenceManager.generateId("todotask");
				p.setId(id);
				filerecordService.insert(p);
			}
			AjaxUtils.ajaxJson(response, JSONObject.fromObject(p).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/phone/upload64")
	public String execute1132333231(ModelMap model, String  refid,String imgsrc,String leixing,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("refid:"+refid);
		System.out.println("leixing:"+leixing);
		System.out.println("imgsrc:"+imgsrc);
		//data:image/jpeg;base64,
		 String base="/lanmo/upload/lantu";
		File filebase = new File(base);
		if(!filebase.exists()){
			FileUtils.forceMkdir(filebase);
		}
		Filerecord filerecord = new Filerecord();
		filerecord.setLeixing(leixing);
		filerecord.setRefid(refid);
		filerecord.setCreateTime(LanDateUtils.getNowDate());
		filerecord.setId(sequenceManager.generateId("filerecord"));
		filerecord.setYuanshimingcheng("xiangce.jpg");
		filerecord.setHouzhuiming("jpg");
		filerecord.setLogicfullpath(base+"/"+filerecord.getId()+"_"+filerecord.getYuanshimingcheng());
		filerecord.setLogicname(filerecord.getId()+"_"+filerecord.getYuanshimingcheng());
		filerecord.setModifiedTime(LanDateUtils.getNowDate());
		filerecord.setXianshimingcheng("xiangce.jpg"); 
		filerecord.setXitongzhuangtai("1");
		filerecord.setBeizhu("");
//		initCreate(filerecord, request); 
		filerecordService.insert(filerecord);
		GenerateImage(imgsrc.substring(23),filerecord.getLogicfullpath());
		Map<String,String>map = new HashMap<String,String>();
		map.put("fileid", filerecord.getId());
		AjaxUtils.ajaxJson(response, map);
		return null;
	}
	
	 public static String GenerateImage(String imgStr,String fullpath) throws IOException
	    {
			//对字节数组字符串进行Base64解码并生成图片
	        if (imgStr == null) //图像数据为空
	            return "";
	        BASE64Decoder decoder = new BASE64Decoder();
	        try 
	        {
	            //Base64解码
	            byte[] b = decoder.decodeBuffer(imgStr);
	            for(int i=0;i<b.length;++i)
	            {
	                if(b[i]<0)
	                {//调整异常数据
	                    b[i]+=256;
	                }
	            }
	            //生成jpeg图片
	            String imgFilePath = fullpath;//新生成的图片
	          
	            OutputStream out = new FileOutputStream(imgFilePath+".png");    
	            out.write(b);
	            System.err.println("b==="+b);
	            out.flush();
	            out.close();
	            return imgFilePath;
	        } 
	        catch (Exception e) 
	        {
	        	e.printStackTrace();
	            return "";
	        }
	    }
	 
	 
		//yn
		@RequestMapping(value = "/ngres/uploadYn")
		@ResponseBody
		public String execute11231s(ModelMap model, @RequestBody String content,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			String base="/lanmo/upload/"+getLogin(request).getUser().getId();
			File filebase = new File(base);
			if(!filebase.exists()){
				FileUtils.forceMkdir(filebase);
			}
			if(request instanceof MultipartHttpServletRequest){
				MultipartHttpServletRequest  mulpartReq = (MultipartHttpServletRequest)request;
				MultipartFile file = mulpartReq.getFile("file");
				String originalFilename=file.getOriginalFilename();
				String leixing=ServletRequestUtils.getStringParameter(request, "leixing");
				String refid=ServletRequestUtils.getStringParameter(request, "refid");
				
			
				String []arr=StringUtils.split(originalFilename,".");
				Filerecord filerecord = new Filerecord();
				filerecord.setLeixing(leixing);
				filerecord.setRefid(refid);
				filerecord.setCreateTime(LanDateUtils.getNowDate());
				filerecord.setId(sequenceManager.generateId("filerecord"));
				filerecord.setYuanshimingcheng(originalFilename);
				filerecord.setHouzhuiming(arr[arr.length-1]);
				filerecord.setLogicfullpath(base+"/"+filerecord.getId()+"."+filerecord.getHouzhuiming());
				filerecord.setLogicname(filerecord.getId()+"_"+filerecord.getYuanshimingcheng());
				filerecord.setModifiedTime(LanDateUtils.getNowDate());
				filerecord.setXianshimingcheng(originalFilename); 
				filerecord.setXitongzhuangtai("1");

				filerecord.setBeizhu(leixing);
				filerecord.setRefid(refid);
				
				
				initCreate(filerecord, request); 
				filerecordService.insert(filerecord);
				file.transferTo(new File(filerecord.getLogicfullpath()));
				Map<String,String>map = new HashMap<String,String>();
				map.put("fileid", filerecord.getId());
				AjaxUtils.ajaxJson(response, map);
			}else{
				response.sendError(500, "welcome to lanmosoft.com" );
			}
			return null;
		}
	 
	 
	
	@RequestMapping(value = "/phone/upload")
	public String execute11333231(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//		String base="/lanmo/upload/"+getLogin(request).getRenyuan().getId();
		String base="/lanmo/upload/lantu";
		File filebase = new File(base);
		if(!filebase.exists()){
			FileUtils.forceMkdir(filebase);
		}
		if(request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest  mulpartReq = (MultipartHttpServletRequest)request;
			MultipartFile file = mulpartReq.getFile("file");
			String originalFilename=file.getOriginalFilename();
			//			if(StringUtils.endsWith(yuanshimingcheng.split("\\.")[1], "xls") || StringUtils.endsWith(yuanshimingcheng.split("\\.")[1], "xlsx")){
			//				file.transferTo(new File(base+"/"+filerecord.getLogicname()));
			//			} else {
			//				file.transferTo(new File(filerecord.getLogicfullpath()));
			//			}
			//			file.transferTo(new File(base+"/"+filerecord.getLogicname()));
			String leixing=ServletRequestUtils.getStringParameter(request, "leixing");
			String refid=ServletRequestUtils.getStringParameter(request, "refid");

			String []arr=StringUtils.split(originalFilename,".");
			Filerecord filerecord = new Filerecord();
			filerecord.setLeixing(leixing);
			filerecord.setRefid(refid);
			filerecord.setCreateTime(LanDateUtils.getNowDate());
			filerecord.setId(sequenceManager.generateId("filerecord"));
			filerecord.setYuanshimingcheng(originalFilename);
			filerecord.setHouzhuiming(arr[arr.length-1]);
			filerecord.setLogicfullpath(base+"/"+filerecord.getId()+"_"+filerecord.getYuanshimingcheng());
			filerecord.setLogicname(filerecord.getId()+"_"+filerecord.getYuanshimingcheng());
			filerecord.setModifiedTime(LanDateUtils.getNowDate());
			filerecord.setXianshimingcheng(originalFilename); 
			filerecord.setXitongzhuangtai("1");
			filerecord.setBeizhu("");
//			initCreate(filerecord, request); 
			filerecordService.insert(filerecord);
			file.transferTo(new File(filerecord.getLogicfullpath()));
			Map<String,String>map = new HashMap<String,String>();
			map.put("fileid", filerecord.getId());
			AjaxUtils.ajaxJson(response, map);
		}else{
			//			throw new RuntimeException("");
			response.sendError(500, "welcome to lanmosoft.com" );
		}
		return null;
	}
	
	@RequestMapping(value = "/ngres/upload")
	@ResponseBody
	public String execute11231(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String base=path.f+"/lanmo/upload/mail";
		//String sessionId =request.getSession().getId();
		LoginModel login = (LoginModel)request.getSession().getAttribute("login");
		if(request instanceof MultipartHttpServletRequest){
			String identifer = ServletRequestUtils.getStringParameter(request, "flowIdentifier");
			String yuanshimingcheng = ServletRequestUtils.getStringParameter(request, "flowFilename");
			System.out.println("------------>"+identifer);
			System.out.println("------------>"+yuanshimingcheng);
			Filerecord filerecord = new Filerecord();
			filerecord.setCreateTime(DateUtils.getNowDate());
			filerecord.setId(sequenceManager.getUUID());
			filerecord.setYuanshimingcheng(yuanshimingcheng);
			filerecord.setHouzhuiming(yuanshimingcheng.substring(yuanshimingcheng.lastIndexOf(".")+1));
			filerecord.setLogicfullpath(base+"/"+filerecord.getId()+"."+filerecord.getHouzhuiming());
			filerecord.setLogicname(filerecord.getId()+"."+filerecord.getHouzhuiming());
			filerecord.setModifiedTime(DateUtils.getNowDate());
			filerecord.setXianshimingcheng(yuanshimingcheng.substring(0, yuanshimingcheng.lastIndexOf("."))); 
			filerecord.setXitongzhuangtai("1");
			filerecord.setBeizhu(LanDateUtils.format(DateUtils.getNowDate(), "yyyyMMddHH"));
			if("xls".equals(filerecord.getHouzhuiming())||"xlsx".equals(filerecord.getHouzhuiming())){
				filerecord.setLeixing("shujudaoru");
			}
			filerecord.setRefid(login.getUser().getId());
			filerecord.setCreatorId(login.getUser().getId());
			filerecord.setCreatorName(login.getUser().getName());
			filerecord.setDelstatus(DelStatus.UNREMOVED);
			filerecordService.insert(filerecord);
			MultipartHttpServletRequest  mulpartReq = (MultipartHttpServletRequest)request;
			MultipartFile file = mulpartReq.getFile("file");
			request.getSession().setAttribute("wenben", filerecord.getLogicname());
			file.transferTo(new File(base+"/"+filerecord.getLogicname()));
		}else{
			response.sendError(500, "welcome to lanmosoft.com" );
		}
		return null;
	}
	

	
	@RequestMapping(value = "/ngres/download/{id}")
	public void exe23wcute12w2111(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, @PathVariable String id) throws Exception{
		if(StringUtils.equals(id, "0")){
			OutputStream os = response.getOutputStream();
			try {  
				response.reset();  
				response.setContentType( "image/gif");
				os.write(FileUtils.readFileToByteArray(new File("/lanmo/upload/common/noimage.jpg")));  
				os.flush();  
			} finally {  
				if (os != null) {  
					os.close();  
				}  
			}  
		}
		Filerecord f= filerecordService.loadById(id);
		String ctype="application/octet-stream";
		if(f!=null) {
			if(StringUtils.equals(f.getHouzhuiming().toLowerCase(), "jpg")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "jpeg")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "gif")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "png")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "bmp")){
				ctype="image/gif";
			}
		}
		if(f!=null) {///lanmo/upload/lantu/Id_filerecord00000003_1423196856213.jpg
			String fullpath=f.getLogicfullpath();
			OutputStream os = response.getOutputStream();
			String agent = request.getHeader("USER-AGENT");    
			String downLoadName = null;  
			String filename=f.getYuanshimingcheng();
			if (null != agent && -1 != agent.indexOf("MSIE"))   //IE  
			{    
				downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");   
			}    
			else if (null != agent && -1 != agent.indexOf("Mozilla")) //Firefox  
			{        
				downLoadName = new String(filename.getBytes("UTF-8"),"iso-8859-1");     
			}    
			else     
			{  
				downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");   
			}    
			try {  
				response.reset();  
				//     			response.setHeader("Content-Disposition", "attachment; filename="+downLoadName);  
				response.setContentType(ctype);  
				os.write(FileUtils.readFileToByteArray(new File(fullpath)));  
				os.flush();  
			} finally {  
				if (os != null) {  
					os.close();  
				}  
			}  
		}
	}
	
	@RequestMapping(value = "/phone/filerecord/download/{id}")
	public void exe23wcute12w234234322111(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, @PathVariable String id) throws Exception{
		if(StringUtils.equals(id, "0")){
			OutputStream os = response.getOutputStream();
			try {  
				response.reset();  
				response.setContentType( "image/gif");
				os.write(FileUtils.readFileToByteArray(new File("/lanmo/upload/common/noimage.jpg")));  
				os.flush();  
			} finally {  
				if (os != null) {  
					os.close();  
				}  
			}  
		}
		Filerecord f= filerecordService.loadById(id);
		String ctype="application/octet-stream";
		if(f!=null) {
			if(StringUtils.equals(f.getHouzhuiming().toLowerCase(), "jpg")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "jpeg")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "gif")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "png")||StringUtils.equals(f.getHouzhuiming().toLowerCase(), "bmp")){
				ctype="image/gif";
			}
		}
		if(f!=null) {///lanmo/upload/lantu/Id_filerecord00000003_1423196856213.jpg
			String fullpath=f.getLogicfullpath();
			OutputStream os = response.getOutputStream();
			String agent = request.getHeader("USER-AGENT");    
			String downLoadName = null;  
			String filename=f.getYuanshimingcheng();
			if (null != agent && -1 != agent.indexOf("MSIE"))   //IE  
			{    
				downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");   
			}    
			else if (null != agent && -1 != agent.indexOf("Mozilla")) //Firefox  
			{        
				downLoadName = new String(filename.getBytes("UTF-8"),"iso-8859-1");     
			}    
			else     
			{  
				downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");   
			}    
			try {  
				response.reset();  
				//     			response.setHeader("Content-Disposition", "attachment; filename="+downLoadName);  
				response.setContentType(ctype);  
				os.write(FileUtils.readFileToByteArray(new File(fullpath)));  
				os.flush();  
			} finally {  
				if (os != null) {  
					os.close();  
				}  
			}  
		}
	}


	@RequestMapping(value = "/ngres/download/muban/{filename}")
	@ResponseBody
	public void exe23wcutewe12w2111(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, @PathVariable String filename) throws Exception{
		if(true) {
			filename=filename+".xls";
			String fullpath="/lanmo/muban/g/"+filename;
			OutputStream os = response.getOutputStream();
			String agent = request.getHeader("USER-AGENT");    
			String downLoadName = null;  
			if (null != agent && -1 != agent.indexOf("MSIE"))   //IE  
			{    
				downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");   
			}    
			else if (null != agent && -1 != agent.indexOf("Mozilla")) //Firefox  
			{        
				downLoadName = new String(filename.getBytes("UTF-8"),"iso-8859-1");     
			}    
			else     
			{  
				downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");   
			}    
			try {  
				response.reset();  
				response.setHeader("Content-Disposition", "attachment; filename="+downLoadName);  
				response.setContentType("application/octet-stream; charset=gbk");  
				os.write(FileUtils.readFileToByteArray(new File(fullpath)));  
				os.flush();  
			} finally {  
				if (os != null) {  
					os.close();  
				}  
			}  
		}
	}

	@RequestMapping(value = "/ngres/filerecord/query")
	public String execute1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			String refid = JSONUtils.getStr(jsonObj, "refid");
			System.out.println(refid);
			List list = filerecordService.query(wc);
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(list,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/phone/filerecord/query")
	public String execu23t3e1(ModelMap model,String refid,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			wc.andEquals("refid",refid);

			wc.setOrderBy("id desc");  
			List list = filerecordService.query(wc);
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(list,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/filerecord/query2")
	public String execu23te1(ModelMap model, String refid,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			if(StringUtils.equals(refid, "wenjiangui")){  
				wc.andEquals("leixing", "wenjiangui").andEquals("creatorId", getLogin(request).getRenyuan().getId());
			}else{
				wc.andEquals("refid",refid);
			}
			wc.setOrderBy("id desc");  
			List<Filerecord> list = filerecordService.query(wc);
			List<String> strlist = new ArrayList<String>();
			for(Filerecord f:list){
				strlist.add(f.getId());
			}
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(strlist,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/filerecord/query3")
	public String execu23te1211(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			String refid = jsonObj.getString("refid");
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			if(StringUtils.equals(refid, "wenjiangui")){  
				wc.andEquals("leixing", "wenjiangui").andEquals("creatorId", getLogin(request).getRenyuan().getId());
			}else{
				wc.andEquals("refid",refid);
			}
			wc.setOrderBy("id desc");  
			List<Filerecord> list = filerecordService.query(wc);
			List<String> strlist = new ArrayList<String>();
			for(Filerecord f:list){
				strlist.add(f.getId());
			}
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(strlist,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/filerecord/query/gongxiang")
	public String execu2te231(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			wc.andEquals("leixing", "wenjiangui").andFullLike("gongxiangid", getLogin(request).getRenyuan().getId());
			wc.setOrderBy("id desc");  
			List list = filerecordService.query(wc);
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(list,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/ngres/filerecord/query/gongxiangall")
	public String exec23u2te231(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 组装查询条件
			WhereCondition wc = new WhereCondition();
			wc.andEquals("leixing", "wenjiangui").andEquals("gongxiangid", "suoyouren");
			wc.setOrderBy("id desc");  
			List list = filerecordService.query(wc);
			AjaxUtils.ajaxJson(response, JSONArray.fromObject(list,jsonConfig).toString());
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/ngres/filerecord/delete")
	public String execute231(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			filerecordService.delete(JSONUtils.getStr(jsonObj, "id"));
			AjaxUtils.ajaxJsonSuccessMessage(response, "success");
		} catch (Exception e) {// TODO
			AjaxUtils.ajaxJsonErrorMessage(response, "添加失败!");
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/ngres/uploadFile")
	@ResponseBody
	public String uploadFile(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginModel login = (LoginModel)request.getSession().getAttribute("login");
		String base="/lanmo/upload/mail/qingpu";
		File filebase = new File(base);
		if(!filebase.exists()){
			FileUtils.forceMkdir(filebase);
		}
		if(request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest  mulpartReq = (MultipartHttpServletRequest)request;
			MultipartFile file = mulpartReq.getFile("file");	
			String originalFilename=file.getOriginalFilename();
			long fileSize = file.getSize();
			String type = ServletRequestUtils.getStringParameter(request, "type");
			String refId = ServletRequestUtils.getStringParameter(request, "refid");
			String status = ServletRequestUtils.getStringParameter(request, "status");
			if(refId==null||"".equals(refId.trim())){
				refId=System.currentTimeMillis()+"";
			}
			if(type==null||"".equals(type.trim())){
				type="shujudaoru";
			}
			String []arr=StringUtils.split(originalFilename,".");
			//System.out.println(arr.toString());
			Filerecord filerecord = new Filerecord();
			filerecord.setCreateTime(DateUtils.getNowDate());
			filerecord.setLeixing(type);
			filerecord.setId(sequenceManager.generateId("filerecord"));
			filerecord.setYuanshimingcheng(originalFilename);
			filerecord.setRefid(login.getUser().getId());
			filerecord.setHouzhuiming(arr[arr.length-1]);
			filerecord.setLogicfullpath(base+"/"+filerecord.getId()+"."+filerecord.getHouzhuiming());
			filerecord.setLogicname(filerecord.getId()+"."+filerecord.getHouzhuiming());
			filerecord.setModifiedTime(DateUtils.getNowDate());
			filerecord.setXianshimingcheng(originalFilename); 
			filerecord.setXitongzhuangtai("1");
			filerecord.setBeizhu(status);
			filerecord.setDelstatus(DelStatus.UNREMOVED);
			filerecordService.insert(filerecord);
			file.transferTo(new File(filerecord.getLogicfullpath()));
			Map<String,String>map = new HashMap<String,String>();
			map.put("fileid", filerecord.getId());
			AjaxUtils.ajaxJson(response, map);
		}else{
			response.sendError(500, "welcome to lanmosoft.com" );
		}
		return null;
	}
	
	@RequestMapping(value = "/ngres/uploadFile1")
	@ResponseBody
	public String uploadFile1(ModelMap model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginModel login = (LoginModel)request.getSession().getAttribute("login");
		String base="/lanmo1/upload/mail/qingpu";
		File filebase = new File(base);
		if(!filebase.exists()){
			FileUtils.forceMkdir(filebase);
		}
		if(request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest  mulpartReq = (MultipartHttpServletRequest)request;
			MultipartFile file = mulpartReq.getFile("file");	
			String originalFilename=file.getOriginalFilename();
			long fileSize = file.getSize();
			System.out.println("---->"+fileSize);
			String type = ServletRequestUtils.getStringParameter(request, "type");
			String refId = ServletRequestUtils.getStringParameter(request, "refid");
			String status = ServletRequestUtils.getStringParameter(request, "status");
			if(refId==null||"".equals(refId.trim())){
				refId=System.currentTimeMillis()+"";
			}
			if(type==null||"".equals(type.trim())){
				type="shujudaoru";
			}
			String []arr=StringUtils.split(originalFilename,".");
			System.out.println(arr.toString());
			Filerecord filerecord = new Filerecord();
			filerecord.setCreateTime(DateUtils.getNowDate());
			filerecord.setLeixing(type);
			filerecord.setId(sequenceManager.generateId("filerecord"));
			filerecord.setYuanshimingcheng(originalFilename);
			filerecord.setRefid(refId);
			filerecord.setHouzhuiming(arr[arr.length-1]);
			filerecord.setLogicfullpath(base+"/"+filerecord.getId()+"."+filerecord.getHouzhuiming());
			filerecord.setLogicname(filerecord.getId()+"."+filerecord.getHouzhuiming());
			filerecord.setModifiedTime(DateUtils.getNowDate());
			filerecord.setXianshimingcheng(originalFilename); 
			filerecord.setXitongzhuangtai("1");
			filerecord.setBeizhu(status);
			filerecord.setDelstatus(DelStatus.UNREMOVED);
			filerecordService.insert(filerecord);
			file.transferTo(new File(filerecord.getLogicfullpath()));
			Map<String,String>map = new HashMap<String,String>();
			map.put("fileid", filerecord.getId());
			AjaxUtils.ajaxJson(response, map);
		}else{
			response.sendError(500, "welcome to lanmosoft.com" );
		}
		return null;
	}
}
