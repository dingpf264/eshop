package com.rhwayfun.eshop.admin.category.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rhwayfun.eshop.admin.category.dao.IAdminCategoryDAO;
import com.rhwayfun.eshop.category.entity.Category;

public class AdminCategoryDAO extends HibernateDaoSupport implements IAdminCategoryDAO {

	@Override
	public List<Category> findAll() throws Exception {
		String hql = "from Category";
		List list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public void save(Category category) {
		Session session = getSession();
		session.beginTransaction();
		session.save(category);
//		this.getHibernateTemplate().save(category);
	}

	@Override
	public Category findByCid(Integer cid) {
		Category category = this.getHibernateTemplate().get(Category.class, cid);
		if(category != null){
			return category;
		}
		return null;
	}

	@Override
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

	@Override
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
}
