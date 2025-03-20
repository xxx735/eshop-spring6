package com.xhxy.eshop.mapper;

import java.util.List;
import com.xhxy.eshop.entity.Faq;

// 相当于FaqDao
public interface FaqMapper {
    List<Faq> findAll(); // 定义一个方法，返回所有FAQ的列表
}