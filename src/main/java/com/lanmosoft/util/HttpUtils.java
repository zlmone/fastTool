package com.lanmosoft.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtils {

    final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * Post 请求超时时间和读取数据的超时时间均为2000ms。
     *
     * @param urlPath       post请求地址
     * @param parameterData post请求参数
     * @return String json字符串，成功：code=1001，否者为其他值
     * @throws Exception 链接超市异常、参数url错误格式异常
     */
    public static String doPost(String urlPath, String parameterData, String who, String ip) throws Exception {

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
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("accept", "*/*");
        httpURLConnection.setRequestProperty("connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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

            inputStream = httpURLConnection.getInputStream(); // 真正的发送请求到服务端
            
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            
            //inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

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

    public static String doPost(String url, Map<String, Object> params) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }

        // no matter for the last '&' character

        return doPost(url, sb.toString().substring(0, sb.length()-1), "", "");
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
    
    public static String getToken(){//多头
    	String token = "";
    	try {
    		Map<String, Object> params = new HashMap<String, Object>();
            params.put("client_id","56b37f81b5564c4cb7383b94a89368dc");
            params.put("client_secret", "5aa6710f80264ad9bd3f514ab31d57a8");
            params.put("grant_type", "client_credentials");
            params.put("scopes", "multiple");
            String s = HttpUtils.doPost(" https://zx-api.juhe.cn/oauth2/token", params);
            System.err.println(s);
            JSONObject s1 = JSONObject.fromObject(s);
            token = s1.getString("access_token");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
        return token;
    } 
    
    public static String getToken_carrier(){
    	String token = "";
    	try {
    		Map<String, Object> params = new HashMap<String, Object>();
            params.put("client_id","56b37f81b5564c4cb7383b94a89368dc");
            params.put("client_secret", "5aa6710f80264ad9bd3f514ab31d57a8");
            params.put("grant_type", "client_credentials");
            params.put("scopes", "carrier");
            String s = HttpUtils.doPost(" https://zx-api.juhe.cn/oauth2/token", params);
            System.err.println(s);
            JSONObject s1 = JSONObject.fromObject(s);
            token = s1.getString("access_token");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
        return token;
    } 
    
    public static String decodeUnicode(String str) {
        Charset set = Charset.forName("UTF-16");
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher( str );
        int start = 0 ;
        int start2 = 0 ;
        StringBuffer sb = new StringBuffer();
        while( m.find( start ) ) {
            start2 = m.start() ;
            if( start2 > start ){
                String seg = str.substring(start, start2) ;
                sb.append( seg );
            }
            String code = m.group( 1 );
            int i = Integer.valueOf( code , 16 );
            byte[] bb = new byte[ 4 ] ;
            bb[ 0 ] = (byte) ((i >> 8) & 0xFF );
            bb[ 1 ] = (byte) ( i & 0xFF ) ;
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append( String.valueOf( set.decode(b) ).trim() );
            start = m.end() ;
        }
        start2 = str.length() ;
        if( start2 > start ){
            String seg = str.substring(start, start2) ;
            sb.append( seg );
        }
        return sb.toString() ;
    }
    //徐少华:TASKYYS100000201805241418490691521373
    //同盾:TASKYYS100000201805241630480661532218，TASKYYS100000201805241640500331922884，TASKYYS100000201805241652450310473604
    //task_id:10c383085f1611e897da00163e13b250
    //openid:JH6e468c04c630eabd377787eebb50d264
    public static void main(String[] args) throws Exception {
    	/*String token = HttpUtils.getToken_carrier();
    	Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", "13486491426");
        params.put("password", "199369");
        params.put("user_id", "213124124");
        //params.put("mobile", "18606627062");
        System.out.println(token);
        //String s = RequestCloud.sendPost("https://zx-api.juhe.cn/carrier/v1/tasks", JSONObject.fromObject(params).toString(),token);
    	String s = RequestCloud.sendGet("https://zx-api.juhe.cn/carrier/v1/tasks/10c383085f1611e897da00163e13b250/status", "",token);
        //JSONObject s1 = JSONObject.fromObject(s);
        String s1 = HttpUtils.decodeUnicode(s);
        System.out.println(s1);*/
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("task_id", "TASKYYS100000201805250912330661530486");
    	//params.put("channel_code", "100000");
    	//params.put("real_name", "徐少华");
    	//params.put("identity_code", "341224198703012321");
    	params.put("user_name", "17612183965");
    	params.put("user_pass", "199369");
    	params.put("task_stage", "INIT");
    	params.put("request_type", "submit");
    	//params.put("task_id", "TASKYYS100000201805241406310720980426");
    	String s = HttpUtils.doPost("https://api.shujumohe.com/octopus/yys.unify.acquire/v3?partner_code=chaorendai_mohe&partner_key=53675f6abacb44319d50d7f84a2d5a85", params);
    	System.out.println(s);
    	/*Map<String, Object> params = new HashMap<String, Object>();
    	//params.put("task_id", "TASKYYS100000201805241406310720980426");
    	params.put("channel_type", "YYS");
    	params.put("channel_code", "100000");
    	params.put("real_name", "李攀博");
    	params.put("identity_code", "421181199306098711");
    	params.put("user_mobile", "17612183965");
    	//params.put("task_id", "TASKYYS100000201805241406310720980426");
    	String s = HttpUtils.doPost("https://api.shujumohe.com/octopus/task.unify.create/v3?partner_code=chaorendai_mohe&partner_key=53675f6abacb44319d50d7f84a2d5a85", params);
    	System.out.println(s);*/
	}
    
}
