package com.sakura.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class HttpHelp {
    
    private static final Logger log = LoggerFactory.getLogger(HttpHelp.class);
    
    public static JSONObject get(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        JSONObject jsonObject = null;
        try{
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null){
                String result = EntityUtils.toString(entity,"utf-8");
                jsonObject = JSONObject.parseObject(result);
            }

        }catch (Exception e){
            log.error("Http Get 请求失败："+e.getMessage());
        }
        return jsonObject;
    }

    /**
     *
     * @param url 请求地址
     * @param jsonStr 请求数据的json字符串
     * @return
     */
    public static JSONObject post(String url,String jsonStr){
        JSONObject jsonObject = null;
        try{
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost();
            post.setHeader("Content-type","application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
            StringEntity requestEntity = new StringEntity(jsonStr, Charset.forName("UTF-8"));
            requestEntity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            requestEntity.setContentType("application/json");
            post.setEntity(requestEntity);
            HttpResponse response = httpclient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null){
                String result = EntityUtils.toString(entity,"utf-8");
                jsonObject = JSONObject.parseObject(result);
            }
        }catch (Exception e){
            log.error("Http Post 请求失败:"+e.getMessage());
        }
        return jsonObject;
    }
}
