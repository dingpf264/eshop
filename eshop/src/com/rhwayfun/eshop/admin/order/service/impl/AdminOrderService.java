package com.rhwayfun.eshop.admin.order.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rhwayfun.eshop.admin.order.dao.IAdminOrderDAO;
import com.rhwayfun.eshop.admin.order.service.IAdminOrderService;
import com.rhwayfun.eshop.orders.entity.Orderitem;
import com.rhwayfun.eshop.orders.entity.Orders;
import com.rhwayfun.eshop.utils.PageBean;

@Transactional
public class AdminOrderService implements IAdminOrderService {

	private IAdminOrderDAO adminOrderDAO;
	
	public void setAdminOrderDAO(IAdminOrderDAO adminOrderDAO) {
		this.adminOrderDAO = adminOrderDAO;
	}

	@Override
	public PageBean<Orders> findAllByPage(int currentPage) {
		//创建一个PageBean
		PageBean<Orders> page = new PageBean<Orders>();
		//设置当前页数
		page.setCurrentPage(currentPage);
		//获取总的记录数
		int totalCount = 0;
		totalCount = adminOrderDAO.findAllCount();
		//设置总的记录数
		page.setTotalCount(totalCount);
		int pageSize = 8;
		page.setPageSize(pageSize);
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		page.setTotalPage(totalPage);
		//当前页第一条记录的位置，或者说是rownum
		int startIndex = (currentPage - 1) * pageSize;
		List<Orders> list = adminOrderDAO.findAllByPage(startIndex, pageSize);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}

	@Override
	public List<Orderitem> findItemsByOid(Integer oid) {
		return adminOrderDAO.findItemsByOid(oid);
	}

	@Override
	public Orders findByOid(Integer oid) {
		return adminOrderDAO.findByOid(oid);
	}

	@Override
	public void updateState(Orders order) {
		adminOrderDAO.updateState(order);
	}

	@Override
	public PageBean<Orders> findPageByCriteria(int currentPage, Integer state) {
		//创建一个PageBean
		PageBean<Orders> page = new PageBean<Orders>();
		//设置当前页数
		page.setCurrentPage(currentPage);
		//获取总的记录数
		int totalCount = 0;
		totalCount = adminOrderDAO.findCriteriaCount(state);
		//设置总的记录数
		page.setTotalCount(totalCount);
		int pageSize = 8;
		page.setPageSize(pageSize);
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		page.setTotalPage(totalPage);
		//当前页第一条记录的位置，或者说是rownum
		int startIndex = (currentPage - 1) * pageSize;
		List<Orders> list = adminOrderDAO.findPageByCriteria(state,startIndex, pageSize);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}

}
