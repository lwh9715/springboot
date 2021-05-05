package com.spring.springutil.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    /**
     * post请求
     */
    public static JSONObject doPost(String url, Map<String, String> params) {
        JSONObject json = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            // 设置请求和传输超时时间30秒

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();

            httpPost.setConfig(requestConfig);

            // 设置请求头参数
            httpPost.setHeader("dealer-id", "12345678");//代码
            httpPost.setHeader("request-id", "440881199601050615");// request的 id
            // 设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            System.out.println("doPost请求url:" + httpPost.toString());
            // 执行post请求
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String respStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            json = JSON.parseObject(respStr);
            response.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * get请求
     */
    public static JSONObject doGet(String url, Map<String, String> params) {
        JSONObject json = null;
        try {
            // 设置参数
            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
            StringBuilder urlBuffer = new StringBuilder();
            urlBuffer.append(url + "?");
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                Object key = entry.getKey();
                urlBuffer.append(key);
                urlBuffer.append('=');
                String value = URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8");//参数加密
                urlBuffer.append(value);
                if (it.hasNext()) {
                    urlBuffer.append("&");
                }
            }
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(urlBuffer.toString());
            System.out.println("doGet请求url:" + urlBuffer.toString());
            // 设置请求和传输超时时间30秒
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();

            httpGet.setConfig(requestConfig);

            // 设置请求头参数
            httpGet.setHeader("dealer-id", "12345678");//代码
            httpGet.setHeader("request-id", "440881199601050615");// request的 id
            // 执行get请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String respStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            json = JSON.parseObject(respStr);
            response.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}