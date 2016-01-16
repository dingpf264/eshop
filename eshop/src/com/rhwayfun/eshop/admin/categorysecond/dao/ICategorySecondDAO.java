package com.rhwayfun.eshop.admin.categorysecond.dao;

import java.util.List;

import com.rhwayfun.eshop.category.entity.Categorysecond;

public interface ICategorySecondDAO {

	List<Categorysecond> findAllByPage(int currentPage,int startIndex,int pageSize);

	int findCountCsid();

	void save(Categorysecond categorysecond);

	Categorysecond findByCsid(Integer csid);

	void delete(Categorysecond categorysecond);

	void update(Categorysecond categorysecond);

	List<Categorysecond> findAll();
}
