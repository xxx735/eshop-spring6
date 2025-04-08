package com.xhxy.eshop.service.Impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.BlogDao;
import com.xhxy.eshop.dao.impl.BlogDaoImpl;
import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.interceptor.Page;
import com.xhxy.eshop.service.BlogService;

public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao = new BlogDaoImpl();
	@Override
	public List<Blog> findAll() {
		return blogDao.findAll();
	}
	@Override
	public Blog findById(Integer id) {
		return blogDao.findById(id);
	}
	@Override
	public List<Blog> findLatestBlog(Integer rows) {
		return blogDao.findLatestBlog(rows);
	}
	@Override
	public List<Blog> findByPage(Page page) {
		// 未实现
		return null;
	}

}
