package com.rhwayfun.eshop.admin.user.action;

import com.opensymphony.xwork2.ActionContext;
import com.rhwayfun.eshop.admin.user.entity.Seller;
import com.rhwayfun.eshop.admin.user.service.IAdminUserService;

public class SellerAction {

	private Seller seller;
	
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	private IAdminUserService adminUserService;

	public void setAdminUserService(IAdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	//登录首页
	public String manager() throws Exception{
		return "manager";
	}

	//loginSeller
	public String loginSeller() throws Exception{
		Seller seller1 = adminUserService.loginSeller(seller);
		if(seller1 != null){
			//保存到session中
			ActionContext.getContext().getSession().put("seller", seller1);
			return "loginSellerSuccess";
		}
		return "loginSellerFailue";
	}
}
