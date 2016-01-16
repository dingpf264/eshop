package com.rhwayfun.eshop.admin.order.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.rhwayfun.eshop.admin.order.service.IAdminOrderService;
import com.rhwayfun.eshop.orders.entity.Orderitem;
import com.rhwayfun.eshop.orders.entity.Orders;
import com.rhwayfun.eshop.utils.PageBean;

public class AdminOrderAction implements ModelDriven<Orders>{

	private Orders order = new Orders();
	
	@Override
	public Orders getModel() {
		return order;
	}

	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	private IAdminOrderService adminOrderService;

	public void setAdminOrderService(IAdminOrderService adminOrderService) {
		this.adminOrderService = adminOrderService;
	}
	
	/***************以下是action的实现****************/
	
	public String findAllByPage() throws Exception{
		PageBean<Orders> oList = adminOrderService.findAllByPage(currentPage);
		ActionContext.getContext().getValueStack().set("oList", oList);
		return "findAllByPage";
	}
	
	public String showDetail() throws Exception{
		List<Orderitem> orderitems = adminOrderService.findItemsByOid(order.getOid());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("orderitems", orderitems);
		return "showDetail";
	}
	
	public String updateState() throws Exception{
		order = adminOrderService.findByOid(order.getOid());
		order.setState(3);
		//设置订单的法国时间
		order.setDeliverytime(new Timestamp(new Date().getTime()));
		adminOrderService.updateState(order);
		return "updateState";
	}
	
	public String findByState() throws Exception{
		PageBean<Orders> oList = adminOrderService.findPageByCriteria(currentPage,order.getState());
		ActionContext.getContext().getValueStack().set("oList", oList);
		return "findByState";
	}
}
