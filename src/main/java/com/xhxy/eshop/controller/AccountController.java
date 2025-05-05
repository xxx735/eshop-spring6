package com.xhxy.eshop.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Order;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.service.AddressService;
import com.xhxy.eshop.service.OrderService;
import com.xhxy.eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/account")
@MultipartConfig(location="D:\\",fileSizeThreshold=1024)
public class AccountController {

	@Resource
	private UserService userService ;
	@Resource
	private OrderService orderService ;
	@Resource
	private AddressService addressService ;
	

	// 账号中心的“主页面”
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Integer id = (Integer)session.getAttribute("userId");// 已登录用户的id
		User user = userService.findById(id);
		model.addAttribute("user", user);
		
		return "account-dashboard";
	}
	// ------ 订单 -> 列表 -------
	public String orderlist(HttpSession session , Model model) {
		Integer id = (Integer)session.getAttribute("userId");// 已登录用户的id
		
		List<Order> orders = orderService.getByUserId(id);
		model.addAttribute("orders", orders);
		
		User user = userService.findById(id);
		model.addAttribute("user", user);
		
		return "account-order-list";
	}
	// ------ 订单 -> 查看单个 -------
	public String orderView(HttpServletRequest request, HttpServletResponse response) {
		Integer orderId = Integer.parseInt(request.getParameter("id")); //订单Id
		
		Order order = orderService.findById(orderId);
		request.setAttribute("order", order);
		
		return "account-order-view.jsp";
	}
	
	// ------ 收货地址 -> 列表 -------
	@GetMapping("/addresslist")
	public String addresslist(HttpSession session,Model model) {
		Integer id = (Integer)session.getAttribute("userId");// 已登录用户的id
		
		List<Address> addressList = addressService.findByUserId(id);
		model.addAttribute("addressList", addressList);
		
		return "account-address-list";
	}
	// ------ 收货地址 -> 编辑 -------
	@GetMapping("/editAddress/{id}")
	public String editAddress(@PathVariable Integer id ,Model model) {

		Address address = addressService.findById(id);
		model.addAttribute("address", address);
		
		return "account-address-edit";
	}
	// ------ 收货地址 -> 新建 -------
	@PostMapping("addAddress")
	public String addAddress(Address address,Integer userId) {

		User user = userService.findById(userId);

		address.setUser(user);
		
		addressService.add(address);
		
		return "redirect:/account/addresslist";	//需要用重定向redirect，否则页面依然是旧数据
	}
	
	// ------ 收货地址 -> 更改 -------
	@PostMapping("updateAddress")
	public String updateAddress(Address address) {

		// 1、让数据层去更新数据
		addressService.update(address);
		// 2.返回表示层
		return "redirect:/account/addresslist"; //需要用重定向redirect，否则页面依然是旧数据
	}
	
	// ------ 收货地址 -> 删除 -------
	@GetMapping("deleteAddress/{id}")
	public String deleteAddress(@PathVariable Integer id ){

		// 1、让数据层去更新数据
		addressService.delete(id);
		// 2.返回表示层
		return "redirect:/account/addresslist"; //需要用重定向redirect，否则页面依然是旧数据
	}
	
	// ------ 用户信息 -> 查看 -------
	@GetMapping("/viewUser/{userId}")
	public String viewUser(@PathVariable Integer userId, Model model) {
//		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
//		Integer id = Integer.parseInt(request.getParameter("id"));
		
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		
		return "account-user-view";
	}
	
	// ------ 用户信息 -> 编辑页面  -------
	@GetMapping("/editUser/{userId}")
	public String editUser(@PathVariable Integer userId, Model model) {
//		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
//		Integer id = Integer.parseInt(request.getParameter("id"));
		
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		
		return "account-user-edit";
	}
	// ------ 用户信息 -> 更改操作-----
	@PostMapping ("/updateUser")
	public String updateUser(User user, @RequestParam("newpassword") String newPassword,
							 MultipartFile avatarFile ,Model model , HttpServletRequest request) throws ServletException, IOException {
		// 2.要不要替换密码
		String currPassword = userService.findPasswordById(user.getId());  //数据库中的密码
		String password = user.getPassword();  //用户提交的原密码
		if(password.isBlank()) {    //未输原密码---->维持原密码
			password = null;	// 设为null，便于mapper中进行动态SQL处理(条件update)
		}
		else if(password != null && !password.equals(currPassword)){	// 输入的原密码不正确
			request.getSession().setAttribute("pswdErrorMessage", "原密码输入错误，请重新输入");
			return "redirect:/account/editUser/"+user.getId();		// 简单返回页面，重新输入
		}
		else if(password !=null && password.equals(currPassword) && newPassword != null && (!newPassword.isEmpty())) {
			password = newPassword;
		}
		user.setPassword(password); //更换为新密码
		
		// 4.上传头像文件的处理
		if(avatarFile == null || avatarFile.getSize() == 0) {	// 若没上传头像，则保持原头像不变
//			user.setAvatar(userService.findById(id).getAvatar());	
			user.setAvatar(null);// 设为null，便于mapper中进行动态SQL处理(条件update)
		}
		else {				// 若上传头像，则
			String filePath = request.getServletContext().getRealPath("/upload");
			filePath = filePath + "\\" + user.getId();  //upload/3/
			File file = new File(filePath);
			if( !file.exists()) {	// 若目录不存在，则创建目录
				file.mkdirs();
			}
			String fileName = avatarFile.getOriginalFilename();  // member1.jpg
			avatarFile.transferTo(new File(filePath + "\\" + fileName));  // upload/3/member1.jpg
			String avatar = "upload\\" + user.getId() + "\\" + fileName;	// 在web根文件夹的相对路径文件
			user.setAvatar(avatar);
		}
		
		// 4、让数据层去更新数据
		userService.update(user);
		
		// 5.返回表示层
		model.addAttribute("user", user);
		return "redirect:/account/viewUser/"+user.getId();
	}
}
