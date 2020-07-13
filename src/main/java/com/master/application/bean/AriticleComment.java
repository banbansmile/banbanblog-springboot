package com.master.application.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class AriticleComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private int id;

	@JSONField(name = "user_id")
	private int userId;

	@JSONField(name = "article_id")
	private int ariticleId;

	@JSONField(name = "comment")
	private String comment;

	@JSONField(name = "like_count")
	private int likeCount;

	@JSONField(name = "dislike_count")
	private int dislikeCount;

	@JSONField(name = "add_time")
	private Timestamp addTime;

	@JSONField(name = "user_name")
	private String userName;

	@JSONField(name = "address")
	private String address;
	
	@JSONField(name = "image")
	private String image;
	

	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAriticleId() {
		return ariticleId;
	}

	public void setAriticleId(int ariticleId) {
		this.ariticleId = ariticleId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
