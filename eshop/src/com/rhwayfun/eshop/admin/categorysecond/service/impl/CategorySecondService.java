package com.rhwayfun.eshop.admin.categorysecond.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rhwayfun.eshop.admin.categorysecond.dao.ICategorySecondDAO;
import com.rhwayfun.eshop.admin.categorysecond.service.ICategorySecondService;
import com.rhwayfun.eshop.category.entity.Categorysecond;
import com.rhwayfun.eshop.utils.PageBean;

@Transactional
public class CategorySecondService implements ICategorySecondService {

	private ICategorySecondDAO categorySecondDAO;
	
	public void setCategorySecondDAO(ICategorySecondDAO categorySecondDAO) {
		this.categorySecondDAO = categorySecondDAO;
	}

	@Override
	public PageBean<Categorysecond> findAllByPage(int currentPage) {
		//创建一个PageBean对象
		PageBean<Categorysecond> page = new PageBean<Categorysecond>();
		page.setCurrentPage(currentPage);
		int pageSize = 6;
		page.setPageSize(pageSize);
		int totalCount = 0;
		totalCount = categorySecondDAO.findCountCsid();
		page.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		page.setTotalPage(totalPage);
		int startIndex = (currentPage - 1) * pageSize;
		List<Categorysecond> list = categorySecondDAO.findAllByPage(currentPage, startIndex, pageSize);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}

	@Override
	public void save(Categorysecond categorysecond) {
		categorySecondDAO.save(categorysecond);
	}

	@Override
	public Categorysecond findByCsid(Integer csid) {
		return categorySecondDAO.findByCsid(csid);
	}

	@Override
	public void delete(Categorysecond categorysecond) {
		categorySecondDAO.delete(categorysecond);
	}

	@Override
	public void update(Categorysecond categorysecond) {
		categorySecondDAO.update(categorysecond);
	}

	@Override
	public List<Categorysecond> findAll() {
		return categorySecondDAO.findAll();
	}

}
