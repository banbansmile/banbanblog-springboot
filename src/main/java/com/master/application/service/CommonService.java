package com.master.application.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.master.application.bean.Ariticle;
import com.master.application.bean.AriticleComment;
import com.master.application.bean.BlogComment;
import com.master.application.bean.BlogUser;
import com.master.application.bean.Mod;
import com.master.application.bean.Tag;
import com.master.application.util.Result;

public interface CommonService {

	public Result getAriticleList(int pageIndex, int pageSize, String typeIds, String tagIds) throws Exception;

	public Map<String, Ariticle> getAriticleById(int id, boolean getNext) throws Exception;

	public void addVisitCount(int id) throws Exception;

	public void addZanCount(int id) throws Exception;

	public Result getAriticleCommentList(int pageIndex, int pageSize, int ariticleId) throws Exception;

	public List<Mod> getModList() throws Exception;

	public List<Map<String, Object>> getAllAriticleSimpleInfo() throws Exception;

	public void addLikeCount(int id) throws Exception;

	public void addDisLikeCount(int id) throws Exception;

	public List<Tag> getTagAriticleCountList() throws Exception;

	public void sendEmail(String usercode, String email) throws Exception;

	public void sendEmail1(String email) throws Exception;

	public void userRegister(BlogUser user, String authCode) throws Exception;

	public Map<String, Object> userLogin(String account, String password) throws Exception;

	public void updateUserName(String usercode, String newusername) throws Exception;

	public void updatePassword(String usercode, String oldpassword, String newpassword) throws Exception;

	public void updateEmail(String usercode, String email, String authcode) throws Exception;

	public BlogUser getBlogUserUserCode(String usercode) throws Exception;

	public void updateImage(String usercode, String image) throws Exception;

	public void findBackPassword(String email, String password, String authcode) throws Exception;

	public void addBlogComment(BlogComment b) throws Exception;

	public PageInfo<BlogComment> getBlogComment(int pageIndex, int pageSize) throws Exception;

	public void addBlogCommentLike(int id) throws Exception;

	public void addBlogCommentDisLike(int id) throws Exception;

	public Map<String, Integer> getBlogCommentCount() throws Exception;

	public void addAriticleComment(AriticleComment a) throws Exception;

	public List<Map> getTop2AriticleCount() throws Exception;

}
