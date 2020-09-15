package com.legend.springboot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/15
 */
@RestController
public class DemoController {


    @GetMapping("/demo")
    public String demo(){
        System.out.println("hello");
        return "6666";
    }
}
