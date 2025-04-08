package com.xhxy.eshop.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhxy.eshop.entity.Product;

public interface ProductDao{
	
	// 根据id查询商品
	public Product findById(Integer id);
	
	// 根据分类id号，查找所有的商品
	public ArrayList<Product> findListByCategoryId(Integer categoryId);
	
	// 查询全部商品
	public List<Product> findAll();
	
	// 查询前n件热销商品
	public List<Product> findHot(Integer n);
	
	// 查询前n件新品
	public List<Product> findLatest(Integer n);
	
	// 根据用户输入的关键词（名称、简介、详情）搜索，涉及到sql的拼接
	public List<Product> findByKeywords(String name, String brief, String detail);
}
