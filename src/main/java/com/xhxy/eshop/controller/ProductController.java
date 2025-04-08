package com.xhxy.eshop.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.service.Impl.mybatis.ProductServiceImpl;

/**
 * 商品的控制器类
 */
@WebServlet("/product")
public class ProductController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductServiceImpl();
	
	public String detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		Product product = productService.findById(id);
		
		if(product != null) {
			request.setAttribute("product", product);
			return "product.jsp";
		}else {
			return "404.jsp";
		}
		
		
	}

}
