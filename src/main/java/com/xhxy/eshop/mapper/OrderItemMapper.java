package com.xhxy.eshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhxy.eshop.entity.OrderItem;

public interface OrderItemMapper {
	// 根据id获取
	public List<OrderItem> findByOrderId(Integer orderId);
	
	// 保存（插入）一个OrderItem
	public Integer save(OrderItem orderItem);
	
	// 保存（插入）多个OrderItem
	public Integer batchSave(List<OrderItem> orderItemList);
}
