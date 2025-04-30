package com.xhxy.eshop.controller;

import java.io.IOException;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.service.FaqService;
import com.xhxy.eshop.service.Impl.mybatis.FaqServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller("/faq")
public class FaqController   {

	@Resource
	private FaqService faqService ;


	public String index(Model model) throws IOException {
		List<Faq> faqs = faqService.findAll();
		
		model.addAttribute("faqs", faqs);
		return "faq";
	}
}
