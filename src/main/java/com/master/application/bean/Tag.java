package com.master.application.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Tag {

	@JSONField(name = "id")
	private int id;

	@JSONField(name = "tag_name")
	private String tagName;

	@JSONField(name = "image")
	private String image;

	@JSONField(name = "ariticle_count")
	private int ariticleCount = 0;
	
	
	@JSONField(name = "visit_count")
	private int visitCount=0;
	
	

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

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

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getAriticleCount() {
		return ariticleCount;
	}

	public void setAriticleCount(int ariticleCount) {
		this.ariticleCount = ariticleCount;
	}

}
