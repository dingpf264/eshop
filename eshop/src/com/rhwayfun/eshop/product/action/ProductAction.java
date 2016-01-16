package com.rhwayfun.eshop.product.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.product.service.IProductService;
import com.rhwayfun.eshop.utils.PageBean;

public class ProductAction extends ActionSupport {

	private int pid;
	private int cid;
	private int csid;
	private int currentPage;
	private String pname;
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getCsid() {
		return csid;
	}

	public void setCsid(int csid) {
		this.csid = csid;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	private IProductService productService;
	
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	/******************以下是Action的实现******************************/
	
	public String findDetail() throws Exception{
		Product product = productService.findDetail(pid);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("product", product);
		return "product";
	}
	
	public String findPageProductsByCategory() throws Exception{
		PageBean<Product> products = productService.findPageProductsByCategory(cid,currentPage);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("pageProducts", products);
//		ActionContext.getContext().getValueStack().set("pageBean", products);
		return "pageProducts";
	}
	
	public String findPageProductsByCategorysecond() throws Exception{
		PageBean<Product> products = productService.findPageProductsByCategorysecond(csid, currentPage);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("pageProducts2", products);
//		ActionContext.getContext().getValueStack().set("pageProducts", products);
		return "pageProducts2";
	}
	
	public String findPageProductsByName() throws Exception{
		PageBean<Product> products = productService.findPageProductsByName(pname,currentPage);
		ActionContext.getContext().getValueStack().set("pList", products);
		return "findPageProductsByName";
	}
}
