package com.rhwayfun.eshop.admin.order.service;

import java.util.List;

import com.rhwayfun.eshop.orders.entity.Orderitem;
import com.rhwayfun.eshop.orders.entity.Orders;
import com.rhwayfun.eshop.utils.PageBean;

public interface IAdminOrderService {

	PageBean<Orders> findAllByPage(int currentPage);

	List<Orderitem> findItemsByOid(Integer oid);

	Orders findByOid(Integer oid);

	void updateState(Orders order);

	PageBean<Orders> findPageByCriteria(int currentPage, Integer state);
}
