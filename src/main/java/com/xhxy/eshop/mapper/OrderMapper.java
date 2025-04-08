package com.xhxy.eshop.mapper;

import java.util.List;

import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.Order;

public interface OrderMapper{
	
	// 根据用户id获取其全部的Order
	public List<Order> getByUserId(Integer userId);
	
	// 根据订单id获取Order
	public Order findById(Integer orderId);
	
	// 从购物车对象生成订单
	public Integer saveFromCart(Cart cart);
	
	// 保存（插入）一个Order订单，返回插入后的id（自增长）
	public Integer save(Order order);
	
}
