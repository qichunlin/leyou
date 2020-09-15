package com.legend.user.config;

import com.legend.user.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 定义配置类,注册拦截器
 * 1.声明该类是一个java配置类
 * 2.实现WebMvcConfigurer接口
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/14
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    /**
     * 重写接口中的addInterceptors方法，添加自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有(拦截多级)
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }
}
