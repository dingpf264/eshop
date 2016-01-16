package com.rhwayfun.eshop.category.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rhwayfun.eshop.category.dao.ICategoryDAO;
import com.rhwayfun.eshop.category.entity.Category;

public class CategoryDAO extends HibernateDaoSupport implements ICategoryDAO {

	@Override
	public List<Category> findAll() throws Exception {
		List list = this.getHibernateTemplate().find("from Category");
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

}
