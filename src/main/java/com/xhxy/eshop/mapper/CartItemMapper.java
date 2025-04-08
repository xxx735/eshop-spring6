package com.xhxy.eshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.entity.Product;

public interface CartItemMapper{
	// 根据id获取cartItem
	public CartItem findById(Integer id);
	
	// 根据购物车的id获取其全部cartItem
	public List<CartItem> findByCartId(Integer cartId);
	
	// 由product创建购物项
	public Integer add(@Param("product") Product product, @Param("quantity") int quantity, @Param("total") Float total,@Param("cartId") int cartId);
	
	// 删掉某个cart的所有cartItem项
	public Integer deleteByCartId(Integer cartId);
	
	// 查找某个cart中是否已有某个Product
	public Integer findByCartIdAndProductId(@Param("cartId") Integer cartId, @Param("productId") Integer productId);
	
	// 更改某个cartItem的数量和总额
	public Integer update(@Param("cartItemId") Integer cartItemId, @Param("quantity") Integer quantity, @Param("total") Float total);
	
}
