package com.legend.elasticsearch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.legend.elasticsearch.config.EsConfig;
import com.legend.elasticsearch.entity.EsParam;
import com.legend.elasticsearch.entity.Item;
import com.legend.elasticsearch.respository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品控制器
 * 具体可以参考 com.legend.elasticsearch.IndexTest测试类
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/17
 */
@RestController
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    /**
     * 业务类
     */
    @Autowired
    private ItemRepository itemRepository;

    /**
     * 序列化和反序列化使用
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 远程调用使用
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 创建索引库和类型测试
     *
     * @return
     */
    @GetMapping("/create/index")
    public String createIndex() {
        System.out.println("hello");
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);

        Item itemRepositoryById = itemRepository.findById(1L).get();

        StringBuffer result = null;
        try {
            result.append(objectMapper.writeValueAsString(itemRepositoryById));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    /**
     * 远程调用es的api(基于restTemplate不能用)
     *
     * @return
     */
    @Deprecated
    @GetMapping("/es/rest/api")
    public String restEsApi() {
        System.out.println("hello");

        //添加请求头参数
        Map<Object, Object> uriMapParam = new HashMap<Object, Object>();
        uriMapParam.put("Content-Type", "application/json;charset=utf-8");
        uriMapParam.put("kbn-name", "kibana");
        uriMapParam.put("kbn-xpack-sig", "kbn-xpack-sig");
        uriMapParam.put("kbn-xsrf", "kbn-version:6.6.0");
        uriMapParam.put("kbn-version", "6.6.0");
        log.info("当前Map集合长度为:{}", uriMapParam.size());

        EsParam esParam = new EsParam();
        esParam.setPath("/item");
        esParam.setMethod("HEAD");
        String result = restTemplate.postForObject("http://localhost:5601/api/console/proxy?path=/item&method=HEAD", uriMapParam, String.class);
        return result;
    }


    @Autowired
    private EsConfig esConfig;

    /**
     * 远程调用es的api(基于HttpClient不能用)
     * <p>
     * http://localhost:8012/es/http/api
     *
     * @return
     */
    @GetMapping("/es/http/api")
    @ResponseBody
    public String httpEsApi() {
        String result = esConfig.get();
        return result;
    }

}
