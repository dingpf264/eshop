package com.rhwayfun.eshop.admin.user.service;

import com.rhwayfun.eshop.admin.user.entity.Adminuser;
import com.rhwayfun.eshop.admin.user.entity.Seller;
import com.rhwayfun.eshop.user.entity.User;
import com.rhwayfun.eshop.utils.PageBean;

public interface IAdminUserService {

	Adminuser login(Adminuser adminuser) throws Exception;
	
	PageBean<User> findAllByPage(int currentPage) throws Exception;

	void save(User user);

	User findByUid(Integer userid);

	void delete(User user);

	void update(User user);

	Seller loginSeller(Seller seller);
}
