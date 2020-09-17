package com.legend.elasticsearch.config;


import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;

/**
 * HttpClient模拟发送es接口请求
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/17
 */
@Configuration
public class EsConfig {

    /**
     * es的获取数据
     *
     * @return
     */
    public String get() {
        //过时替换
        //DefaultHttpClient httpClient = new DefaultHttpClient();

        //创建HttpClient客户端
        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = "http://localhost:5601/api/console/proxy?path=%2Fitem%2F_mapping&method=GET";
        HttpPost httpPost = new HttpPost(url);

        //添加请求头参数
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.addHeader("kbn-name", "kibana");
        httpPost.addHeader("kbn-xpack-sig", "06a2cb70fb6490cad5269e9dc13a0c7d");
        httpPost.addHeader("kbn-xsrf", "kbn-version:6.6.0");
        httpPost.addHeader("kbn-version", "6.6.0");

        // 执行请求
        HttpResponse response = null;
        JSONObject jsonObject = null;
        try {
            response = httpClient.execute(httpPost);
            String json2 = EntityUtils.toString(response.getEntity(), "utf-8");
            jsonObject = JSONObject.parseObject(json2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 打印执行结果
        System.out.println(jsonObject);

        return jsonObject.toJSONString();
    }


    /**
     * post请求
     *
     * @param url
     * @return
     */
    public static JSONObject post(String url) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        /**
         * curl -XPOST 'localhost:9200/bank/_search?pretty' -d '
         '
         */
//            String url = "http://101.236.47.119:9200/redis-log-2018.07.26/doc/_search?pretty";
        HttpPost httpPost = new HttpPost(url);

        // 设置请求的header
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
//            String param="{"+
//                    "\"query\": { \"match_all\": {} },"+
//                    "\"from\": 10,"+
//                    "\"size\": 10"+
//                    "}";
        String param = "{" +
                "\"query\": { \"match_phrase\": {\"biztype\":\"排队的 代理IP为:\"} }," +
                "\"from\": 1," +
                "\"size\": 9000" +
                "}";
//            String param="{"+
//                    "  \"query\": {"+
//                    "    \"bool\": {"+
//                    "      \"should\": ["+
//                    "        { \"match\": { \"biztype\": \"排队的代理IP为:\" } }"+
//                //    "        { \"match\": { \"address\": \"lane\" } }"+
//                    "      ]"+
//                    "    }"+
//                    "  }"+
//                    "}";
        // 设置请求的参数
        JSONObject jsonParam = JSONObject.parseObject(param);


        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        JSONObject jsonObject = null;
        try {

            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            String json2 = EntityUtils.toString(response.getEntity(), "utf-8");
            jsonObject = JSONObject.parseObject(json2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 打印执行结果
        return jsonObject;

    }
}
