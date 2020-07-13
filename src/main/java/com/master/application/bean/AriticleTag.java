package com.master.application.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class AriticleTag {

	@JSONField(name = "id")
	private int id;

	@JSONField(name = "ariticle_id")
	private int ariticleId;

	@JSONField(name = "tag_id")
	private int tagId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAriticleId() {
		return ariticleId;
	}

	public void setAriticleId(int ariticleId) {
		this.ariticleId = ariticleId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

}
