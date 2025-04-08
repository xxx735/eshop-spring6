package com.xhxy.eshop.service.Impl.mybatis;

import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.mapper.UserMapper;
import com.xhxy.eshop.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper ;
	
	@Override
	public Integer addUser(User user) {
		return userMapper.addUser(user);
	}

	@Override
	public Integer login(String username, String password) {
		return userMapper.login(username, password);
	}

	@Override
	public User findById(int id) {
		return userMapper.findById(id);
	}

	@Override
	public Integer update(User user) {
		return userMapper.update(user);
	}

	@Override
	public String findPasswordById(int id) {
		return userMapper.findPasswordById(id);
	}

}
