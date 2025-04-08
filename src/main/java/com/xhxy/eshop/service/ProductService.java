package com.xhxy.eshop.service;

import java.util.List;

import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.interceptor.Page;

public interface ProductService {
	
	// 根据id查询商品
	public Product findById(Integer id);
	
	// 根据分类id号，查找所有的商品
	public List<Product> findListByCategoryId(Integer categoryId);
	
	// 根据分类id号，查找所有的商品（分页）
	public List<Product> findListByCategoryIdPage(Integer categoryId, Page page);
	
	// 查询全部商品
	public List<Product> findAll();
	
	// 查询全部商品(分页)
	public List<Product> findAllPage(Page page);
	
	// 查询n件热销商品
	public List<Product> findHot(Integer n);
	
	// 查询n件新品
	public List<Product> findLatest(Integer n);
	
	// 根据用户输入的关键词（名称、简介、详情）搜索
	public List<Product> findByKeywords( String name, String brief, String detail);
	
	// 根据用户输入的关键词（名称、简介、详情）搜索(分页)
	public List<Product> findByKeywordsPage(String name, String brief, String detail, Page page);
}
