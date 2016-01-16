package com.rhwayfun.eshop.user.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.rhwayfun.eshop.user.dao.IUserDAO;
import com.rhwayfun.eshop.user.entity.User;
import com.rhwayfun.eshop.user.service.IUserService;

@Transactional
public class UserService implements IUserService{

	private IUserDAO userDAO;
	
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void save(User user) throws Exception {
		userDAO.save(user);
	}

	@Override
	public User validateUsername(String username) {
		return userDAO.validateUsername(username);
	}

	@Override
	public User findUserByCode(String code) {
		return userDAO.findUserByCode(code);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public User login(User user) {
		return userDAO.login(user);
	}

	@Override
	public User findUserById(Integer userid) {
		return userDAO.findUserById(userid);
	}

}
