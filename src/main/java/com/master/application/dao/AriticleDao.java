package com.master.application.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.master.application.bean.Ariticle;

public interface AriticleDao {

	public Integer addAriticle(Ariticle a);

	public void updateAriticle(Ariticle a);

	public List<Ariticle> getAriticleList(@Param("typeIds") List<String> typeIds);

	public List<Ariticle> getAriticleListByTagAndType(@Param("typeIds") List<String> typeIds,
			@Param("tagIds") List<String> tagIds);

	public List<Ariticle> getAriticleListByType(@Param("type_id") int type_id);

	public Ariticle getAriticleById(@Param("id") int id);

	public Ariticle getNextAriticleById(@Param("id") int id);

	public Ariticle getLastAriticleById(@Param("id") int id);

	public void deleteAriticle(@Param("id") int id);

	public void addZanCount(@Param("id") int id);

	public void addVisitCount(@Param("id") int id);

	public List<Ariticle> getAllAriticleSimpleInfo();

	public List<Map> getTop2AriticleCount();

}
