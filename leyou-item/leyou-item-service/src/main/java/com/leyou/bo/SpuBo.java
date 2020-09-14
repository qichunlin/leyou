package com.leyou.bo;

import com.leyou.item.pojo.Spu;

/**
 * Spu的扩展类
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/14
 */
public class SpuBo extends Spu {

    /**
     * 分类名称
     */
    private String cname;

    /**
     * 品牌名称
     */
    private String bname;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
