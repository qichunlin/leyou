package com.legend.elasticsearch;

import com.legend.elasticsearch.interceptor.UserAgentInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;

/**
 * 主启动类
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/17
 */
@SpringBootApplication
public class ElasticsearchAppMain {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchAppMain.class, args);
    }

    /**
     * 注入RestTemplate 到容器中
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 注入RestTemplate 到容器中添加请求信息
     *
     * @return
     */
    /*@Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(10000);
        httpRequestFactory.setConnectTimeout(100000000);
        httpRequestFactory.setReadTimeout(5000000);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.setInterceptors(Collections.singletonList(new UserAgentInterceptor()));
        return restTemplate;
    }*/
}
