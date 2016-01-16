package com.rhwayfun.eshop.admin.category.dao;

import java.util.List;

import com.rhwayfun.eshop.category.entity.Category;

public interface IAdminCategoryDAO {

	List<Category> findAll() throws Exception;

	void save(Category category);

	Category findByCid(Integer cid);

	void update(Category category);

	void delete(Category category);
}
