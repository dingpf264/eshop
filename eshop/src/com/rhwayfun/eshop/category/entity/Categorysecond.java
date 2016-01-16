package com.rhwayfun.eshop.category.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Categorysecond entity. @author MyEclipse Persistence Tools
 */

public class Categorysecond implements java.io.Serializable {

	// Fields

	private Integer csid;
	private Category category;
	private String csname;
	private Set products = new HashSet(0);

	// Constructors

	/** default constructor */
	public Categorysecond() {
	}

	/** minimal constructor */
	public Categorysecond(Category category, String csname) {
		this.category = category;
		this.csname = csname;
	}

	/** full constructor */
	public Categorysecond(Category category, String csname, Set products) {
		this.category = category;
		this.csname = csname;
		this.products = products;
	}

	// Property accessors

	public Integer getCsid() {
		return this.csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCsname() {
		return this.csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}