package com.rhwayfun.eshop.admin.order.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rhwayfun.eshop.admin.order.dao.IAdminOrderDAO;
import com.rhwayfun.eshop.orders.entity.Orderitem;
import com.rhwayfun.eshop.orders.entity.Orders;
import com.rhwayfun.eshop.utils.PageHibernateCallback;

public class AdminOrderDAO extends HibernateDaoSupport implements
		IAdminOrderDAO {

	@Override
	public List<Orders> findAllByPage(int startIndex,
			int pageSize) {
		String hql = "from Orders order by ordertime desc";
		List<Orders> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Orders>(hql, null, startIndex,
						pageSize));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public int findAllCount() {
		String hql = "select count(*) from Orders";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Orderitem> findItemsByOid(Integer oid) {
		String hql = "select s from Orderitem s join s.orders o where o.oid = ?";
		List list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public Orders findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Orders.class, oid);
	}

	@Override
	public void updateState(Orders order) {
		this.getHibernateTemplate().update(order);
	}

	@Override
	public List<Orders> findPageByCriteria(Integer state,
			int startIndex, int pageSize) {
		String hql = "select o from Orders o where o.state = ? order by ordertime desc";
		List<Orders> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Orders>(hql, new Object[] { state },
						startIndex, pageSize));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public int findCriteriaCount(Integer state) {
		String hql = "select count(*) from Orders o where o.state = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,state);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

}
