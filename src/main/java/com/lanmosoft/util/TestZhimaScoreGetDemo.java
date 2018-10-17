package com.lanmosoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;
public class TestZhimaScoreGetDemo {
    // 芝麻信用开放平台网关地址
    private static String gatewayUrl     = "https://zmopenapi.zmxy.com.cn/openapi.do";
    // 商户应用 Id
    private static String appId          = "300002295";
    // 商户 RSA 2048位私钥(本处为一个示例值)
    private static String privateKey     = "***";
    // 芝麻 RSA 公钥(本处为一个示例值)
    private static String zhimaPublicKey = "***";
    /**
    * 发起芝麻分请求
    */
    public void testGetScore() {
        ZhimaCreditScoreGetRequest req = new ZhimaCreditScoreGetRequest();
        String transactionId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                               + UUID.randomUUID().toString(); //创建业务流水凭证，以当前时间作为前缀，uuid为后缀
        req.setTransactionId(transactionId); // 必要参数，业务流水凭证
        req.setProductCode("w1010100100000000001"); // 必要参数，这个值对于芝麻分产品是固定的，无需修改
        req.setOpenId("268806305103918675035174490"); // 必要参数，授权获得的openid
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey,
            zhimaPublicKey);
        try {
            ZhimaCreditScoreGetResponse response = client.execute(req);
            // TODO 将业务流水凭证与响应内容持久化到DB，便于后续对账
            System.out.println("transactionId=" + transactionId + ";请求完整响应=" + response.getBody());
            if (response.isSuccess()) {
                //打印正常响应
                System.out.println("用户芝麻信用评分=" + response.getZmScore());
            } else {
                //打印异常原因
                System.out.println(response.getErrorCode());
                System.out.println(response.getErrorMessage());
            }
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        TestZhimaScoreGetDemo result = new TestZhimaScoreGetDemo();
        result.testGetScore();
    }
}