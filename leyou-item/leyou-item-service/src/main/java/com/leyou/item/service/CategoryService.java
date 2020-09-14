package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类服务
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2019/10/31
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点查询子节点
     *
     * @param pid
     * @return
     */
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }

    /**
     * 查询分类的名字根据id集合
     *
     * @param idList
     * @return
     */
    public List<String> queryNamesByIds(List<Long> idList) {
        List<Category> categoryList = this.categoryMapper.selectByIdList(idList);
        //List集合取某个对象单个值返回
        List<String> categoryNameList = categoryList.stream().map(category -> category.getName()).collect(Collectors.toList());
        return categoryNameList;
    }
}
