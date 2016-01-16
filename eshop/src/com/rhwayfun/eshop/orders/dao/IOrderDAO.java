package com.rhwayfun.eshop.orders.dao;

import java.util.List;

import com.rhwayfun.eshop.orders.entity.Orders;

public interface IOrderDAO {

	void save(Orders order);

	List<Orders> findPageOrdersByUid(Integer userid,int startIndex,int pageSize);

	int findCountByUid(Integer userid);

	Orders findOrderByOid(Integer oid);

	void update(Orders currOrder);

	void deleteOrderByOid(int oid);

}
