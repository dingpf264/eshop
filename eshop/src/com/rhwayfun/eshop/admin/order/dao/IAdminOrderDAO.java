package com.rhwayfun.eshop.admin.order.dao;

import java.util.List;

import com.rhwayfun.eshop.orders.entity.Orderitem;
import com.rhwayfun.eshop.orders.entity.Orders;

public interface IAdminOrderDAO {

	List<Orders> findAllByPage(int startIndex,int pageSize);

	int findAllCount();

	List<Orderitem> findItemsByOid(Integer oid);

	Orders findByOid(Integer oid);

	void updateState(Orders order);

	List<Orders> findPageByCriteria(Integer state,int startIndex, int pageSize);

	int findCriteriaCount(Integer state);
	
}
