package com.lanmosoft.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils2 {

    final static Logger logger = LoggerFactory.getLogger(HttpUtils2.class);

    /**
     * Post 请求超时时间和读取数据的超时时间均为2000ms。
     *
     * @param urlPath       post请求地址
     * @param parameterData post请求参数
     * @return String json字符串，成功：code=1001，否者为其他值
     * @throws Exception 链接超市异常、参数url错误格式异常
     */
    
    //token:ZmNiODVjMzdlNzEzN2VjZDFjOGYxOWEy
    
    public static String doPost(String urlPath, String parameterData, String who, String ip,String token) throws Exception {

        if (null == urlPath || null == parameterData) { // 避免null引起的空指针异常
            return "";
        }
        URL localURL = new URL(urlPath);
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput(true);
        if (!StringUtils.isEmpty(who)) {
            httpURLConnection.setRequestProperty("who", who);
        }
        if (!StringUtils.isEmpty(ip)) {
            httpURLConnection.setRequestProperty("clientIP", ip);
        }
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        //httpURLConnection.setRequestProperty("auth_token", token);
        httpURLConnection.setRequestProperty("Authorization", token);
        httpURLConnection.setConnectTimeout(18000);
        httpURLConnection.setReadTimeout(18000);

        System.out.println(parameterData);
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuilder resultBuffer = new StringBuilder();
        String tempLine = null;

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(parameterData.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream(); // 真正的发送请求到服务端
            //inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
        return resultBuffer.toString();
        //return new String(resultBuffer.toString().getBytes(), "UTF-8");
    }

    public static String doPost(String url, Map<String, Object> params,String token) throws Exception {
    	String s =JSONObject.fromObject(params).toString();
        // no matter for the last '&' character

        return doPost(url, s, "", "",token);
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String who, String ip) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            if (!"".equals(param)) {
                urlNameString = urlNameString + "?" + param;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            if (!StringUtils.isEmpty(who)) {
                connection.setRequestProperty("who", who);
            }
            if (!StringUtils.isEmpty(ip)) {
                connection.setRequestProperty("clientIP", ip);
            }
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.warn("发送GET请求出现异常！", e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                logger.warn("fail to close inputStream.", e2);
            }
        }
        return result;
    }

    public static String sendGet(String url, Map<String, Object> params) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }

        // no matter for the last '&' character

        return sendGet(url, sb.toString(), "", "");
    }
    
    public static String getToken(){
    	String token = "";
    	try {
    		Map<String, Object> params = new HashMap<String, Object>();
            params.put("apiKey","886b379665a18dd5cefdef1ffb16372b");
            params.put("securityKey", "0a42524d621533613fe20fbcba4c742612088496");
            params.put("refresh", "1");
            String s = HttpUtils2.doPost("https://api.dsdatas.com/credit/api/token", params,"");
            JSONObject s1 = JSONObject.fromObject(s);
            JSONObject s2 = JSONObject.fromObject(s1.getJSONObject("data"));
            token = s2.getString("token");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
        return token;
    } 
    
    
  //apiKey886b379665a18dd5cefdef1ffb16372b
    //securityKey:0a42524d621533613fe20fbcba4c742612088496
    public static void main(String[] args) throws Exception {
    	String s = HttpUtils2.getToken();
    	Map<String, Object> params = new HashMap<String, Object>();
        params.put("name","王克健");
        params.put("timestamp", System.currentTimeMillis());
        params.put("idCard", "612425199612233717");
        params.put("mobile", "18667426556");
        String s1 = HttpUtils2.doPost("https://api.dsdatas.com/blackData/fireEyesBlackv2", params,s);
        JSONObject s2 = JSONObject.fromObject(s1);
        System.out.println(s2);
	}
    
}
