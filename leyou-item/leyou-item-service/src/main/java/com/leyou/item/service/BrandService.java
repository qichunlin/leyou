package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 品牌Service
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2019/11/4
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查询品牌
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //根据name模糊查询,或者根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            //name 满足或者首字母也满足
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        // 添加分页条件
        PageHelper.startPage(page, rows);

        // 添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            //setOrderByClause("id desc")  排序条件
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        //选择数据根据查询条件查询
        List<Brand> brands = this.brandMapper.selectByExample(example);
        // 包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        // 包装成分页结果集返回  获取总条数还有集合信息
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 新增品牌
     *
     * @param brand
     * @param cids
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveBrand(Brand brand, List<Long> cids) {
        //先新增brand
        /*Boolean flag = this.brandMapper.insertSelective(brand) == 1;
        if (flag) {
            cids.forEach(cid ->{
                this.brandMapper.insertCategoryAndBrand(cid,brand.getId());
            });
        }*/
        this.brandMapper.insertSelective(brand);
        cids.forEach(cid -> {
            this.brandMapper.insertCategoryAndBrand(cid, brand.getId());
        });
    }

    /**
     * 查询品牌列表根据分类id
     *
     * @param cid
     */
    public List<Brand> queryBrandsByCid(Long cid) {
        List<Brand> brandList = this.brandMapper.selectBrandsByCid(cid);
        return brandList;
    }
}
