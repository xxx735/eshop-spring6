package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.interceptor.Page;
import com.xhxy.eshop.mapper.ProductMapper;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.util.MybatisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductMapper productMapper ;
	
	@Override
	public Product findById(Integer id) {
		return productMapper.findById(id);
	}

	@Override
	public List<Product> findListByCategoryId(Integer categoryId) {
		return productMapper.findListByCategoryId(categoryId);
	}

	@Override
	public List<Product> findAll() {
		return productMapper.findAll();
	}

	@Override
	public List<Product> findHot(Integer n) {
		return productMapper.findHot(n);
	}

	@Override
	public List<Product> findLatest(Integer n) {
		return productMapper.findLatest(n);
	}

	@Override
	public List<Product> findByKeywords(String name, String brief, String detail) {
		return productMapper.findByKeywords(name, brief, detail);
	}

	@Override
	public List<Product> findListByCategoryIdPage(Integer categoryId, Page page) {
		return productMapper.findListByCategoryIdPage(categoryId, page);
	}

	@Override
	public List<Product> findAllPage(Page page) {
		return productMapper.findAllPage(page);
	}

	@Override
	public List<Product> findByKeywordsPage(String name, String brief, String detail, Page page) {
		return productMapper.findByKeywordsPage(name, brief, detail, page);
	}

}
