package com.master.application.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class Mod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private int id;

	@JSONField(name = "content")
	private String content;

	@JSONField(name = "add_time")
	private Timestamp addTime;

	@JSONField(name = "user_id")
	private int userId;

	@JSONField(name = "user_name")
	private String userName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
