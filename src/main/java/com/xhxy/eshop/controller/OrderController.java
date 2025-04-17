package com.xhxy.eshop.controller;

import java.util.List;

import jakarta.annotation.Resource;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.service.AddressService;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.service.OrderService;
import com.xhxy.eshop.service.Impl.mybatis.CartServiceImpl;
import com.xhxy.eshop.service.Impl.mybatis.OrderServiceImpl;
import com.xhxy.eshop.service.Impl.mybatis.AddressServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Resource
	private CartService cartService ;
	@Resource
	private OrderService orderService ;
	@Resource
	private AddressService addressService ;
	
	// 创建订单:create
	@GetMapping("/create")
	public String create(Model model, HttpSession session) {
		int userId = (int)session.getAttribute("userId");// 登录用户的id
		
		Cart cart = cartService.findByUserId(userId);
		model.addAttribute("cart", cart);
		
		List<Address> addressList = addressService.findByUserId(userId);
		model.addAttribute("addressList", addressList);
		
		return "order-create";
	}
	
	// 完成（结束）订单
	@PostMapping("/complete")
	public String complete(Integer addressId,HttpSession session) {
		
		Integer id = (Integer)session.getAttribute("userId");	// 即userId
		Cart cart = cartService.findByUserId(id);

		Address address = addressService.findById(addressId);
		
		orderService.createOrder(cart,address); 	
		
		return "/order-complete";
	}
}
