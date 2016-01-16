package com.rhwayfun.eshop.admin.product.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rhwayfun.eshop.admin.product.dao.IAdminProductDAO;
import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.utils.PageHibernateCallback;

public class AdminProductDAO extends HibernateDaoSupport implements
		IAdminProductDAO {

	@Override
	public List<Product> findAllByPage(int currentPage, int startIndex,
			int pageSize) {
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, null, startIndex,
						pageSize));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public int findAllCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	@Override
	public Product findByPid(Integer pid) {
		String hql = "from Product where pid = ?";
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	@Override
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	@Override
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
}
