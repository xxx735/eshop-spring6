package com.xhxy.eshop.controller;

import java.io.IOException;
import java.util.List;

import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.service.FaqService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faq")
public class FaqController {

    @Resource
    private FaqService faqService;

    @GetMapping("/index")
    public String index(Model model) throws IOException {
        List<Faq> faqs = faqService.findAll();

        model.addAttribute("faqs", faqs);
        return "faq";
    }
}