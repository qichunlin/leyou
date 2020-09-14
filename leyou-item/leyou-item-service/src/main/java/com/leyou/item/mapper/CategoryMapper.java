package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * CategoryMapper
 * SelectByIdListMapper<Category,Long> 具体是传入id集合查询数据(第一个参数是对象Bean,第二个是主键的数据类型)
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/10/31
 */
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {
}
