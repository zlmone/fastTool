package com.lanmosoft.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

public class SendUtil {
	
	//private String sendurl="http://123.57.77.231:9909/send";
	private String sendurl="http://47.92.25.195:9002/pay";
	
	//private String sendurl="http://127.0.0.1:9002/pay";
	
	public synchronized String sendMsg(String openid, String spbill_create_ip, String amount,String partner_trade_no) {
		String sendUrl = sendurl+"?openid="+openid+"&spbill_create_ip="+spbill_create_ip+"&amount="+amount+"&partner_trade_no="+partner_trade_no;
		System.out.println("sendUrl------------->:" + sendUrl);
		return getUrl(sendUrl);
	}
	
	
	public static void main(String[] args){  
		SendUtil s=new SendUtil();
		String partner_trade_no=System.currentTimeMillis()+"";
		String rt=s.sendMsg("oNovFw-YfJlWxPWGswlqfJn3Bxb8", "116.231.50.222", "100",partner_trade_no);
		System.out.println("rt----->"+rt);
	}

	/** 
	 * @Title: getUrl 
	 * @Description: 获取地址
	 * @param urlString
	 * @return    
	 */ 
		
	public String getUrl(String urlString) {
		StringBuffer sb = new StringBuffer();
		try {
			//网络通信类
			URL url = new URL(urlString);
			//远程通信类
			URLConnection conn = url.openConnection();
			
			conn.setReadTimeout(15000);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			for (String line = null; (line = reader.readLine()) != null;) {
				sb.append(line + "\n");
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		String result = "";
		try {
			result = URLDecoder.decode(sb.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("--------------result"+result);
		return result;
	}

}
