package com.rhwayfun.eshop.product.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.rhwayfun.eshop.category.entity.Categorysecond;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Integer pid;
	private Categorysecond categorysecond;
	private String pname;
	private Double marketPrice;
	private Double shopPrice;
	private String image;
	private String descimage;
	private String pdesc;
	private Integer isHot;
	private Timestamp pdate;
	private Set orderitems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(Categorysecond categorysecond, String pname,
			Double marketPrice, Double shopPrice, String image, String pdesc,
			Integer isHot, Timestamp pdate, Set orderitems) {
		this.categorysecond = categorysecond;
		this.pname = pname;
		this.marketPrice = marketPrice;
		this.shopPrice = shopPrice;
		this.image = image;
		this.pdesc = pdesc;
		this.isHot = isHot;
		this.pdate = pdate;
		this.orderitems = orderitems;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Categorysecond getCategorysecond() {
		return this.categorysecond;
	}

	public void setCategorysecond(Categorysecond categorysecond) {
		this.categorysecond = categorysecond;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getShopPrice() {
		return this.shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getDescimage() {
		return descimage;
	}

	public void setDescimage(String descimage) {
		this.descimage = descimage;
	}

	public String getPdesc() {
		return this.pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getIsHot() {
		return this.isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Timestamp getPdate() {
		return this.pdate;
	}

	public void setPdate(Timestamp pdate) {
		this.pdate = pdate;
	}

	public Set getOrderitems() {
		return this.orderitems;
	}

	public void setOrderitems(Set orderitems) {
		this.orderitems = orderitems;
	}

}