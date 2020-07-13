package com.master.application.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class Ariticle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private int id;

	@JSONField(name = "type_id")
	private int typeId;

	@JSONField(name = "title")
	private String title;
	
	@JSONField(name = "description")
	private String description;

	@JSONField(name = "image")
	private String image;

	@JSONField(name = "content")
	private String content;

	@JSONField(name = "user_id")
	private int userId;

	@JSONField(name = "visit_count")
	private int visitCount;

	@JSONField(name = "zan_count")
	private int zanCount;

	@JSONField(name = "add_time")
	private Timestamp addTime;

	@JSONField(name = "update_time")
	private Timestamp updateTime;

	@JSONField(name = "user_name")
	private String userName;
	
	@JSONField(name = "type_name")
	private String typeName;
	
	@JSONField(name = "year")
	private Integer year;
	
	
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@JSONField(name = "comment_count")
	private Integer commentCount;
	
	@JSONField(name = "commentuser_count")
	private Integer commentUserCount;
	
	

	public Integer getCommentUserCount() {
		return commentUserCount;
	}

	public void setCommentUserCount(Integer commentUserCount) {
		this.commentUserCount = commentUserCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public int getZanCount() {
		return zanCount;
	}

	public void setZanCount(int zanCount) {
		this.zanCount = zanCount;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
