package com.rhwayfun.eshop.category.dao;

import java.util.List;

import com.rhwayfun.eshop.category.entity.Category;

public interface ICategoryDAO {

	List<Category> findAll() throws Exception;
}
