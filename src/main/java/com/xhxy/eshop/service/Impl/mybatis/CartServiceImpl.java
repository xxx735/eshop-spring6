package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.mapper.CartItemMapper;
import com.xhxy.eshop.mapper.CartMapper;
import com.xhxy.eshop.mapper.ProductMapper;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.util.MybatisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Resource
	private CartMapper cartMapper ;
	@Resource
	private CartItemMapper cartItemMapper ;
	
	@Override
	public Cart findByUserId(Integer userId) {
		return cartMapper.findByUserId(userId);
	}

	@Override
	public boolean clear(Integer cartId) {
		// 1. 先删该cart中的所有cartItem项
		cartItemMapper.deleteByCartId(cartId);
		
		// 2. 再将该cart的total项设为0
		cartMapper.updateTotal(cartId, 0.0F);
		
		return true;
	}

	@Override
	public boolean add(Product product, int quantity, int cartId) {
		// 1.检查该cart中是否已有该商品；
		Integer productId = product.getId();
		Integer cartItemId = cartItemMapper.findByCartIdAndProductId(cartId,productId);
		// 1.1 若有，则将原商品数量 加1
		if(cartItemId!=null && cartItemId > 0) {
			CartItem cartItem= cartItemMapper.findById(cartItemId);		//取得该cartItem
			quantity = quantity + cartItem.getQuantity();				// 新购买数量 + 原购买数量 
			Float total = cartItem.getProduct().getPrice() * quantity; 	// 重新计算总价
			
			cartItemMapper.update(cartItemId, quantity,total);			// 更新该cartItem的数量和总价
		}
		// 1.2 若无，则创建新的购物项cartItem
		else {
			
			Float total = product.getPrice() * quantity; 	// 重新计算总价
			cartItemMapper.add(product, quantity, total, cartId);
		}
		// 2.重新计算该购物车的总额
		List<CartItem> cartItemList = cartItemMapper.findByCartId(cartId);
		Float cartTotal = 0.0F;
		for(CartItem cartItem:cartItemList) {
			cartTotal = cartTotal + cartItem.getTotal();	// 将每项cartItem的总价加进购物车的总价
		}
		cartMapper.updateTotal(cartId, cartTotal);
		return true;
	}

}
