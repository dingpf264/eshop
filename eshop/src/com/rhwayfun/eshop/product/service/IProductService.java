package com.rhwayfun.eshop.product.service;

import java.util.List;

import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.utils.PageBean;

public interface IProductService {

	List<Product> findHotProducts();
	List<Product> findNewProducts();
	Product findDetail(int pid);
	PageBean<Product> findPageProductsByCategory(int cid,int currentPage);
	PageBean<Product> findPageProductsByCategorysecond(int csid,int currentPage);
	Product findProductByPid(int pid);
	PageBean<Product> findPageProductsByName(String pname, int currentPage);
	
}
