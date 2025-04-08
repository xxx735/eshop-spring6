package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.mapper.CartItemMapper;
import com.xhxy.eshop.service.CartItemService;
import com.xhxy.eshop.util.MybatisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

	@Resource
	private CartItemMapper cartItemMapper ;
	
	@Override
	public List<CartItem> findByCartId(Integer cartId) {
		return cartItemMapper.findByCartId(cartId);
	}

}
