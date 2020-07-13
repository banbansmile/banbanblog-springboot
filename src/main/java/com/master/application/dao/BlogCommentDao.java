package com.master.application.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.master.application.bean.BlogComment;

public interface BlogCommentDao {

	public void addBlogComment(BlogComment b);

	public List<BlogComment> getBlogCommentList();

	public void deleteBlogComment(@Param("id") int id);
	
	public void addBlogCommentLike(@Param("id") int id);
	
	public void addBlogCommentDisLike(@Param("id") int id);
	
	public Map<String,Integer>getBlogCommentCount();
}
