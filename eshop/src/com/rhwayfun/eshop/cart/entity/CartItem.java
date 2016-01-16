package com.rhwayfun.eshop.cart.entity;

import com.rhwayfun.eshop.product.entity.Product;

/**
 * 购物车项
 * <p>Title:CartItem</p>
 * <p>Description:</p>
 * @author rhwayfun
 * @date Jan 10, 2016 7:35:09 PM
 * @version 1.0
 */
public class CartItem {

	//商品
	private Product product;
	//小计
	private Double subTotal;
	//数量
	private Integer count;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Double getSubTotal() {
		return count * product.getShopPrice();
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	
}
