package com.rhwayfun.eshop.user.entity;

import java.util.Date;

/**
 * Userdetail entity. @author MyEclipse Persistence Tools
 */

public class Userdetail implements java.io.Serializable {

	// Fields

	private Integer userid;
	private User user;
	private Byte gender;
	private String truename;
	private Date birthday;
	private String email;
	private String address;
	private String phone;
	private Byte state;
	private String code;

	// Constructors

	/** default constructor */
	public Userdetail() {
	}

	/** minimal constructor */
	public Userdetail(User user, Byte gender) {
		this.user = user;
		this.gender = gender;
	}

	/** full constructor */
	public Userdetail(User user, Byte gender, String truename,
			Date birthday, String email, String address, String phone,
			Byte state, String code) {
		this.user = user;
		this.gender = gender;
		this.truename = truename;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.state = state;
		this.code = code;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Byte getGender() {
		return this.gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getTruename() {
		return this.truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Byte getState() {
		return this.state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}