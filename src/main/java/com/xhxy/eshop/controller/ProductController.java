package com.xhxy.eshop.controller;


import jakarta.annotation.Resource;

import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品的控制器类
 */
@Controller
@RequestMapping("/product")   //基路径
public class ProductController {

	@Resource
	private ProductService productService ;

	@GetMapping("/detail/{id}")
	public String detail(@PathVariable(required = true) Integer id, Model model)  {

		
		Product product = productService.findById(id);
		
		if(product != null) {
			model.addAttribute("product", product);
			return "product";
		}else {
			return "404";
		}
		
		
	}

}
