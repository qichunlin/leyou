package com.legend.elasticsearch.entity;

/**
 * Es的参数
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/17
 */
public class EsParam {

    private String path;
    private String method;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
