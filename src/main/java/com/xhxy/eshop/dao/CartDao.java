package com.xhxy.eshop.dao;

import com.xhxy.eshop.entity.Cart;

public interface CartDao{
	
	// 根据id，查询Cart
	public Cart findById(Integer id);
	
	// 根据用户Id，返回Cart
	public Cart findByUserId(Integer userId);
	
	// 更改某个cart的total总价
	public Integer updateTotal(Integer cartId, Float cartTotal);
}
