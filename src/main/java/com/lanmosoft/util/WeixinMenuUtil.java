package com.lanmosoft.util;

import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class WeixinMenuUtil {
	
	public static void main(String[] args) {
		WeixinMenuUtil w=new WeixinMenuUtil();
		w.createMenu();
		//yztg7DKwKYdQs3HCZFi0cx6XHohE-LqwKK7lbzTB3MSp23iJmtYiPUyOlSS36iiYqQu_0chEBvF5I8fvlAwdoty5yif3Z8qxE1e6RXJdTmUWR8eLHHFImtGOJhXEOcogVMOgAFAHYA
		//String access_token = w.getAccessToken();
		//System.out.println(access_token);
		//String action ="https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token="+access_token;
		
		//w.connectWeiXinInterface(action, "{}");
		System.out.println("------------------");
		
		//WeixinMenuUtil menuUtil = new WeixinMenuUtil();
		//String token = menuUtil.getAccessToken();
		//System.out.println(token);
		
	}
	
	
	public void createMenu(){
		// String json = "{\"touser\": \""+toUser+"\",\"msgtype\": \"text\", \"text\": {\"content\": \""+content+"\"}}";
		
		String access_token = getAccessToken();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
		String json ="{ \"button\":["
							/*+ "{\"name\": \"申请\", \"sub_button\":["
									+ "{\"type\":\"view\", "
									+ "\"name\": \"企业介绍\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/qiyejieshao/\"},"
									+ "{\"type\":\"view\", "
									+ "\"name\": \"我的二维码\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/my_yaoqing/\"},"
									+ "{\"type\":\"view\", "
									+ "\"name\": \"代理商申请\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/dailishangshenqing/\"},"
									+ "{\"type\":\"view\", "
									+ "\"name\": \"会员申请\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/huiyuanshenqing/\"}"
							+ "]}, "
							+ "{\"name\": \"微商城\", \"sub_button\":["
									+ "{\"type\":\"view\", "
									+ "\"name\": \"微商城\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/weishangcheng/\"}"
							+ "]}, "
							+ "{\"name\": \"个人中心\", \"sub_button\":["
									+ "{\"type\":\"view\", "
									+ "\"name\": \"个人信息\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/gerenxinxi/\"},"
									+ "{\"type\":\"view\", "
									+ "\"name\": \"提现\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/huiyuantixian/\"},"
									+ "{\"type\":\"view\", "
									+ "\"name\": \"服务商信息\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/fuwushang/\"},"
									+ "{\"type\":\"view\", "
									+ "\"name\": \"我的订单\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/my_dingdan/\"},"
									+ "{\"type\":\"view\", "
									+ "\"name\": \"APP下载\", "
									+ "\"url\":\"http://www.bjleyihui.com/pingtai/weixin/app/\"}"
							+ "]} ";*/
		
		
		
							+ "{\"name\": \"申请\", \"sub_button\":["
							+ "{\"type\":\"view\", "
							+ "\"name\": \"企业介绍\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/qiyejieshao/\"},"
							+ "{\"type\":\"view\", "
							+ "\"name\": \"我的二维码\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/my_yaoqing/\"},"
							+ "{\"type\":\"view\", "
							+ "\"name\": \"代理商申请\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/dailishangshenqing/\"},"
							+ "{\"type\":\"view\", "
							+ "\"name\": \"会员申请\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/huiyuanshenqing/\"}"
					+ "]}, "
					+ "{\"name\": \"微商城\", \"sub_button\":["
							+ "{\"type\":\"view\", "
							+ "\"name\": \"微商城\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/weishangcheng/\"}"
					+ "]}, "
					+ "{\"name\": \"个人中心\", \"sub_button\":["
							+ "{\"type\":\"view\", "
							+ "\"name\": \"个人信息\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/gerenxinxi/\"},"
							+ "{\"type\":\"view\", "
							+ "\"name\": \"提现\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/huiyuantixian/\"},"
							+ "{\"type\":\"view\", "
							+ "\"name\": \"服务商信息\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/fuwushang/\"},"
							+ "{\"type\":\"view\", "
							+ "\"name\": \"我的订单\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/my_dingdan/\"},"
							+ "{\"type\":\"view\", "
							+ "\"name\": \"APP下载\", "
							+ "\"url\":\"http://sz.lanmosoft.com/pingtai/weixin/app/\"}"
					+ "]} ";
		
			
        json +="]}";
        
        connectWeiXinInterface(action,json);

	}
	//secret:630af3c5f9b5633ff90b9b884de8f708
	 public static String getAccessToken(){
		   String accessToken="";
		   SendUtil send=new SendUtil();
		   //正式
		   //String urlString="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx309e31e2fa875df1&secret=4a0aaeba073013b174bfa12f535dd84f";
		   //测试
		   String urlString="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx968d59671a0715af&secret=630af3c5f9b5633ff90b9b884de8f708";
		   String json=send.getUrl(urlString);
		   System.out.println("json-->"+json);
	       JSONObject jsonObject=JSONObject.fromObject(json);
	       if(jsonObject.getString("access_token")!=null){
	    	   accessToken=jsonObject.getString("access_token");
	       }
	       System.out.println("accessToken-->"+accessToken);
		   return accessToken;
	} 
	 
	 
	 /**

	    * 连接请求微信后台接口

	    * @param action 接口url

	    * @param json  请求接口传送的json字符串

	    */

	   public  void connectWeiXinInterface(String action,String json){

	       URL url;

	      try {

	          url = new URL(action);

	          HttpURLConnection http = (HttpURLConnection) url.openConnection();

	          http.setRequestMethod("POST");

	          http.setRequestProperty("Content-Type",

	                  "application/x-www-form-urlencoded");

	          http.setDoOutput(true);

	          http.setDoInput(true);

	          System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

	          System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

	          http.connect();

	          OutputStream os = http.getOutputStream();

	          os.write(json.getBytes("UTF-8"));// 传入参数

	          InputStream is = http.getInputStream();

	          int size = is.available();

	          byte[] jsonBytes = new byte[size];

	          is.read(jsonBytes);

	          String result = new String(jsonBytes, "UTF-8");

	          System.out.println("请求返回结果:"+result);

	          os.flush();

	          os.close();

	      } catch (Exception e) {

	          e.printStackTrace();

	      }

	   }

}
