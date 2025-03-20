package com.xhxy.eshop.service;

import java.util.List;
import com.xhxy.eshop.entity.Faq;

public interface FaqService {
    List<Faq> findAll(); // 定义一个方法，返回所有FAQ的列表
}