package com.rhwayfun.eshop.admin.user.dao;

import java.util.List;

import com.rhwayfun.eshop.admin.user.entity.Adminuser;
import com.rhwayfun.eshop.admin.user.entity.Seller;
import com.rhwayfun.eshop.user.entity.User;

public interface IAdminUserDAO {

	Adminuser login(Adminuser adminuser) throws Exception;
	
	List<User> findAllByPage(int startIndex,int pageSize);

	int findCount();

	void save(User user);

	User findByUid(Integer userid);

	void delete(User user);

	void update(User user);

	Seller loginSeller(Seller seller);
}
