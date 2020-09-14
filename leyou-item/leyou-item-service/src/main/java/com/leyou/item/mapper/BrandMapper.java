package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 品牌mapper
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2019/11/4
 */
public interface BrandMapper extends Mapper<Brand> {

    /**
     * 查询分类品牌中间表
     *
     * @param cid
     * @param bid
     */
    @Insert("INSERT INTO tb_category_brand(category_id,brand_id) values(#{cid},#{bid})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    /**
     * 查询品牌列表根据分类id
     *
     * @param cid
     * @return
     */
    @Select({"SELECT * FROM tb_brand a INNER JOIN tb_category_brand b ON a.id=b.brand_id WHERE b.category_id=#{cid}"})
    List<Brand> selectBrandsByCid(@Param("cid") Long cid);
}