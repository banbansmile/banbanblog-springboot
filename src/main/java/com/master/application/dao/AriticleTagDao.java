package com.master.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AriticleTagDao {

	
	public void deleteTagByAriticle(@Param("ariticleId") int ariticleId);
	
	public void addAriticleTag(@Param("ariticleId") int ariticleId,@Param("tagIds")List<String>tagIds);
}
