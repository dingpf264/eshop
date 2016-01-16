package com.rhwayfun.eshop.admin.user.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.rhwayfun.eshop.admin.user.entity.Adminuser;
import com.rhwayfun.eshop.admin.user.entity.Seller;
import com.rhwayfun.eshop.admin.user.service.IAdminUserService;
import com.rhwayfun.eshop.user.entity.User;
import com.rhwayfun.eshop.utils.PageBean;

public class AdminUserAction implements ModelDriven<User>{

	private Adminuser adminuser;
	private int currentPage;
	
	public Adminuser getAdminuser() {
		return adminuser;
	}
	
	public void setAdminuser(Adminuser adminuser) {
		this.adminuser = adminuser;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}

	private IAdminUserService adminUserService;

	public void setAdminUserService(IAdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	/*****************以下是action的具体实现******************/
	
	public String admin() throws Exception{
		return "adminPage";
	}
	
	public String login() throws Exception{
		Adminuser adminuser1 = adminUserService.login(adminuser);
		if(adminuser1 != null){
			//保存到session中
			ActionContext.getContext().getSession().put("adminuser", adminuser1);
			return "loginSuccess";
		}
		return "loginFailue";
	}
	
	public String logout() throws Exception{
		ActionContext.getContext().getSession().remove("adminuser");
		return "logout";
	}
	
	public String findAllByPage() throws Exception{
		PageBean<User> uList = adminUserService.findAllByPage(currentPage);
		ActionContext.getContext().getValueStack().set("uList", uList);
		return "findAllByPage";
	}
	
	public String save() throws Exception{
		adminUserService.save(user);
		return "save";
	}
	
	public String delete() throws Exception{
		user = adminUserService.findByUid(user.getUserid());
		adminUserService.delete(user);
		return "delete";
	}
	
	public String edit() throws Exception{
		user = adminUserService.findByUid(user.getUserid());
		return "edit";
	}
	
	public String update() throws Exception{
		user = adminUserService.findByUid(user.getUserid());
		adminUserService.update(user);
		return "update";
	}
}
