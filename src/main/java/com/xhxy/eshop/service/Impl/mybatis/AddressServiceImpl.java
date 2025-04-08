package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.mapper.AddressMapper;
import com.xhxy.eshop.service.AddressService;
import com.xhxy.eshop.util.MybatisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

//	private AddressDao addressDao = new AddressDaoImpl();	// 原代码
	@Resource
	private AddressMapper addressMapper ;
	
	@Override
	public List<Address> findByUserId(Integer userId) {
		return addressMapper.findByUserId(userId);
	}
	
	@Override
	public Address findById(Integer id) {
		return addressMapper.findById(id);
	}
	
	// 为某个用户增加收货地址
	@Override
	public int add(Address address) {
		return addressMapper.add(address);
	}
	// 更新某个收货地址
	@Override
	public int update(Address address) {
		return addressMapper.update(address);
	}
	// 删除某个收货地址
	@Override
	public int delete(Integer id) {
		return addressMapper.delete(id);
	}


}
