package com.rhwayfun.eshop.category.service;

import java.util.List;

import com.rhwayfun.eshop.category.entity.Category;
import com.rhwayfun.eshop.category.entity.Categorysecond;

public interface ICategoryService {

	List<Category> findAll() throws Exception;
	
}
