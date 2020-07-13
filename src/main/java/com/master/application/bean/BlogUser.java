package com.master.application.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class BlogUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private int id;

	@JSONField(name = "user_code")
	private String userCode;

	@JSONField(name = "user_name")
	private String userName;

	@JSONField(name = "password")
	private String password;

	@JSONField(name = "email")
	private String email;
	
	@JSONField(name = "address")
	private String address;
	
	@JSONField(name = "type")
	private int type=1;
	
	@JSONField(name = "image")
	private String image;
	
	
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@JSONField(name = "register_time")
	private Timestamp registerTime;
	
	

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
