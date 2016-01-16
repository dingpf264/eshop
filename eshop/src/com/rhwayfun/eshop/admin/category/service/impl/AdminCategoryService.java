package com.rhwayfun.eshop.admin.category.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rhwayfun.eshop.admin.category.dao.IAdminCategoryDAO;
import com.rhwayfun.eshop.admin.category.dao.impl.AdminCategoryDAO;
import com.rhwayfun.eshop.admin.category.service.IAdminCategoryService;
import com.rhwayfun.eshop.category.entity.Category;

@Transactional
public class AdminCategoryService implements IAdminCategoryService {

	private IAdminCategoryDAO adminCategoryDAO;
	
	public void setAdminCategoryDAO(AdminCategoryDAO adminCategoryDAO) {
		this.adminCategoryDAO = adminCategoryDAO;
	}

	@Override
	public List<Category> findAll() throws Exception {
		return adminCategoryDAO.findAll();
	}

	@Override
	public void save(Category category) {
		adminCategoryDAO.save(category);
	}

	@Override
	public Category findByCid(Integer cid) {
		return adminCategoryDAO.findByCid(cid);
	}

	@Override
	public void update(Category category) {
		adminCategoryDAO.update(category);
	}

	@Override
	public void delete(Category category) {
		adminCategoryDAO.delete(category);
	}

}
