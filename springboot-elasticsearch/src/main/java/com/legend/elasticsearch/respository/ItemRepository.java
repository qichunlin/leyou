package com.legend.elasticsearch.respository;

import com.legend.elasticsearch.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * ElasticsearchRepository需要两个参数
 * 第一个是实体类,第二个是实体类的主键id的类型
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/17
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    /**
     * 自定义方法不需要实现
     *
     * @param title
     * @return
     */
    List<Item> findByTitle(String title);

    /**
     * 查找价格区间
     *
     * @param price1
     * @param price2
     * @return
     */
    List<Item> findByPriceBetween(Double price1, Double price2);
}
