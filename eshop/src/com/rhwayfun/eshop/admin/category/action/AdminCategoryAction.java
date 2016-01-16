package com.rhwayfun.eshop.admin.category.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.rhwayfun.eshop.admin.category.service.IAdminCategoryService;
import com.rhwayfun.eshop.category.entity.Category;

public class AdminCategoryAction implements ModelDriven<Category>{

	private Category category = new Category();
	
	@Override
	public Category getModel() {
		return category;
	}
	
	private IAdminCategoryService adminCategoryService;
	
	public void setAdminCategoryService(IAdminCategoryService adminCategoryService) {
		this.adminCategoryService = adminCategoryService;
	}

	/******************以下是action的实现***********************/
	
	public String findAll() throws Exception{
		List<Category> list = adminCategoryService.findAll();
		//放入值栈中
		ActionContext.getContext().getValueStack().set("categories", list);
		return "findAll";
	}
	
	public String save() throws Exception{
		adminCategoryService.save(category);
		List<Category> list = adminCategoryService.findAll();
		//放入值栈中
		ActionContext.getContext().getValueStack().set("categories", list);
		return "saveSuccess";
	}
	
	public String edit() throws Exception{
		//根据cid获取一级分类的信息
		category = adminCategoryService.findByCid(category.getCid());
		return "edit";
	}
	
	public String update() throws Exception{
		adminCategoryService.update(category);
		return "update";
	}
	
	public String delete() throws Exception{
		//获取一级分类
		category = adminCategoryService.findByCid(category.getCid());
		adminCategoryService.delete(category);
		return "delete";
	}
}
