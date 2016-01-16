package com.rhwayfun.eshop.product.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rhwayfun.eshop.product.dao.IProductDAO;
import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.product.service.IProductService;
import com.rhwayfun.eshop.utils.PageBean;

@Transactional
public class ProductService implements IProductService {

	private IProductDAO productDAO;
	
	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public List<Product> findHotProducts() {
		return productDAO.findHotProducts();
	}

	@Override
	public List<Product> findNewProducts() {
		return productDAO.findNewProducts();
	}

	@Override
	public Product findDetail(int pid) {
		return productDAO.findDetail(pid);
	}

	@Override
	public PageBean<Product> findPageProductsByCategory(int cid,int currentPage) {
		//创建一个PageBean对象
		PageBean page = new PageBean<Product>();
		//查询该一级分类的总记录数
		int totalCount = productDAO.findCountByCid(cid);
		//得到每页的显示的记录数
		int pageSize = 8;
		page.setPageSize(pageSize);
		//设置当前页
		page.setCurrentPage(currentPage);
		//设置总记录数
		page.setTotalCount(totalCount);
		//计算从那条记录开始
		int startIndex = (currentPage - 1) * pageSize;
		//计算总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		//设置总页数
		page.setTotalPage(totalPage);
		//调用categoryDAO接口获取该页的所有商品记录
		List<Product> list = productDAO.findPageProductsByCategory(cid, startIndex, pageSize);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}

	@Override
	public PageBean<Product> findPageProductsByCategorysecond(int csid,
			int currentPage) {
		//创建一个PageBean对象
		PageBean page = new PageBean<Product>();
		//查询该一级分类的总记录数
		int totalCount = productDAO.findCountByCsid(csid);
		//得到每页的显示的记录数
		int pageSize = 8;
		page.setPageSize(pageSize);
		//设置当前页
		page.setCurrentPage(currentPage);
		//设置总记录数
		page.setTotalCount(totalCount);
		//计算从那条记录开始
		int startIndex = (currentPage - 1) * pageSize;
		//计算总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		//设置总页数
		page.setTotalPage(totalPage);
		//调用categoryDAO接口获取该页的所有商品记录
		List<Product> list = productDAO.findPageProductsByCategorysecond(csid, startIndex, pageSize);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}

	@Override
	public Product findProductByPid(int pid) {
		return productDAO.findProductByPid(pid);
	}

	@Override
	public PageBean<Product> findPageProductsByName(String pname,int currentPage) {
		//创建一个PageBean对象
		PageBean page = new PageBean<Product>();
		//查询该一级分类的总记录数
		int totalCount = productDAO.findCountByPname(pname);
		//得到每页的显示的记录数
		int pageSize = 10;
		page.setPageSize(pageSize);
		//设置当前页
		page.setCurrentPage(currentPage);
		//设置总记录数
		page.setTotalCount(totalCount);
		//计算从那条记录开始
		int startIndex = (currentPage - 1) * pageSize;
		//计算总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		//设置总页数
		page.setTotalPage(totalPage);
		//调用categoryDAO接口获取该页的所有商品记录
		List<Product> list = productDAO.findPageProductsByName(pname, startIndex, pageSize);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}

}
