package com.rhwayfun.eshop.admin.product.service;

import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.utils.PageBean;

public interface IAdminProductService {

	PageBean<Product> findAllByPage(int currentPage);

	void save(Product product);

	Product findByPid(Integer pid);

	void update(Product product);

	void delete(Product product);
}
