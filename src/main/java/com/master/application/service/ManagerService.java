package com.master.application.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.master.application.bean.Ariticle;
import com.master.application.bean.AriticleType;
import com.master.application.bean.Mod;
import com.master.application.bean.Tag;

public interface ManagerService {
	public List<AriticleType>getAriticleTypeList()throws Exception;
	
	public List<Tag>getTagList()throws Exception;
	
	public void saveAriticle(Ariticle a,String tag_ids)throws Exception;
	
	public PageInfo<Ariticle> getAriticleList(int pageIndex,int pageSize,String typeIds)throws Exception;
	
	public void deleteAritile(int id)throws Exception;
	
	public PageInfo<Mod> getModList(int pageIndex,int pageSize)throws Exception;
	
	public void addMod(Mod m) throws Exception;
	
	public void deleteMod(int id)throws Exception;
}
