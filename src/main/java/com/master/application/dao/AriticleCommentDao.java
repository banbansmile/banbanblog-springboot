package com.master.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.master.application.bean.AriticleComment;

public interface AriticleCommentDao {
	
	public void addAriticleComment(AriticleComment c);
	
	public void updateAriticleComment(AriticleComment c);
	
	public void deleteAriticleComment(@Param("id") int id);
	
	public void addLike(@Param("id") int id);
	
	public void addDisLike(@Param("id") int id);
	
	public List<AriticleComment>getAriticleCommentList(@Param("ariticleId")int ariticleId);
	
	public int getAriticleCommentCount(@Param("id") int id);
	
	public int getAriticleCommentUserCount(@Param("id") int id);

}
