package com.xhxy.eshop.service.Impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.ProductDao;
import com.xhxy.eshop.dao.impl.ProductDaoImpl;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.interceptor.Page;
import com.xhxy.eshop.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao = new ProductDaoImpl();
	
	@Override
	public Product findById(Integer id) {
		return productDao.findById(id);
	}

	@Override
	public List<Product> findListByCategoryId(Integer categoryId) {
		return productDao.findListByCategoryId(categoryId);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public List<Product> findHot(Integer n) {
		return productDao.findHot(n);
	}

	@Override
	public List<Product> findLatest(Integer n) {
		return productDao.findLatest(n);
	}

	@Override
	public List<Product> findByKeywords(String name, String brief, String detail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findListByCategoryIdPage(Integer categoryId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAllPage(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByKeywordsPage(String name, String brief, String detail, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
