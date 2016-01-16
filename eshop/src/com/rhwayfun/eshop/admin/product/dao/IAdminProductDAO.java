package com.rhwayfun.eshop.admin.product.dao;

import java.util.List;

import com.rhwayfun.eshop.product.entity.Product;

public interface IAdminProductDAO {

	List<Product> findAllByPage(int currentPage,int startIndex,int pageSize);

	int findAllCount();

	void save(Product product);

	Product findByPid(Integer pid);

	void update(Product product);

	void delete(Product product);
}
