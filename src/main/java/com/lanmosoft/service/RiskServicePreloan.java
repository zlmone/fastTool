package com.lanmosoft.service;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.lanmosoft.webapp.webmodel.PreloanQueryResponse;
import com.lanmosoft.webapp.webmodel.PreloanSubmitResponse;

public class RiskServicePreloan {

    private static final Log    LOGGER                  = LogFactory.getLog(RiskServicePreloan.class);

    // 连接建立超时时间
    private static final int CONNECT_TIME_OUT        = 1 * 1000;
    // 设置读取超时，建议设置为3000ms。若同时调用了信息核验服务，请与客户经理协商确认具体时间”
    private static final int SOCKET_TIME_OUT         = 3 * 1000;
    // 连接不够用时从connection manager获取连接等待超时时间
    private static final int CONNECT_REQUST_TIME_OUT = 1 * 1000;

    private static CloseableHttpClient httpClient    = null;
    private final static Object syncLock             = new Object();
    private final static int RETRY_TIMES             = 5; // 最大重试次数

    private static final String submitUrl            = "https://api.tongdun.cn/credit/event/v3";
    private static final String PARTNER_CODE         = "chaorendai"; // 合作方标识
    private static final String PARTNER_KEY          = "testkey"; // 合作方密钥
    private static final String PARTNER_APP          = "chaorendai_web"; // 应用名
    private static final String EVENT_TYPE           = "loan"; // 事件类型
    private static final String VERSION              = "v1"; // 表单版本号

