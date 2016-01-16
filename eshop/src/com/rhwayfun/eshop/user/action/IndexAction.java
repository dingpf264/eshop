package com.rhwayfun.eshop.user.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.rhwayfun.eshop.category.entity.Category;
import com.rhwayfun.eshop.category.service.ICategoryService;
import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.product.service.IProductService;

public class IndexAction extends ActionSupport {

	private ICategoryService categoryService;
	private IProductService productService;
	
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public String execute() throws Exception {
		List<Category> list = categoryService.findAll();
		List<Product> hotlist = productService.findHotProducts();
		List<Product> newlist = productService.findNewProducts();
		//放入session
		Map session = ActionContext.getContext().getSession();
		session.put("categories", list);
		//热门商品放入值栈中
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("hotProducts", hotlist);
		valueStack.set("newProduts", newlist);
		return "index";
	}
}
