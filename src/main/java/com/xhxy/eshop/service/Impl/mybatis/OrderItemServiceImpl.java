package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.OrderItem;
import com.xhxy.eshop.mapper.OrderItemMapper;
import com.xhxy.eshop.service.OrderItemService;
import com.xhxy.eshop.util.MybatisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

	@Resource
	private OrderItemMapper orderItemMapper ;
	
	@Override
	public List<OrderItem> findByOrderId(Integer orderId) {
		return orderItemMapper.findByOrderId(orderId);
	}

	@Override
	public Integer save(OrderItem orderItem) {
		return orderItemMapper.save(orderItem);
	}

}
