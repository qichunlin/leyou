package com.leyou.item.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 库存表，代表库存，秒杀库存等信息
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2019/11/4
 */
@Table(name = "tb_stock")
public class Stock {

    @Id
    private Long skuId;

    /**
     * 秒杀可用库存
     */
    private Integer seckillStock;

    /**
     * 已秒杀数量
     */
    private Integer seckillTotal;

    /**
     * 正常库存
     */
    private Integer stock;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getSeckillStock() {
        return seckillStock;
    }

    public void setSeckillStock(Integer seckillStock) {
        this.seckillStock = seckillStock;
    }

    public Integer getSeckillTotal() {
        return seckillTotal;
    }

    public void setSeckillTotal(Integer seckillTotal) {
        this.seckillTotal = seckillTotal;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}