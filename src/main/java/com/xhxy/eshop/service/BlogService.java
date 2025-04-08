package com.xhxy.eshop.service;

import java.util.List;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.interceptor.Page;

public interface BlogService {
	// 查询全部推荐文章
	List<Blog> findAll();
	
	// 查询某篇推荐文章
	Blog findById(Integer id);
	
	// 查询最新的n篇推荐文章：用于首页显示用
	List<Blog> findLatestBlog(Integer rows);
	
	// 查询全部推荐文章（分页插件用）
	List<Blog> findByPage(Page page);
	
}
