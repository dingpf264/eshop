package com.rhwayfun.eshop.product.dao;

import java.util.List;

import com.rhwayfun.eshop.product.entity.Product;

public interface IProductDAO {

	List<Product> findHotProducts();
	List<Product> findNewProducts();
	Product findDetail(int pid);
	List<Product> findPageProductsByCategory(int cid,int startIndex , int pageSize);
	int findCountByCid(int cid);
	List<Product> findPageProductsByCategorysecond(int csid,int startIndex,int pageSize);
	int findCountByCsid(int csid);
	Product findProductByPid(int pid);
}
