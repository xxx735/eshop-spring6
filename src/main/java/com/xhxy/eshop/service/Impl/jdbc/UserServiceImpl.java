package com.xhxy.eshop.service.Impl.jdbc;

import com.xhxy.eshop.dao.UserDao;
import com.xhxy.eshop.dao.impl.UserDaoImpl;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public Integer addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public Integer login(String username, String password) {
		return userDao.login(username, password);
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public Integer update(User user) {
		return userDao.update(user);
	}

	@Override
	public String findPasswordById(int id) {
		return userDao.findPasswordById(id);
	}

}
