package com.rhwayfun.eshop.admin.user.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rhwayfun.eshop.admin.user.dao.IAdminUserDAO;
import com.rhwayfun.eshop.admin.user.entity.Adminuser;
import com.rhwayfun.eshop.admin.user.entity.Seller;
import com.rhwayfun.eshop.admin.user.service.IAdminUserService;
import com.rhwayfun.eshop.user.entity.User;
import com.rhwayfun.eshop.utils.PageBean;

@Transactional
public class AdminUserService implements IAdminUserService {

	private IAdminUserDAO adminUserDAO;
	
	public void setAdminUserDAO(IAdminUserDAO adminUserDAO) {
		this.adminUserDAO = adminUserDAO;
	}

	@Override
	public Adminuser login(Adminuser adminuser) throws Exception {
		return adminUserDAO.login(adminuser);
	}

	@Override
	public Seller loginSeller(Seller seller) {
		return adminUserDAO.loginSeller(seller);
	}
	
	@Override
	public PageBean<User> findAllByPage(int currentPage) throws Exception {
		PageBean<User> page = new PageBean<User>();
		page.setCurrentPage(currentPage);
		int totalCount = 0;
		totalCount = adminUserDAO.findCount();
		page.setTotalCount(totalCount);
		int pageSize = 8;
		page.setPageSize(pageSize);
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		page.setTotalPage(totalPage);
		int startIndex = (currentPage - 1) * pageSize;
		List<User> list = adminUserDAO.findAllByPage(startIndex, pageSize);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}

	@Override
	public void save(User user) {
		adminUserDAO.save(user);
	}

	@Override
	public User findByUid(Integer userid) {
		return adminUserDAO.findByUid(userid);
	}

	@Override
	public void delete(User user) {
		adminUserDAO.delete(user);
	}

	@Override
	public void update(User user) {
		adminUserDAO.update(user);
	}

}
