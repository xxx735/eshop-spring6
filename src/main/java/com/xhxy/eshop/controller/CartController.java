package com.xhxy.eshop.controller;

import java.io.IOException;

import jakarta.annotation.Resource;
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
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 购物车的控制器类
 */

@Controller
@RequestMapping("/cart")
public class CartController {

	@Resource
	private CartService cartService ;
	@Resource
	private ProductService productService ;


	//查看购物车
	@GetMapping("/view")
	public String view(Model model, HttpSession session)  {
		// 获取请求参数
		Integer userId = (Integer)session.getAttribute("userId");// 此为用户id
		
		Cart cart = cartService.findByUserId(userId);
		
		if(cart != null) {
			model.addAttribute("cart", cart);
			
			return "cart";
		}else {
			return "404";
		}
	}
	
	// 增加商品到购物车
	@GetMapping("/add/{productId}")
	public String add(@PathVariable Integer productId, HttpSession session){
		// 获取请求参数
		Integer userId = (Integer)session.getAttribute("userId");// 用户id
		
		Product product = productService.findById(productId);
		Integer cartId = cartService.findByUserId(userId).getId();
		
		cartService.add(product, 1, cartId);
		return "redirect:/cart/view";
	}
	
	// 尚未实现
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
		String id = request.getParameter("id");
		return null;
	}

}
