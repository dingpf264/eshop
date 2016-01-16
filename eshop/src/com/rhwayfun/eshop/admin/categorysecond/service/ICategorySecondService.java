package com.rhwayfun.eshop.admin.categorysecond.service;

import java.util.List;

import com.rhwayfun.eshop.category.entity.Categorysecond;
import com.rhwayfun.eshop.utils.PageBean;

public interface ICategorySecondService {

	PageBean<Categorysecond> findAllByPage(int currentPage);

	void save(Categorysecond categorysecond);

	Categorysecond findByCsid(Integer csid);

	void delete(Categorysecond categorysecond);

	void update(Categorysecond categorysecond);

	List<Categorysecond> findAll();
}
