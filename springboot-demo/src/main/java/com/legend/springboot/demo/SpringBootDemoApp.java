package com.legend.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 常用注解
 * <p>
 * 1.@RestController
 * 2.@EnableAutoConfiguration
 * 3.@ComponentScan
 * 4.@SpringBootApplication
 * 5.@SpringBootConfiguration
 * 6.@Configuration
 * 7.@PropertySource
 * 8.@Bean
 * 9.@Value
 * 10.@ConfigurationProperties
 * 11.@EnableConfigurationProperties
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/15
 */
@SpringBootApplication
public class SpringBootDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApp.class, args);
    }
}
