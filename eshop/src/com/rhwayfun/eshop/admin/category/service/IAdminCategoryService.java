package com.rhwayfun.eshop.admin.category.service;

import java.util.List;

import com.rhwayfun.eshop.category.entity.Category;

public interface IAdminCategoryService {

	List<Category> findAll() throws Exception;

	void save(Category category);

	Category findByCid(Integer cid);

	void update(Category category);

	void delete(Category category);
}
