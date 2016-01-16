package com.rhwayfun.eshop.product.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rhwayfun.eshop.product.dao.IProductDAO;
import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.utils.PageHibernateCallback;

public class ProductDAO extends HibernateDaoSupport implements IProductDAO {

	@Override
	public List<Product> findHotProducts() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("isHot", 1));
		List list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<Product> findNewProducts() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public Product findDetail(int pid) {
		List list = this.getHibernateTemplate().find(
				"from Product where pid = ?", pid);
		if (list != null && list.size() > 0) {
			return (Product) list.get(0);
		}
		return null;
	}

	@Override
	public List<Product> findPageProductsByCategory(int cid, int startIndex,
			int pageSize) {
		String hql = "select p from Product p join p.categorysecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { cid },
						startIndex, pageSize));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public int findCountByCid(int cid) {
		String hql = "select count(*) from Product p where p.categorysecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Product> findPageProductsByCategorysecond(int csid,
			int startIndex, int pageSize) {
		String hql = "select p from Product p join p.categorysecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { csid },
						startIndex, pageSize));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public int findCountByCsid(int csid) {
		String hql = "select count(*) from Product p join p.categorysecond cs where cs.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public Product findProductByPid(int pid) {
		String hql = "from Product where pid = ?";
		List list = this.getHibernateTemplate().find(hql, pid);
		if(list != null && list.size() > 0){
			return (Product) list.get(0);
		}
		return null;
	}

}
