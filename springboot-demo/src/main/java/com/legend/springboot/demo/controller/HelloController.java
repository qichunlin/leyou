package com.legend.springboot.demo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 * <p>
 * 这个可以不用写SpringBootDemoApp主启动类
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/15
 */
//@EnableAutoConfiguration //启用自动配置
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String demo() {
        System.out.println("hello");
        return "6666";
    }

//    public static void main(String[] args) {
//        SpringApplication.run(HelloController.class, args);
//    }
}
