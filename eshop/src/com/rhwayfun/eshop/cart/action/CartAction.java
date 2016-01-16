package com.rhwayfun.eshop.cart.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rhwayfun.eshop.cart.entity.Cart;
import com.rhwayfun.eshop.cart.entity.CartItem;
import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.product.service.IProductService;

public class CartAction extends ActionSupport {

	private int pid;
	private int count;
	
	private IProductService productService;
	
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/*****************以下的action的实现方法*********************/
	
	public String addToCart() throws Exception{
		//首先根据pid查询商品的信息
		Product product = productService.findProductByPid(pid);
		//获取购物车的对象
		Cart cart = getCart();
		//创建一个购物项
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setCount(count);
		cart.addToCart(cartItem);
		ActionContext.getContext().getSession().put("cart", cart);
		return "addToCart";
	}
	
	public String clearCart() throws Exception{
		//获取购物车
		Cart cart = getCart();
		cart.clearCart();
		ActionContext.getContext().getSession().remove("cart");
		return "clearCart";
	}
	
	public String remove() throws Exception{
		Cart cart = getCart();
		cart.remove(pid);
		return "remove";
	}
	
	public String showCart() throws Exception{
		return "showCart";
	}

	private Cart getCart() {
		Cart cart = (Cart) ActionContext.getContext().getSession().get("cart");
		if(cart == null){
			//创建一个购物车
			cart = new Cart();
		}
		return cart;
	}
}
