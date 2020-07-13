package com.master.application.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class AuthCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private int id;
	
	@JSONField(name = "email")
	private String email;
	
	@JSONField(name = "auth_code")
	private String authCode;
	
	@JSONField(name = "add_time")
	private Timestamp addTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
	
	
}
