package com.leyou.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 *
 * @author legend
 */
@Table(name="tb_category")
public class Category {

	/**
	 * 类目id
	 */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	/**
	 * 类目名称
	 */
	private String name;

	/**
	 * 父类目id,顶级类目填0
	 */
	private Long parentId;

	/**
	 * 是否为父节点，0为否，1为是
	 * 注意isParent生成的getter和setter方法需要手动加上Is
	 */
	private Boolean isParent;

	/**
	 * 排序指数，越小越靠前
	 */
	private Integer sort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean parent) {
		isParent = parent;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}