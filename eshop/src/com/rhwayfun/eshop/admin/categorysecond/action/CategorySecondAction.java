package com.rhwayfun.eshop.admin.categorysecond.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.rhwayfun.eshop.admin.category.service.IAdminCategoryService;
import com.rhwayfun.eshop.admin.categorysecond.service.ICategorySecondService;
import com.rhwayfun.eshop.category.entity.Category;
import com.rhwayfun.eshop.category.entity.Categorysecond;
import com.rhwayfun.eshop.utils.PageBean;

public class CategorySecondAction implements ModelDriven<Categorysecond>{

	private Categorysecond categorysecond = new Categorysecond();
	private int currentPage;
	
	@Override
	public Categorysecond getModel() {
		return categorysecond;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	private ICategorySecondService categorySecondService;
	private IAdminCategoryService categoryService;

	public void setCategorySecondService(
			ICategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	
	public void setCategoryService(IAdminCategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/****************以下是action的实现*******************/
	
	public String findAllByPage() throws Exception{
		PageBean<Categorysecond> csList = categorySecondService.findAllByPage(currentPage);
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "findAllByPage";
	}
	
	public String addPage() throws Exception{
		List<Category> cList = categoryService.findAll();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("cList", cList);
		return "addPage";
	}
	
	public String save() throws Exception{
		categorySecondService.save(categorysecond);
		PageBean<Categorysecond> csList = categorySecondService.findAllByPage(currentPage);
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "save";
	}
	
	public String edit() throws Exception{
		categorysecond = categorySecondService.findByCsid(categorysecond.getCsid());
		List<Category> cList = categoryService.findAll();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("cList", cList);
		return "edit";
	}
	
	public String delete() throws Exception{
		categorysecond = categorySecondService.findByCsid(categorysecond.getCsid());
		categorySecondService.delete(categorysecond);
		return "delete";
	}
	
	public String update() throws Exception{
		categorysecond = categorySecondService.findByCsid(categorysecond.getCsid());
		categorySecondService.update(categorysecond);
		return "update";
	}
}
