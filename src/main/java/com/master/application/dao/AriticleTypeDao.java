package com.master.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.master.application.bean.AriticleType;

public interface AriticleTypeDao {
	
	public void addAriticleType(AriticleType t);
	
	public List<AriticleType> getAriticleTypeList();
	
	public void deleteAriticleType(@Param("id") int id);
}
