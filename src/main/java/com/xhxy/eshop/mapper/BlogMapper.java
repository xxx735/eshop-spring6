package com.xhxy.eshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.interceptor.Page;

@Mapper
public interface BlogMapper {
	
	// 查询全部推荐文章
	List<Blog> findAll();
	
	// 查询某篇推荐文章
	Blog findById(Integer id);
	
	// 查询最新的n篇推荐文章：用于首页显示用
	List<Blog> findLatestBlog(Integer n);
	
	// 查询全部推荐文章（分页）
	List<Blog> findByPage(@Param("page") Page page);
	
}
