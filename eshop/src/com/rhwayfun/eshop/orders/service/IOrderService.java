package com.rhwayfun.eshop.orders.service;

import java.util.List;

import com.rhwayfun.eshop.orders.entity.Orders;
import com.rhwayfun.eshop.utils.PageBean;

public interface IOrderService {

	void save(Orders order);

	PageBean<Orders> findPageOrdersByUid(Integer userid,int currentPage);

	Orders findOrderByOid(Integer oid);

	void update(Orders currOrder);

	void deleteOrderByOid(int oid);

}
