package com.legend.elasticsearch.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * restTemplate注入请求参数通过拦截器的方式
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/17
 */
public class UserAgentInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();

        //添加请求头参数
        headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3314.0 Safari/537.36 SE 2.X MetaSr 1.0");
        headers.add("Content-Type", "application/json;charset=utf-8");
        headers.add("kbn-name", "kibana");
        headers.add("kbn-xpack-sig", "kbn-xpack-sig");
        headers.add("kbn-xsrf", "kbn-version:6.6.0");
        headers.add("kbn-version", "6.6.0");
        return execution.execute(request, body);
    }
}