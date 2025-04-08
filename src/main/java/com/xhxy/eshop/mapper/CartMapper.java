package com.xhxy.eshop.mapper;

import org.apache.ibatis.annotations.Param;

import com.xhxy.eshop.entity.Cart;

public interface CartMapper{
	// 根据用户Id，返回Cart
	public Cart findByUserId(Integer userId);
	
	// 更改某个cart的total总价
	public Integer updateTotal(@Param("cartId") Integer cartId, @Param("cartTotal") Float cartTotal);
}
