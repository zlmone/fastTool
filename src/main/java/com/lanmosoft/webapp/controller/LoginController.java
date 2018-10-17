package com.lanmosoft.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanmosoft.dao.model.Permission;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.enums.Enums;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.PermissionService;
import com.lanmosoft.service.biz.UserService;
import com.lanmosoft.util.AjaxUtils;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.util.PasswdUtil;
import com.lanmosoft.webapp.webmodel.LoginModel;

@Controller
public class LoginController extends BaseController {
	@Autowired
	UserService userService;
	@Autowired
	PermissionService permissionService;
	/*@Autowired
	Account_lanmoService account_lanmoService;*/
	/*@Autowired 
	ShouyegonggaoService shouyegonggaoService;*/
	
	@RequestMapping(value = "/login")
	public String execute(ModelMap model, String org_code, String loginname,
			String passwd, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("come in1");
		Object loginObj = request.getSession().getAttribute("login");
		if(loginObj!=null){
			return "redirect:/ng/index.html";
		}
		WhereCondition whereCondition = new WhereCondition();
		/*whereCondition.andNotEquals("delstatus", "1");
		whereCondition.or();
		whereCondition.andIsNull("delstatus");
		List<Shouyegonggao> syggList = shouyegonggaoService.query(whereCondition);
		model.put("shouyegonggaolist", syggList);*/

		if(StringUtils.isEmpty(loginname)){
//			model.put("error", "用户名不能为空!");
			return "login";
		}
		if(StringUtils.isEmpty(passwd)){
			model.put("error", "密码不能为空!");
			return "login";
		}
		
		WhereCondition wc = new WhereCondition();
		wc = new WhereCondition();
		wc.andEquals("name", loginname);//loginname   passwd
		wc.andEquals("password", PasswdUtil.encode(passwd));
		List<User> list =userService.query(wc);
		if(CollectionUtils.isEmpty(list)){
			//用户名或密码有误
			model.put("error", "用户名或密码有误");
			return "login";
		}else if(list.size()>1){
			//账户异常
			model.put("error", "账户异常，请联系管理员!");
			return "login";
		}else{
			User user = list.get(0);
			System.out.println("size"+list.size());
			LoginModel loginModel = new LoginModel();
			
			User loginUser=user;//登陆成功的人员
			//用户信息
			loginModel.setUser(loginUser);
			//获取权限列表
			WhereCondition permissionwc = new WhereCondition();
			permissionwc.andEquals("position_id", user.getPosition_id());
			List<Permission> permissionList = permissionService.query(permissionwc);
			
			loginModel.setPermissionList(permissionList);
			request.getSession().setAttribute("login", loginModel);  
			return "redirect:/ng/index.html";
		}
	}
	
	
	@RequestMapping(value = "/ngres/passwd")
	public String execute2233xin2zeng(ModelMap model, @RequestBody String content,HttpServletResponse response,
			HttpServletRequest request) {
		JSONObject jsonObj = JSONObject.fromObject(content);
		String oldpasswd=JSONUtils.getStr(jsonObj, "oldpasswd");
		String newpasswd=JSONUtils.getStr(jsonObj, "newpasswd");
		LoginModel loginModel = (LoginModel)request.getSession().getAttribute("login");
		if(StringUtils.equals(loginModel.getUser().getPassword(), PasswdUtil.encode(oldpasswd))){
			userService.loadById(loginModel.getUser().getId());
			loginModel.getUser().setPassword(PasswdUtil.encode(newpasswd));
			userService.update(loginModel.getUser()); 
			
			AjaxUtils.ajaxJsonSuccessMessage(response, "密码更改成功");
		}else{
			AjaxUtils.ajaxJsonErrorMessage(response, "原始密码错误");
		}
		return null;
	}

	@RequestMapping(value = "/logout")
	public String execute2233xin342zeng(ModelMap model,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
		}
		return "redirect:/login";
	}
	@RequestMapping(value = "/ngres/login/info")
	public String execweute2233xin342zeng(ModelMap model,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			LoginModel login = (LoginModel)request.getSession().getAttribute("login");
			Map<String, Object> map  = new HashMap<String,Object>();
			map.put("user", login.getUser());
			String s=JSONObject.fromObject(map,jsonConfig).toString();
			AjaxUtils.ajaxJson(response, s);
		} catch (Exception e) {
		}
		return null;
	}
	
	/*@RequestMapping(value = "/shouyegonggao")
	public String execute(ModelMap model, String id,
			HttpServletResponse response, HttpServletRequest request) {
		Shouyegonggao shouyegonggao = shouyegonggaoService.loadById(id);
		model.put("shouyegonggaodetail", shouyegonggao);
		return "shouyegonggao";
	}*/
}
