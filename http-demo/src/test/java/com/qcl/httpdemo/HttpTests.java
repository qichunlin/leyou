package com.qcl.httpdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcl.httpdemo.pojo.User;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * http测试类
 *
 * @author legend
 * @version 1.0
 * @date 2020/9/16
 */
public class HttpTests {

    CloseableHttpClient httpClient;

    /**
     * 序列化和反序列化工具
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void testGet() throws IOException {
        HttpGet request = new HttpGet("http://www.baidu.com");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    @Test
    public void testPost() throws IOException {
        HttpPost request = new HttpPost("http://www.oschina.net/");
        request.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    @Test
    public void testGetPojo() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8099/user/hello");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
        //反序列化为对象(对象的toString方法)
        User user = objectMapper.readValue(response, User.class);
        System.out.println(user);

        //序列化(json字符串)
        String writeValueAsString = objectMapper.writeValueAsString(user);
        System.out.println(writeValueAsString);
    }
}
