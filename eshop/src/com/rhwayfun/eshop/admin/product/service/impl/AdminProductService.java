package com.rhwayfun.eshop.admin.product.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rhwayfun.eshop.admin.product.dao.IAdminProductDAO;
import com.rhwayfun.eshop.admin.product.service.IAdminProductService;
import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.utils.PageBean;

@Transactional
public class AdminProductService implements IAdminProductService {

	private IAdminProductDAO adminProductDAO;
	
	public void setAdminProductDAO(IAdminProductDAO adminProductDAO) {
		this.adminProductDAO = adminProductDAO;
	}

	@Override
	public PageBean<Product> findAllByPage(int currentPage) {
		//创建一个PageBean
		PageBean<Product> page = new PageBean<Product>();
		//设置当前页数
		page.setCurrentPage(currentPage);
		//获取总的记录数
		int totalCount = 0;
		totalCount = adminProductDAO.findAllCount();
		//设置总的记录数
		page.setTotalCount(totalCount);
		int pageSize = 10;
		page.setPageSize(pageSize);
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		page.setTotalPage(totalPage);
		//当前页第一条记录的位置，或者说是rownum
		int startIndex = (currentPage - 1) * pageSize;
		List<Product> list = adminProductDAO.findAllByPage(currentPage, startIndex, pageSize);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}

	@Override
	public void save(Product product) {
		adminProductDAO.save(product);
	}

	@Override
	public Product findByPid(Integer pid) {
		return adminProductDAO.findByPid(pid);
	}

	@Override
	public void update(Product product) {
		adminProductDAO.update(product);
	}

	@Override
	public void delete(Product product) {
		adminProductDAO.delete(product);
	}

}
