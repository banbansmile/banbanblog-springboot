package com.master.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.master.application.bean.BlogUser;

public interface BlogUserDao {

	public BlogUser getUserByCode(@Param("userCode") String userCode);

	public void addBlogUser(BlogUser user);

	public void updateBlogUser(BlogUser user);

	public void updatePassword_(@Param("id") int id, @Param("newPassword") String newPassword);

	public void deleteBlogUser(@Param("id") int id);

	public List<BlogUser> getBlogUserByEmail(@Param("email") String email);

	public void updateUserName(@Param("usercode") String usercode, @Param("username") String username);

	public void updatePassword(@Param("usercode") String usercode, @Param("newpassword") String newpassword);

	public void updateEmail(@Param("usercode") String usercode, @Param("email") String email);
	
	public void updateImage(@Param("usercode") String usercode, @Param("image") String image);
}
