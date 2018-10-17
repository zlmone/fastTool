package com.lanmosoft.util;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.internal.util.RSACoderUtil;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthorizeRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthqueryRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaAuthInfoAuthqueryResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;


public class Test {
	
    //芝麻开放平台地址
    private static final String URL  = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id
    private static final  String APPID  = "300002295";
    //商户 RSA 私钥
    private static final  String PRIKEY  = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALa7txFr8v4kPbOMGEgJDMJgZ/Tb6Y9ZN8dXgwF9GzdVWGE/oZFAHSb7t5lNQF16RhP9UiZLo1CwXA19Qn4k7qxSLpJb7ql/K6J++jZ1V0PqkAcfGVSDuz89RRsPSB3+xsaiZVucc2srMI98dwJw6YGMuu7h/tZXxFN+Rg887PsTAgMBAAECgYBcYVIPsK9L5C59UtoDsO+OZQapN3RAUC5uzMMiQYk81By2LWEU+YDDcIZFLlTX7TT4bhzV129dH4uqFiVDeJBgGuNMMJTOJAVWZvlaUV3MZ5ukB6SowOHYGWeKjIMmCeUdbnRu+Qbe1KJKx5EbB/lZsnwm1/wgs3yKLQI+hgpioQJBAOCpTOi7pEUfmJcEoogKAi33FztPErfwiKEGgLioBAxbPDKN43bFF7CtvbcfW4pwqrS+F7zesvwaP4yVtyV2gIcCQQDQOSe/5ug62skfj/JbVCKiO9Fr3ekc5tyZtoVqBYxa++FRoaZXZUOI19E7KNbUBI4u8du32PRp7wSSTr6TdBAVAkAi3OLcctHpECAjv0phhfwUmfV29KxY8vCg3Ft5TElkiXvr9GzJ74ffmPaJxkn0T8uNxLn2SzDHQmN+M3V6UMtdAkBhxlTqAc2OxWmnznopdIqGz7v5AM0T6zwvWcuwC0bq+lfFczie1UgvG9h+BSFLkZAKbzuTSucKT1hAM4W9y8Q5AkBaJJaRYoRhrorCmzNh8k801iRFo1ehmanEFR3lQzyae6lr/pJIcQ/6UsTPPA8JvjO4kwl9Gznipw81RhcCc/ea";
    //芝麻 RSA 公钥
    private static final  String PUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOOI7KH4WH//11ZNrNm8KPsii1Z2pMyal5V64JnI/DwWEaw/pqohHgJBRJRMyhsHf3ggF5mlCtjZicgOW6VS51yWw6/f+lNBgj7aEp503hCX4MaX2g1WUIMSJ+TF6UF8VWA4vecKfvvCZYkMH2iI8W/ynsP+CECLKSBeByUtXU5QIDAQAB";
    //统一字符集
    private static final String CHARSET  = "UTF-8";
    
