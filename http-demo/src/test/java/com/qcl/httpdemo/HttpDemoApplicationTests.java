package com.qcl.httpdemo;

import com.qcl.httpdemo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate实现远程调用
 *
 * @author legend
 * @version 1.0
 * @date 2020/9/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HttpDemoApplication.class)
public class HttpDemoApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void httpGet() {
        User user = this.restTemplate.getForObject("http://localhost:8099/user/1", User.class);
        System.out.println(user);
    }

}
