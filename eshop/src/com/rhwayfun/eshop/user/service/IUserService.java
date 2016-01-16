package com.rhwayfun.eshop.user.service;

import com.rhwayfun.eshop.user.entity.User;

public interface IUserService {

	//注册会员
	void save(User user) throws Exception;

	User validateUsername(String username);

	User findUserByCode(String code);

	void update(User user);

	User login(User user);

	User findUserById(Integer userid);
}
