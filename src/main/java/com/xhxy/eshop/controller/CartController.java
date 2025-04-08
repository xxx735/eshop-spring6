package com.xhxy.eshop.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.service.Impl.mybatis.CartServiceImpl;
import com.xhxy.eshop.service.Impl.mybatis.ProductServiceImpl;

/**
 * 购物车的控制器类
 */
@WebServlet("/cart")
public class CartController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CartService cartService = new CartServiceImpl();
	private ProductService productService = new ProductServiceImpl();

	public String view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
		Integer userId = (Integer)request.getSession().getAttribute("id");// 此为用户id		
		
		Cart cart = cartService.findByUserId(userId);
		
		if(cart != null) {
			request.setAttribute("cart", cart);
			
			return "cart.jsp";
		}else {
			return "404.jsp";
		}
	}
	
	// 增加商品到购物车
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
		Integer userId = (Integer)request.getSession().getAttribute("id");// 用户id
		Integer productId = Integer.parseInt(request.getParameter("id"));
		
		Product product = productService.findById(productId);
		Integer cartId = cartService.findByUserId(userId).getId();
		
		cartService.add(product, 1, cartId);
		return "r:/cart?method=view";
	}
	
	// 尚未实现
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
		String id = request.getParameter("id");
		return null;
	}

}
