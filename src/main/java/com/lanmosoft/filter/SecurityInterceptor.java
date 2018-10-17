package com.lanmosoft.filter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lanmosoft.util.AjaxUtils;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request,  
	        HttpServletResponse response,Object handler)throws Exception{  
		//System.out.println("进入spring mvc 拦截器..."); 
		String servletPath =request.getServletPath();
		String contextPath =request.getContextPath();
		String remoteAddr =request.getRemoteAddr();
		String requestedSessionId =request.getRequestedSessionId();
		String requestURI =request.getRequestURI();
		String requestURL = request.getRequestURL().toString();
		String pathInfo = request.getPathInfo();
		request.setAttribute("uripath", pathInfo); 
		
		  if(!bypass(pathInfo)){
			  Object loginobj = request.getSession().getAttribute("login");
			  if(loginobj==null){
//				  response.sendRedirect(contextPath+"/login"); 
				  Map<String,String> map = new HashMap<String,String>();
				  map.put("loginstatus", "error");
				  AjaxUtils.ajaxJson(response, JSONObject.fromObject(map)); 
				  return false;  
				}else{
					//System.out.println(1111);
				}
		  }
		  
		  return true;  
		
	    }  
	private boolean bypass(String pathInfo){//
		//System.out.println("come in 11");
		  if(StringUtils.startsWith(pathInfo,"/login")||
				  StringUtils.startsWith(pathInfo,"/logout")||
				  StringUtils.startsWith(pathInfo,"/ngres/download/")||
				  StringUtils.startsWith(pathInfo,"/phone/")||StringUtils.startsWith(pathInfo, "/weixin/")){ 
			  //System.out.println("come in 22");
			 return true;
		  }
		  return false;
	}
	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(address.getHostAddress());
	}
}
