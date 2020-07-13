package com.master.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.master.application.bean.Mod;

public interface ModDao {
	
	public void addMod(Mod m);
	
	public List<Mod> getModList();
	
	public void deleteMod(@Param("id") int id);
}
