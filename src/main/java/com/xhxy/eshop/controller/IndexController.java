package com.xhxy.eshop.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.BlogService;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.service.UserService;
import com.xhxy.eshop.service.Impl.mybatis.BlogServiceImpl;
import com.xhxy.eshop.service.Impl.mybatis.CartServiceImpl;
import com.xhxy.eshop.service.Impl.mybatis.CategoryServiceImpl;
import com.xhxy.eshop.service.Impl.mybatis.ProductServiceImpl;
import com.xhxy.eshop.service.Impl.mybatis.UserServiceImpl;

@WebServlet("/index")
public class IndexController  extends BaseServlet {

	private CategoryService categoryService = new CategoryServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private BlogService blogService = new BlogServiceImpl();
	private UserService userService = new UserServiceImpl();
	private CartService cartService = new CartServiceImpl();
	private List<Product> findAll;
	
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 获取全部的顶层分类:用于左侧菜单
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		// 获取10条的热销商品
		List<Product> hotProductList = productService.findHot(10);
		
		// 获取最多10条的新品
		List<Product> latestProductList = productService.findLatest(10);
		
		// 获取三篇推荐文章
		List<Blog> blogList = blogService.findLatestBlog(3);
		
		// 从properties属性文件中读取 总请求数、首页访问数、在线用户数、全部用户数
		Properties props = new Properties();
		InputStream in = new BufferedInputStream(getClass().getResourceAsStream("/eshop.properties")); 
		props.load(in);
		
		// 设置model属性
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("hotProductList", hotProductList);
		request.setAttribute("latestProductList", latestProductList);
		request.setAttribute("blogList", blogList);
		
		request.setAttribute("allRequest", props.getProperty("allRequest"));
		request.setAttribute("indexRequest", props.getProperty("indexRequest"));
		request.setAttribute("onlineUser", props.getProperty("onlineUser"));
		request.setAttribute("allUser", props.getProperty("allUser"));
						
		return "index.jsp";
	}
}
