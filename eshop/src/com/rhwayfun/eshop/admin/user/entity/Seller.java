package com.rhwayfun.eshop.admin.user.entity;

/**
 * Adminuser entity. @author MyEclipse Persistence Tools
 */

public class Seller implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer userid;
	private String username;
	private String password;

	// Constructors

	/** default constructor */
	public Seller() {
	}

	/** full constructor */
	public Seller(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}