    /**
     * submit接口示例
     *
     * @param params
     * @return
     */
    public PreloanSubmitResponse apply(Map<String, Object> params) {

        PreloanSubmitResponse submitResponse = new PreloanSubmitResponse();
        String url = new StringBuilder().append(submitUrl).append("?partner_code=").append(PARTNER_CODE).append("&partner_key=").append(PARTNER_KEY).append("&app_name=").append(PARTNER_APP).append("&event_type=").append(EVENT_TYPE).append("&version=").append(VERSION).toString();
        HttpPost httppost = new HttpPost(url);
        setPostParams(httppost, params);
        CloseableHttpResponse response = null;

        try {
            response = getHttpClient(url).execute(httppost, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            
            return com.alibaba.fastjson.JSON.parseObject(result.toString().trim(), PreloanSubmitResponse.class);
        } catch (Exception e) {
            LOGGER.error("[RiskServicePreloan] apply throw exception, details: " + e);
        } finally {
            try {
                if (response != null) response.close();
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        return submitResponse;
    }
    /**
     * 对http请求做相关配置.
     *
     * @param httpRequestBase
     */
    private static void config(HttpRequestBase httpRequestBase) {
        // 配置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_REQUST_TIME_OUT).setConnectTimeout(CONNECT_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT).build();
        httpRequestBase.setConfig(requestConfig);
    }

    /**
     * 获取HttpClient对象
     *
     * @return
     */
    public static CloseableHttpClient getHttpClient(String url) {
        String hostname = url.split("/")[2];
        int port = 80;
        if (hostname.contains(":")) {
            String[] arr = hostname.split(":");
            hostname = arr[0];
            port = Integer.parseInt(arr[1]);
        }
        if (httpClient == null) {
            synchronized (syncLock) {
                if (httpClient == null) {
                    httpClient = createHttpClient(200, 40, 100, hostname, port);
                }
            }
        }
        return httpClient;
    }

    /**
     * 创建HttpClient对象 这里只设置一个路由.
     *
     * @return
     */
    public static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int maxRoute, String hostname, int port) {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        org.apache.http.config.Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainsf).register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 最大连接数
        cm.setMaxTotal(maxTotal);
        // 每个默认基础路由的连接数
        cm.setDefaultMaxPerRoute(maxPerRoute);
        HttpHost httpHost = new HttpHost(hostname, port);
        // 每个路由的最大连接数增加
        cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);
        SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).build();
        cm.setDefaultSocketConfig(socketConfig);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {

            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= RETRY_TIMES) {               // 如果已经重试了5次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {  // 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) { // 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {   // 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {           // SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(
                httpRequestRetryHandler).build();
        return httpClient;
    }

    /**
     * 设置post请求参数.
     *
     * @param httpost
     * @param params
     */
    private static void setPostParams(HttpPost httpost, Map<String, Object> params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
    }

    private static void closePool() {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
    public static void main(String[] args) {
    	Map<String, Object> params = new HashMap<String, Object>();
        params.put("company_type", "私营");
        params.put("coborrower_home_address", "浙江省杭州市西湖区古荡新村2幢201");
        params.put("career", "半专业人员");
        params.put("contact3_relation", "test");
        params.put("occupation", "见习专员");
        params.put("customer_channel", "test");
        params.put("contact5_name", "test");
        params.put("work_phone", "0571-111111111");
        params.put("surety_name", "刘能");
        params.put("contact1_id_number", "test");
        params.put("contact5_id_number", "test");
        params.put("loan_purpose", "车贷");
        params.put("coborrower_id_number", "321282555555555555");
        params.put("company_address", "test");
        params.put("coborrower_phone", "0571-10101010");
        params.put("surety_phone", "0571-222222222");
        params.put("token_id", "test");
        params.put("house_property", "有房");
        params.put("contact2_id_number", "test");
        params.put("diploma", "研究生");
        params.put("annual_income", "100000-200000");
        params.put("id_number", "123123123123123000");
        params.put("biz_code", "test");
        params.put("surety_id_number", "321282333333333333");
        params.put("card_number", "6333380402564890000");
        params.put("device_id", "test");
        params.put("contact1_mobile", "test");
        params.put("account_phone", "0571-42331233");
        params.put("monthly_income", "12000以上");
        params.put("loan_amount", "10000");
        params.put("qq_number", "313131313");
        params.put("apply_province", "四川");
        params.put("surety_mobile", "15223456789");
        params.put("contact4_relation", "test");
        params.put("contact5_mobile", "test");
        params.put("loan_term", "12");
        params.put("account_mobile", "13113131313");
        params.put("contact3_mobile", "test");
        params.put("organization_address", "浙江省杭州市阿里巴巴西溪园区");
        params.put("work_time", "1年以下");
        params.put("contact3_id_number", "test");
        params.put("contact3_name", "test");
        params.put("coborrower_name", "王五");
        params.put("loan_date", "2015-11-19");
        params.put("registered_address", "浙江省杭州市西湖区古荡新村2幢101");
        params.put("applyer_type", "在职");
        params.put("is_cross_loan", "否");
        params.put("industry", "金融业");
        params.put("surety_company_address", "浙江省杭州市下城区潮王路18号");
        params.put("contact2_name", "test");
        params.put("resp_detail_type", "test");
        params.put("account_email", "212121212@qq.com");
        params.put("surety_home_address", "浙江省杭州市西湖区古荡新村");
        params.put("home_address", "浙江省杭州市西湖区古荡新村2幢101");
        params.put("marriage", "未婚");
        params.put("account_name", "张三");
        params.put("contact5_relation", "test");
        params.put("house_type", "商品房");
        params.put("contact_address", "浙江省杭州市西湖区古荡新村2幢101");
        params.put("black_box", "test");
        params.put("contact4_id_number", "test");
        params.put("contact1_name", "test");
        params.put("contact2_relation", "test");
        params.put("coborrower_mobile", "17012345678");
        params.put("contact4_name", "test");
        params.put("ip_address", "test");
        params.put("coborrower_company_address", "杭州市江干区2号大街928号");
        params.put("contact1_relation", "test");
        params.put("contact4_mobile", "test");
        params.put("event_occur_time", "2016-03-01 08:16:30");
        params.put("organization", "阿里巴巴西溪园区");
        params.put("contact2_mobile", "test");
        RiskServicePreloan service = new RiskServicePreloan();
        PreloanSubmitResponse riskPreloanResponse = service.apply(params);
        System.out.println(riskPreloanResponse.toString());
        if (riskPreloanResponse.getSuccess()) {
            // 调用成功，获取报告编号
            String reportId = riskPreloanResponse.getReport_id();
            //获取风险详情
            PreloanQueryResponse preloanQueryResponse = riskPreloanResponse.getData();
            //其他处理 ...
        }
        // 关闭连接池
        closePool();
	}
}
            
