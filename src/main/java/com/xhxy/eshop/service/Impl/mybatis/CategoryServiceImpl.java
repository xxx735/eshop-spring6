package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.mapper.CategoryMapper;
import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.util.MybatisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryMapper categoryMapper ;
	
	@Override
	public Category findById(Integer id) {
		return categoryMapper.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryMapper.findAll();
	}

	@Override
	public List<Category> findTopCategory() {
		return categoryMapper.findTopCategory();
	}

}
