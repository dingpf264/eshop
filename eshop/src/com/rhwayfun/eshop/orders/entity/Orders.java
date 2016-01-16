package com.rhwayfun.eshop.orders.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.rhwayfun.eshop.user.entity.User;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private Integer oid;
	private User user;
	private Double total;
	private Timestamp ordertime;
	private Timestamp deliverytime;
	private Timestamp paytime;
	private Integer state;
	private String name;
	private String phone;
	private String address;
	private Set orderitems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** full constructor */
	public Orders(User user, Double total, Timestamp ordertime, Integer state,
			String name, String phone, String address, Set orderitems) {
		this.user = user;
		this.total = total;
		this.ordertime = ordertime;
		this.state = state;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.orderitems = orderitems;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Timestamp getOrdertime() {
		return this.ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	
	public Timestamp getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(Timestamp deliverytime) {
		this.deliverytime = deliverytime;
	}

	public Timestamp getPaytime() {
		return paytime;
	}

	public void setPaytime(Timestamp paytime) {
		this.paytime = paytime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set getOrderitems() {
		return this.orderitems;
	}

	public void setOrderitems(Set orderitems) {
		this.orderitems = orderitems;
	}

}