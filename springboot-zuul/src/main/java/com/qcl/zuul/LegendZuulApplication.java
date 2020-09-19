package com.qcl.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 主启动
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/19
 */
@SpringBootApplication
@EnableZuulProxy // 开启Zuul的网关功能
@EnableDiscoveryClient
public class LegendZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegendZuulApplication.class, args);
    }
}