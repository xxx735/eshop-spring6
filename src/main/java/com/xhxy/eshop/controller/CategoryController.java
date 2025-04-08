package com.xhxy.eshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.interceptor.Page;
import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.service.Impl.mybatis.CategoryServiceImpl;
import com.xhxy.eshop.service.Impl.mybatis.ProductServiceImpl;

/**
 * 分类 控制器类
 */
@WebServlet("/category")
public class CategoryController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService = new CategoryServiceImpl();
	private ProductService productService = new ProductServiceImpl();

	// 默认为展示全部分类的商品(调用下面的all()方法)
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return all(request,response);
	}
	
	// 展示全部分类的商品
	public String all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.获得请求参数
		String pageIndex = request.getParameter("pageIndex");
		Integer index = 1;	// 默认第1页
		if(pageIndex !=null && !pageIndex.isBlank()) {	// 有pageIndex参数
			index = Integer.parseInt(pageIndex);
		}
			
		String pageSize = request.getParameter("pageSize");
		Integer size = 6;	// 默认每页6条
		if(pageSize != null && !pageSize.isBlank()) {	// 有pageSize参数
			size = Integer.parseInt(pageSize);
		}
		
		Page page = new Page(size,index);	// 每页显示pageSize条，当前为第pageIndex页
		
		// 2.获取全部的顶层分类
		List<Category> topCategoryList = categoryService.findTopCategory();
				
		// 3.获取所有的product
//		List<Product> productList = productService.findAll();			// 未分页
		List<Product> productList = productService.findAllPage(page);	// 分页
				
		// 4.设置属性
		request.setAttribute("page", page);			// 将page传送到前端
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("productList", productList);
					
		return "category-all.jsp";
	}
	
	// 展示指定id分类的商品
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获取请求参数：分类Id号
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		// 获取id对应的分类
		Category category = categoryService.findById(id);//当前的分类
		
		// 2.获得请求参数：分页
		String pageIndex = request.getParameter("pageIndex");
		Integer index = 1;	// 默认第1页
		if(pageIndex !=null && !pageIndex.isBlank()) {	// 有pageIndex参数
			index = Integer.parseInt(pageIndex);
		}
			
		String pageSize = request.getParameter("pageSize");
		Integer size = 6;	// 默认每页6条
		if(pageSize != null && !pageSize.isBlank()) {	// 有pageSize参数
			size = Integer.parseInt(pageSize);
		}
		
		Page page = new Page(size,index);	// 每页显示pageSize条，当前为第pageIndex页
		
		// 3.获取某个id号的Category里所有的product
//		List<Product> productList = productService.findListByCategoryId(id);	// 不分页
		List<Product> productList = productService.findListByCategoryIdPage(id,page);	// 分页
		
		
		// 4.获取全部的顶层分类：用于左侧的分类列表
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		// 5.设置属性
		request.setAttribute("page", page);			// 将page传送到前端
		request.setAttribute("id",id);				// 请求参数id，再传回去给分页栏
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("category", category);
		request.setAttribute("productList", productList);
			
		return "category-list.jsp";
	}
	
	// 展示指定分类的商品
	public String search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.获得请求参数：分页
		String pageIndex = request.getParameter("pageIndex");
		Integer index = 1;	// 默认第1页
		if(pageIndex !=null && !pageIndex.isBlank()) {	// 有pageIndex参数
			index = Integer.parseInt(pageIndex);
		}
			
		String pageSize = request.getParameter("pageSize");
		Integer size = 6;	// 默认每页6条
		if(pageSize != null && !pageSize.isBlank()) {	// 有pageSize参数
			size = Integer.parseInt(pageSize);
		}
		
		Page page = new Page(size,index);	// 每页显示pageSize条，当前为第pageIndex页
		
		// 2.获取请求参数：搜索词
		String name = request.getParameter("name"); 	// 商品名称 搜索词
		String brief = request.getParameter("brief"); 	// 商品简介 搜索词
		String detail = request.getParameter("detail"); // 商品详情 搜索词
		if(name.isBlank())
			name = null;
		if(brief.isBlank())
			brief = null;
		if(detail.isBlank())
			detail = null;
		
		// 3.按搜索词进行product搜索
//		List<Product> productList = productService.findByKeywords(name,brief,detail);// 不分页
		List<Product> productList = productService.findByKeywordsPage(name,brief,detail,page);// 分页
		
		// 4.获取全部的顶层分类：用于左侧的分类列表
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		// 5.设置属性
		request.setAttribute("page", page);					// 将page传送到前端
		request.setAttribute("name",name);					// 请求参数name，再传回去给分页栏
		request.setAttribute("brief",brief);				// 请求参数brief，再传回去给分页栏
		request.setAttribute("detail",detail);				// 请求参数detail，再传回去给分页栏
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("productList", productList);
		
		// 6.请求转发
		return "category-search.jsp";
	}
}
