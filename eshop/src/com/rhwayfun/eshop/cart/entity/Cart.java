package com.rhwayfun.eshop.cart.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车封装类
 * <p>Title:Cart</p>
 * <p>Description:</p>
 * @author rhwayfun
 * @date Jan 10, 2016 7:36:56 PM
 * @version 1.0
 */
public class Cart {

	//封装购物车项的集合
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer, CartItem>();
	//总计
	private double total;
	
	//获取购物车所有的购物项
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//获得总价
	public Double getTotal() {
		return total;
	}
	
	//清空购物车
	public void clearCart(){
		//移除所有的购物项
		map.clear();
		//设置总价为0
		total = 0;
	}
	
	//添加到购物车
	public void addToCart(CartItem cartItem){
		//获取商品的pid
		Integer pid = cartItem.getProduct().getPid();
		//首先判断购物车中是否有重复的商品
		if(map.containsKey(pid)){
			//获取原来的购物项
			CartItem originCartItem = map.get(pid);
			originCartItem.setCount(originCartItem.getCount() + cartItem.getCount());
		}else{
			map.put(pid, cartItem);
		}
		
		//设置总价的值
		total += cartItem.getSubTotal();
	}
	
	//移除购物项
	public void remove(Integer pid){
		CartItem item = map.remove(pid);
		//重新设置总价
		total -= item.getSubTotal();
	}
}
