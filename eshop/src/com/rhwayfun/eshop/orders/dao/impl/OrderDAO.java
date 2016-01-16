package com.rhwayfun.eshop.orders.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rhwayfun.eshop.orders.dao.IOrderDAO;
import com.rhwayfun.eshop.orders.entity.Orders;
import com.rhwayfun.eshop.utils.PageHibernateCallback;

public class OrderDAO extends HibernateDaoSupport implements IOrderDAO {

	@Override
	public void save(Orders order) {
		this.getHibernateTemplate().save(order);
	}

	@Override
	public List<Orders> findPageOrdersByUid(Integer userid, int startIndex,
			int pageSize) {
		String hql = "from Orders where userid = ? order by ordertime desc";
		List list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Orders>(hql, new Object[] { userid },
						startIndex, pageSize));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public int findCountByUid(Integer userid) {
		String hql = "select count(*) from Orders where userid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, userid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public Orders findOrderByOid(Integer oid) {
		Orders order = this.getHibernateTemplate().get(Orders.class, oid);
		if(order != null){
			return order;
		}
		return null;
	}

	@Override
	public void update(Orders currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	@Override
	public void deleteOrderByOid(int oid) {
		Orders order = this.getHibernateTemplate().get(Orders.class, oid);
		this.getHibernateTemplate().delete(order);
	}
}
