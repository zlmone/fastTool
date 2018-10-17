package com.lanmosoft.util;

import com.alibaba.fastjson.JSON;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;

/***
 * 提供部分产品的查询方式demo
 *  本处提供的demo 仅供给参考，不提供真实数据
 *  
 *  
 * @author zmxy
 * @version $Id: TestScoreGet.java, v 0.1 2016-1-22 上午10:34:05 zmxy Exp $
 */
public class TestScoreGet {
    //芝麻开放平台地址
    private String gatewayUrl     = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id
    private String appId          = "300002295";
    //商户 RSA 私钥
    private String privateKey     = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALa7txFr8v4kPbOMGEgJDMJgZ/Tb6Y9ZN8dXgwF9GzdVWGE/oZFAHSb7t5lNQF16RhP9UiZLo1CwXA19Qn4k7qxSLpJb7ql/K6J++jZ1V0PqkAcfGVSDuz89RRsPSB3+xsaiZVucc2srMI98dwJw6YGMuu7h/tZXxFN+Rg887PsTAgMBAAECgYBcYVIPsK9L5C59UtoDsO+OZQapN3RAUC5uzMMiQYk81By2LWEU+YDDcIZFLlTX7TT4bhzV129dH4uqFiVDeJBgGuNMMJTOJAVWZvlaUV3MZ5ukB6SowOHYGWeKjIMmCeUdbnRu+Qbe1KJKx5EbB/lZsnwm1/wgs3yKLQI+hgpioQJBAOCpTOi7pEUfmJcEoogKAi33FztPErfwiKEGgLioBAxbPDKN43bFF7CtvbcfW4pwqrS+F7zesvwaP4yVtyV2gIcCQQDQOSe/5ug62skfj/JbVCKiO9Fr3ekc5tyZtoVqBYxa++FRoaZXZUOI19E7KNbUBI4u8du32PRp7wSSTr6TdBAVAkAi3OLcctHpECAjv0phhfwUmfV29KxY8vCg3Ft5TElkiXvr9GzJ74ffmPaJxkn0T8uNxLn2SzDHQmN+M3V6UMtdAkBhxlTqAc2OxWmnznopdIqGz7v5AM0T6zwvWcuwC0bq+lfFczie1UgvG9h+BSFLkZAKbzuTSucKT1hAM4W9y8Q5AkBaJJaRYoRhrorCmzNh8k801iRFo1ehmanEFR3lQzyae6lr/pJIcQ/6UsTPPA8JvjO4kwl9Gznipw81RhcCc/ea";
    //芝麻 RSA 公钥
    private String zhimaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOOI7KH4WH//11ZNrNm8KPsii1Z2pMyal5V64JnI/DwWEaw/pqohHgJBRJRMyhsHf3ggF5mlCtjZicgOW6VS51yWw6/f+lNBgj7aEp503hCX4MaX2g1WUIMSJ+TF6UF8VWA4vecKfvvCZYkMH2iI8W/ynsP+CECLKSBeByUtXU5QIDAQAB";

    /**
     * 获取芝麻粉
     */
    public void testZhimaCreditWatchlistGet() {
        ZhimaCreditScoreGetRequest req = new ZhimaCreditScoreGetRequest();
        req.setProductCode("w1010100100000000001");// 必要参数        
        req.setOpenId("268816231939676969685782895");// 必要参数        
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey,
            zhimaPublicKey);
        try {
            ZhimaCreditScoreGetResponse response = client.execute(req);
            System.out.println(JSON.toJSON(response));
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * main 方法
     * 
     * @param args
     */
    public static void main(String[] args) {
        TestScoreGet result = new TestScoreGet();
        result.testZhimaCreditWatchlistGet();
    }
}