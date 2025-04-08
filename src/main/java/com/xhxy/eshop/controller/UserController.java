package com.xhxy.eshop.controller;

import java.io.IOException;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.service.UserService;
import com.xhxy.eshop.service.Impl.mybatis.CartServiceImpl;
import com.xhxy.eshop.service.Impl.jdbc.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Servlet
 */
@Controller
@SessionAttributes({"userName","userId"})
@RequestMapping("/user")
public class UserController {
	private static final long serialVersionUID = 1L;

	@Resource
	private UserService userService;
	@Resource
	private CartService cartService ;


	@GetMapping("/{url}")
	public String url(@PathVariable String url){
		return url;
	}

	@PostMapping("/login")
	public String login(User user, @RequestParam(required=false) String autologin,
			@CookieValue(value="username",required=false) String usernameCookie,
			@CookieValue(value="password",required = false) String passwordCookie,
						Model model, HttpServletResponse response)  {

		// 首先检查 是否是手动登录：当login.jsp提交时，可能也会有以前的cookie含自动登录，这时不能用自动登录
		if(user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {//若不是手动登录，再考虑自动登录

				Integer userId = userService.login(usernameCookie, passwordCookie);	// 用cookie取到的username和password尝试登录
				if ( userId != null && userId > -1) { // 登录成功
					model.addAttribute("userName", usernameCookie);	// 设置会话里的userName属性
					model.addAttribute("userId", userId);					// 设置会话里的Id属性
					
					// 获取该用户的购物车
					Cart cart = cartService.findByUserId(userId);
					model.addAttribute("cart", cart);	// 购物车cart放入request中
					// 重定向到首页
					return "redirect:/index";	//此处需要：重定向redirect
				}
			}
		// 若不能自动登录，则继续获取请求参数

		// 验证是否登录成功
		Integer id = userService.login(user.getUsername(), user.getPassword());
		if ( id != null && id > -1) { // 登录成功
			model.addAttribute("userName", user.getUsername());
			model.addAttribute("id", id);
			
			// 获取该用户的购物车
			Cart cart = cartService.findByUserId(id);
			// 判断是否需要自动登录
			if ( autologin !=null && autologin.equals("checked")) {
				Cookie nameCookie = new Cookie("username",user.getUsername());
				Cookie pswdCookie = new Cookie("password",user.getPassword());
				
				nameCookie.setMaxAge(60*60*24*7);
				pswdCookie.setMaxAge(60*60*24*7);
				
				response.addCookie(nameCookie);
				response.addCookie(pswdCookie);
			}
			model.addAttribute("cart", cart);//放入request
			// 重定向到首页
			return "redirect:/index";	//此处需要：重定向redirect
		}else { // 登录失败
			model.addAttribute("message", "登录失败，请重新登录");
			
			return "login";
		}
		
	}

	@PostMapping("/signup")
	public String signup(User user, Model model)  {

				
		// 调用UserDao插入新用户
		if(userService.addUser(user) > 0) {
			return "login";
		}else {
			String message =  "注册失败，请重新输入";
			model.addAttribute("message", message);
					
			return "signup";
		}
	
	}


	//退出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userName");
		session.removeAttribute("userId");
		session.invalidate();
		
		return "login"; //此处需要：重定向redirect
	}
}
