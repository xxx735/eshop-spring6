package com.xhxy.eshop.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.service.FaqService;
import com.xhxy.eshop.service.Impl.mybatis.FaqServiceImpl;

@WebServlet("/faq")
public class FaqController  extends BaseServlet {

	private FaqService faqService = new FaqServiceImpl();
	
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Faq> faqs = faqService.findAll();
		
		request.setAttribute("faqs", faqs);
		return "faq.jsp";
	}
}
