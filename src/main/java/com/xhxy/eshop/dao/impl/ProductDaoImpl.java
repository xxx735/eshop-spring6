package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xhxy.eshop.dao.CategoryDao;
import com.xhxy.eshop.dao.EvaluationDao;
import com.xhxy.eshop.dao.PictureDao;
import com.xhxy.eshop.dao.ProductDao;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.util.JdbcUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public Product findById(Integer id) {
		// SQL：查询某个商品
		String sql = "select * from product where id=?";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setBrief(rs.getString("brief"));
				product.setDetail(rs.getString("detail"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getFloat("price"));
				product.setOriginalPrice(rs.getFloat("original_price"));
				product.setPic(rs.getString("pic"));//主图片的文件名
				product.setPic2(rs.getString("pic2"));
				
				// 设置 分类
				Integer categoryId = rs.getInt("category_id");
				CategoryDao categoryDao = new CategoryDaoImpl();
				product.setCategory(categoryDao.findById(categoryId));
				
				// 设置 图片
				PictureDao pictureDao = new PictureDaoImpl();
				product.setPictureList(pictureDao.findByProductId(id));
				
				// 设置 评价
				EvaluationDao evaluationDao = new EvaluationDaoImpl();
				product.setEvaluationList(evaluationDao.findByProductId(id));
				
				return product;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Product> findListByCategoryId(Integer categoryId) {
		// SQL：查询某个分类的全部商品
		String sql = "select * from product where category_id=?";
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
			)
		{
			pstmt.setInt(1, categoryId);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null) {
				ArrayList<Product> productList = new ArrayList<>();
				while( rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					//product.setBrief(rs.getString("brief"));
					//product.setDetail(rs.getString("detail"));
					//product.setStock(rs.getInt("stock"));
					product.setPrice(rs.getFloat("price"));
					product.setOriginalPrice(rs.getFloat("original_price"));
					product.setPic(rs.getString("pic"));//主图片的文件名
					product.setPic2(rs.getString("pic2"));
						
					// 设置 分类
					CategoryDao categoryDao = new CategoryDaoImpl();
					product.setCategory(categoryDao.findById(categoryId));
					
					// 设置 图片
					//PictureDao pictureDao = new PictureDaoImpl();
					//product.setPictures(pictureDao.findByProductId(id));
				    productList.add(product);
				}
				return productList;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> findAll() {
		// SQL：查询某个分类的全部商品
		String sql = "select * from product";
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			ResultSet rs = pstmt.executeQuery();
			if(rs != null) {
				List<Product> productList = new ArrayList<>();
				while( rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					//product.setBrief(rs.getString("brief"));
					//product.setDetail(rs.getString("detail"));
					//product.setStock(rs.getInt("stock"));
					product.setPrice(rs.getFloat("price"));
					product.setOriginalPrice(rs.getFloat("original_price"));
					product.setPic(rs.getString("pic"));//主图片的文件名
					product.setPic2(rs.getString("pic2"));
					
					// 设置 分类
					Integer categoryId = rs.getInt("category_id");
					CategoryDao categoryDao = new CategoryDaoImpl();
					product.setCategory(categoryDao.findById(categoryId));
					
					// 设置 图片
					//PictureDao pictureDao = new PictureDaoImpl();
					//product.setPictures(pictureDao.findByProductId(id));
				    productList.add(product);
				}
				return productList;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	return null;
	}

	@Override
	public List<Product> findHot(Integer n) {
		// SQL：查询热销商品
		String sql = "select * from product where hot = 1 limit ?";
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
			)
		{
			pstmt.setInt(1, n);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null) {
				ArrayList<Product> hotProductList = new ArrayList<>();
				while( rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					//product.setBrief(rs.getString("brief"));
					//product.setDetail(rs.getString("detail"));
					//product.setStock(rs.getInt("stock"));
					product.setPrice(rs.getFloat("price"));
					product.setOriginalPrice(rs.getFloat("original_price"));
					product.setPic(rs.getString("pic"));//主图片的文件名
					product.setPic2(rs.getString("pic2"));
					
				    hotProductList.add(product);
				}
				return hotProductList;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	return null;
	}

	@Override
	public List<Product> findLatest(Integer n) {
		// SQL：查询n条最新商品
		String sql = "select * from product where latest = 1 limit ?";
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, n);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null) {
				ArrayList<Product> latestProductList = new ArrayList<>();
				while( rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					//product.setBrief(rs.getString("brief"));	// 暂不需要
					//product.setDetail(rs.getString("detail"));
					//product.setStock(rs.getInt("stock"));
					product.setPrice(rs.getFloat("price"));
					product.setOriginalPrice(rs.getFloat("original_price"));
					product.setPic(rs.getString("pic"));	//主图片的文件名
					product.setPic2(rs.getString("pic2"));
					
				    latestProductList.add(product);
				}
				return latestProductList;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	return null;
	}

	@Override
	public List<Product> findByKeywords(String name, String brief, String detail) {
		// SQL：根据指定搜索词 查询商品
		String sql = "select * from product where 1!=1 ";	// where 1!=1是为了避免判断后面的条件之前是否需要or
		if(name != null)	// 有name搜索词
			sql = sql + "or name like %" + name + "% ";
			
		if(brief != null)	// 有brief搜索词
			sql = sql + "or brief like %" + brief + "% ";
		
		if(detail != null)	// 有detail搜索词
			sql = sql + "or detail like %" + detail + "% ";
		
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils工具类的静态方法
			Statement stmt = conn.createStatement();
			)
		{
			ResultSet rs = stmt.executeQuery(sql);
			if(rs != null) {
				ArrayList<Product> productList = new ArrayList<>();
				while( rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					//product.setBrief(rs.getString("brief"));
					//product.setDetail(rs.getString("detail"));
					//product.setStock(rs.getInt("stock"));
					product.setPrice(rs.getFloat("price"));
					product.setOriginalPrice(rs.getFloat("original_price"));
					product.setPic(rs.getString("pic"));//主图片的文件名
					product.setPic2(rs.getString("pic2"));
						
					// 设置 分类
					CategoryDao categoryDao = new CategoryDaoImpl();
					product.setCategory(categoryDao.findById(rs.getInt("category_id")));
					
					// 设置 图片
					//PictureDao pictureDao = new PictureDaoImpl();
					//product.setPictureList(pictureDao.findByProductId(rs.getInt("id"));
				    productList.add(product);
				}
				return productList;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
