package com.xhxy.eshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.interceptor.Page;

public interface ProductMapper {
	
	// 根据id查询商品
	public Product findById(Integer id);
	
	// 根据分类id号，查找所有的商品
	public List<Product> findListByCategoryId(Integer categoryId);
	
	// 根据分类id号，查找所有的商品（分页）
	public List<Product> findListByCategoryIdPage(@Param("categoryId") Integer categoryId, @Param("page") Page page);
	
	// 查询全部商品
	public List<Product> findAll();
	
	// 查询全部商品(分页)
	public List<Product> findAllPage(@Param("page") Page page);
	
	// 查询前n件热销商品
	public List<Product> findHot(Integer n);
	
	// 查询前n件新品
	public List<Product> findLatest(Integer n);
	
	// 根据用户输入的关键词（名称、简介、详情）搜索
	public List<Product> findByKeywords(@Param("name") String name, @Param("brief")  String brief, @Param("detail")String detail);
	
	// 根据用户输入的关键词（名称、简介、详情）搜索(分页)
	public List<Product> findByKeywordsPage(@Param("name") String name, @Param("brief")  String brief, @Param("detail")String detail,@Param("page") Page page);
}
