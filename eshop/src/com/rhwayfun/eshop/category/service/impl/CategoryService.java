package com.rhwayfun.eshop.category.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rhwayfun.eshop.category.dao.ICategoryDAO;
import com.rhwayfun.eshop.category.entity.Category;
import com.rhwayfun.eshop.category.service.ICategoryService;

@Transactional
public class CategoryService implements ICategoryService {

	private ICategoryDAO categoryDAO;
	
	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public List<Category> findAll() throws Exception {
		return categoryDAO.findAll();
	}

}
