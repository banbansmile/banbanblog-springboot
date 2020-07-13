package com.master.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.master.application.bean.Tag;

public interface TagDao {
	
	public void addTag(Tag t);
	
	public List<Tag> getTagList();
	
	public void deleteTag(@Param("id") int id);
	
	public List<Tag> getTagAriticleCountList();
}
