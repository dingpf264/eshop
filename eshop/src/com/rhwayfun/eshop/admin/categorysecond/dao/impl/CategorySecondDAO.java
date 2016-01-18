package com.rhwayfun.eshop.admin.categorysecond.dao.impl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rhwayfun.eshop.admin.categorysecond.dao.ICategorySecondDAO;
import com.rhwayfun.eshop.category.entity.Categorysecond;
import com.rhwayfun.eshop.utils.PageHibernateCallback;

public class CategorySecondDAO extends HibernateDaoSupport implements
		ICategorySecondDAO {

	@Override
	public List<Categorysecond> findAllByPage(int currentPage, int startIndex,
			int pageSize) {
		String hql = "from Categorysecond order by csid desc";
		List<Categorysecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Categorysecond>(hql, null,
						startIndex, pageSize));
		if(list != null && list.size()> 0){
			return list;
		}
		return null;
	}

	@Override
	public int findCountCsid() {
		String hql = "select count(*) from Categorysecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public void save(Categorysecond categorysecond) {
		this.getHibernateTemplate().save(categorysecond);
	}

	@Override
	public Categorysecond findByCsid(Integer csid) {
		String hql = "from Categorysecond where csid = ?";
		List list = this.getHibernateTemplate().find(hql, csid);
		if(list != null && list.size() > 0){
			return (Categorysecond) list.get(0);
		}
		return null;
	}

	@Override
	public void delete(Categorysecond categorysecond) {
		this.getHibernateTemplate().delete(categorysecond);
	}

	@Override
	public void update(Categorysecond categorysecond) {
		this.getHibernateTemplate().update(categorysecond);
	}

	@Override
	public List<Categorysecond> findAll() {
		String hql = "from Categorysecond order by csid";
		List list = this.getHibernateTemplate()
				.find(hql);
		if(list !=null && list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<Categorysecond> findCs(int cid){
		String hql = "select cs from Categorysecond cs where cs.category.cid = ?";
		List<Categorysecond> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
}
