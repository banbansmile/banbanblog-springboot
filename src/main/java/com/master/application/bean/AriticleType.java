package com.master.application.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class AriticleType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JSONField(name = "id")
	private int id;
	
	@JSONField(name = "type_name")
	private String typeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
