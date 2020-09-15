package com.legend.springboot.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 专门写一个属性读取类
 * <p>
 * ConfigurationProperties注解不提供读取  jdbc.properties-移到application.properties 配置文件的功能
 * <p>
 * 默认读取application.properties文件
 *
 * @author legend
 */
@ConfigurationProperties(prefix = "jdbc2")
public class JdbcProperties {

    /**
     * 属性名必须要和application.properties配置文件中的属性名相同
     */
    private String driverClassName;
    private String url;
    private String userName;
    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