    /**
     * 查询授权信息
     */
    public void testZhimaAuthInfoAuthorize() {
        ZhimaAuthInfoAuthorizeRequest req = new ZhimaAuthInfoAuthorizeRequest();
        req.setIdentityType("2");// 身份标识
        req.setChannel("apppc"); // PC端
        //必要参数 state: 用于给商户提供透传的参数，芝麻会将此参数透传给商户;
        req.setBizParams("{\"auth_code\":\"M_APPPC_CERT\",\"state\":\"100111211\"}");
        req.setIdentityParam("{\"certNo\":\"330621198710114617\",\"certType\":\"IDENTITY_CARD\",\"name\":\"陈金赛\"}");// 必要参数        
        DefaultZhimaClient client = new DefaultZhimaClient(URL,APPID,CHARSET,PRIKEY,PUBKEY);
        try {
            String url = client.generatePageRedirectInvokeUrl(req);
            System.out.println(url);
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取目标用户的open_id,没有openid,走授权
     */
    public static void testZhimaAuthInfoReq() {
        ZhimaAuthInfoAuthqueryRequest req = new ZhimaAuthInfoAuthqueryRequest();
     // 0：芝麻信用开放账号ID 1：按照手机号进行授权 2:按照身份证+姓名进行授权 3通过公安网验证进行授权 4.通过人脸验证进行授权
        req.setIdentityType("2");        
        req.setIdentityParam("{\"certNo\":\"421181199306098711\",\"certType\":\"IDENTITY_CARD\",\"name\":\"李攀博\"}");// 必要参数        
        DefaultZhimaClient client = new DefaultZhimaClient(URL,APPID,CHARSET,PRIKEY,PUBKEY);
        try {
        	// 如果正常返回,直接在对象里面获取结果值
            ZhimaAuthInfoAuthqueryResponse response = client.execute(req);
            System.out.println(JSON.toJSON(response));
/*
 成功响应报文
 {
 				"authorized": true,
  				"body": "{\"success\":true,\"authorized\":true,\"open_id\":\"268816231939676969685782895\"}",
  				"openId": "268816231939676969685782895",
  				"params": {
    			"params": "faeSa69w3kOL+ZRAuHykvufu6ogiqxA48l5hzPutKItPy/2HKtdznbzvLhutsY6KTqLgwS/Af1GN1Y7MLh+XDFtjVUf5kF/npUspRkyFUCPpASJAMxeK56vaELnZ4yCVFjVseOBYlCn4YOCZWmBpu02l7IctE6ZP3r1o4HUR+sMQYlYu2xFHfXiqX2g0vdQn81pJ/fcQ4OuBzq1d3c+qniBXf3iESni1m21nMjxeUclzCdZwXWCI1aIYQnDFgVo/58kJ9CTw/6FrffUcP+c6zvzdM8RNCefiGGuF5Vef+C6tck1kBaYoYT6BS5u4ahYteoiG+XJgzLKF+BqAplhq/A=="
  				},
  				"success": true
	}
// 
  失败响应报文
           {
  				"authorized": false,
  				"body": "{\"success\":true,\"authorized\":false}",
  				"params": {
    			"params": "lGYId9CaU8McGDd8U0ZMM8Pky1/ulOE44LkgGcrWrdzmEtU1JBT5M+Jjd5e27bz45d/CNEqbwmwtg3VsozZTol42YKNi+MYzvvyZEOsUT9F68qiIPxxlOv3vGJsUpB+LJMOI8ZU7eXtHoCnbarQSOvtSylilp6dpIbAIYxbPPvSkj+7f1TiSne/7Re/XZ1qVVM18yTJyleCr+WlOe7o59lZAoiZ5bDoP2ta8MIjwlxz4/1d+IYe0f2BoZQa/9oDqJZ0MNQ0iPCbhqBKRuShPCQe8afEyOTTI0p+cROjwWTz0dwP7QXnkWBvzX3hrWXImdk7JszOVoSxAAAfwcVXNxw=="
  			},
  			"success": true
		  }
*/
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 测试查询芝麻分.
     * @throws ZhimaApiException 
     */
    public static void testQueryScore() throws ZhimaApiException {
        ZhimaCreditScoreGetRequest creditScoreGetRequest = new ZhimaCreditScoreGetRequest();
        creditScoreGetRequest.setPlatform("zmop"); // 开放平台,zmop代表芝麻开放平台
        creditScoreGetRequest.setChannel("apppc"); // pc端
        //transactionId，该标记是商户每次请求的唯一标识。建议使用uuid进行传递，
        creditScoreGetRequest.setTransactionId(UUID.randomUUID().toString());
        creditScoreGetRequest.setProductCode("w1010100100000000001"); // 商户配置那块儿的产品Code
        creditScoreGetRequest.setOpenId("268801608926476769048254213"); // appid,每个人的标识
        DefaultZhimaClient client = new DefaultZhimaClient(URL,APPID,CHARSET,PRIKEY,PUBKEY);
        // 如果正常返回,直接在对象里面获取结果值
        ZhimaCreditScoreGetResponse creditScoreGetResponse = client.execute(creditScoreGetRequest);
        System.out.println(JSON.toJSON(creditScoreGetResponse));
        
/*
    成功查询响应报文
   {
  "bizNo": "ZM20160120014501763890a37b16614532255017521309",
  "body": "{\"success\":true,\"biz_no\":\"ZM20160120014501763890a37b16614532255017521309\",\"zm_score\":751}",
  "params": {
    "params": "aqiMCUg1VbZRqflPyVyLKgxShcvc3WoWLTFewqA9PvQjp6yuC99lYlGdr/Bf2FevQ2zemdPTixqQWnO7qdz0pT2x4VFWVJdldPgLVsRr37qWDcO5NjvNZzOuEYozzGSnzwhTyelqglAdzOOepM0ows+7sNVJriABtRdt1HH8gqc="
  },
  "success": true,
  "zmScore": "751"
  }
  失败查询响应报文
  {
  "body": "{\"success\":false,\"error_code\":\"ZMCREDIT.api_product_not_match\",\"error_message\":\"输入的产品码不正确\"}",
  "errorCode": "ZMCREDIT.api_product_not_match",
  "errorMessage": "输入的产品码不正确",
  "params": {
    "params": "vJTcbtVvo0/WEG01Flve0//EbE70clk7v84B7MVJZH59/HefdyAZ1JG4opTna5ANWvY8UArsGlVIAQ+9K0169ARyJ2yoGs+0Z/fHmrXmWNNqOpcuV56A1xX6E+VOpUth8Z8RqPFKnEq4/rFJRKPA9Uhue2KM9yj6hsAbF0ZXU74="
  },
  "success": false
 }
*/
    }

    /**
     * 自动生成页面授权的url.
     * @throws Exception
     */
    public static void testPageAuth() throws Exception {
        ZhimaAuthInfoAuthorizeRequest authInfoAuthorizeRequest = new ZhimaAuthInfoAuthorizeRequest();
        authInfoAuthorizeRequest.setChannel("apppc"); // PC端
        authInfoAuthorizeRequest.setPlatform("zmop"); // 开放平台
        // 0：芝麻信用开放账号ID 1：按照手机号进行授权 2:按照身份证+姓名进行授权 3通过公安网验证进行授权 4.通过人脸验证进行授权
        authInfoAuthorizeRequest.setIdentityType("2");
        Map<String, String> identityParams = new HashMap<String, String>();
        identityParams.put("certNo", "421181199306098711"); // 证件号码
        identityParams.put("name", "李攀博"); // 姓名
        identityParams.put("certType", "IDENTITY_CARD"); // 证件类型
        authInfoAuthorizeRequest.setIdentityParam(JSONObject.toJSONString(identityParams));
        DefaultZhimaClient client = new DefaultZhimaClient(URL,APPID,CHARSET,PRIKEY,PUBKEY);
        String pageAuthUrl = client.generatePageRedirectInvokeUrl(authInfoAuthorizeRequest);
        System.out.println(pageAuthUrl);
    }
    
    /**
     * 获取芝麻粉(GET方式)
     */
    public void testZhimaCreditWatchlistGet() {
        ZhimaCreditScoreGetRequest req = new ZhimaCreditScoreGetRequest();
        req.setProductCode("w1010100100000000001");// 必要参数        
        req.setOpenId("268816231939676969685782895");// 必要参数        
        DefaultZhimaClient client = new DefaultZhimaClient(URL,APPID,CHARSET,PRIKEY,PUBKEY);
        try {
        	// 如果正常返回,直接在对象里面获取结果值
            ZhimaCreditScoreGetResponse response = client.execute(req);
            System.out.println(JSON.toJSON(response));
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 处理回调后的参数,然后解密params
     * @param url 例如：http://xxxx.com?params=xxxxx&key1=xxxxx&key2=xxxxx
     * @throws Exception
     */
    public static void parseFromReturnUrl(String url) throws Exception {
        int index = url.indexOf("?");
        String urlParamString = url.substring(index + 1);
        String[] paraPairs = urlParamString.split("&");
        String encryptedParam = "";
        for (String paramPair : paraPairs) {
            String[] splits = paramPair.split("=");
            if ("params".equals(splits[0])) {
                encryptedParam = splits[1];
            }
        }
        String decryptedParam = RSACoderUtil.decrypt(URLDecoder.decode(encryptedParam, CHARSET),
            PRIKEY, CHARSET);
        //通过浏览器返回时，不需要decode
        System.out.println(URLDecoder.decode(decryptedParam, CHARSET));
/*
        params主要JSON参数如下：
                 名称	   	 	类型      	示例值    	 	备注
        success  		String 	success 	请求成功还是失败的标识
		error_code  	String 	000001    	失败时的错误码
		error_message	String	缺少appId	失败时的错误信息
		open_id			String	26881...	芝麻业务id
		state			String	239...		商户透传的值，芝麻不做解析
*/
    }
    
    public static void main(String[] args) throws Exception {
		Test.testQueryScore();
	}
}
