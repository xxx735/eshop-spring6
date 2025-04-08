package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.interceptor.Page;
import com.xhxy.eshop.mapper.BlogMapper;
import com.xhxy.eshop.service.BlogService;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Resource
	private BlogMapper blogMapper ;
	@Override
	public List<Blog> findAll() {
		return blogMapper.findAll();
	}
	@Override
	public Blog findById(Integer id) {
		return blogMapper.findById(id);
	}
	@Override
	public List<Blog> findLatestBlog(Integer rows) {
		return blogMapper.findLatestBlog(rows);
	}
	@Override
	public List<Blog> findByPage(Page page) {
		return blogMapper.findByPage(page);
	}

}
