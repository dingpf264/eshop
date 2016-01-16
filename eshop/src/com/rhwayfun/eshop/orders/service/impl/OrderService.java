package com.rhwayfun.eshop.orders.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rhwayfun.eshop.orders.dao.IOrderDAO;
import com.rhwayfun.eshop.orders.entity.Orders;
import com.rhwayfun.eshop.orders.service.IOrderService;
import com.rhwayfun.eshop.utils.PageBean;

@Transactional
public class OrderService implements IOrderService {

	private IOrderDAO orderDAO;
	
	public void setOrderDAO(IOrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	@Override
	public void save(Orders order) {
		orderDAO.save(order);
	}

	@Override
	public PageBean<Orders> findPageOrdersByUid(Integer userid,int currentPage) {
		//创建一个PageBean对象
		PageBean<Orders> page = new PageBean<Orders>();
		//获取订单总数
		int totalCount = orderDAO.findCountByUid(userid);
		//设置
		page.setTotalCount(totalCount);
		page.setCurrentPage(currentPage);
		int pageSize = 5;
		page.setPageSize(pageSize);
		//计算总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		page.setTotalPage(totalPage);
		int startIndex = (currentPage - 1) * pageSize;
		List<Orders> list = orderDAO.findPageOrdersByUid(userid, startIndex, pageSize);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}

	@Override
	public Orders findOrderByOid(Integer oid) {
		return orderDAO.findOrderByOid(oid);
	}

	@Override
	public void update(Orders currOrder) {
		orderDAO.update(currOrder);
	}

	@Override
	public void deleteOrderByOid(int oid) {
		orderDAO.deleteOrderByOid(oid);
	}

}
