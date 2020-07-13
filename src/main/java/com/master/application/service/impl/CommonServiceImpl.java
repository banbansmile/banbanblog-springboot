package com.master.application.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.master.application.bean.Ariticle;
import com.master.application.bean.AriticleComment;
import com.master.application.bean.AuthCode;
import com.master.application.bean.BlogComment;
import com.master.application.bean.BlogUser;
import com.master.application.bean.Mod;
import com.master.application.bean.Tag;
import com.master.application.dao.AriticleCommentDao;
import com.master.application.dao.AriticleDao;
import com.master.application.dao.AuthCodeDao;
import com.master.application.dao.BlogCommentDao;
import com.master.application.dao.BlogUserDao;
import com.master.application.dao.ModDao;
import com.master.application.dao.TagDao;
import com.master.application.service.CommonService;
import com.master.application.shiro.BlogUserPasswordToken;
import com.master.application.util.Base64Utils;
import com.master.application.util.BlogJwtUtil;
import com.master.application.util.MD5Utils;
import com.master.application.util.Result;
import com.master.application.util.SendMailUtil;
import com.master.application.util.Utils;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	AriticleDao ariticleDao;

	@Autowired
	AriticleCommentDao ariticleCommonDao;

	@Autowired
	ModDao modDao;

	@Autowired
	TagDao tagDao;

	@Autowired
	BlogUserDao blogUserDao;

	@Autowired
	AuthCodeDao authCodeDao;

	@Autowired
	BlogCommentDao blogCommentDao;

	@Value("${server.port:80}")
	public int port;
	
	
	@Value("${domain.name}")
	public String domainName;
	
	private final static String blogAuthCode="班班博客验证码";
	private final static String updatePasword="博客密码更改";
	

	@Override
	public Result getAriticleList(int pageIndex, int pageSize, String typeIds, String tagIds) throws Exception {

		PageHelper.startPage(pageIndex, pageSize);
		List<Ariticle> list = null;

		// 查询根据TypeIds查询
		List<String> typeIdsList = new ArrayList<String>();
		if (!StringUtils.isBlank(typeIds)) {
			String[] ids = typeIds.split(",");
			typeIdsList = Arrays.asList(ids);
		}

		// 根据TagIds和TypeIds查询
		if (!StringUtils.isBlank(tagIds)) {
			List<String> tagIdsList = new ArrayList<String>();
			if (!StringUtils.isBlank(tagIds)) {
				String[] ids = tagIds.split(",");
				tagIdsList = Arrays.asList(ids);
				list = ariticleDao.getAriticleListByTagAndType(typeIdsList, tagIdsList);
			}
		} else {
			list = ariticleDao.getAriticleList(typeIdsList);
		}

		PageInfo<Ariticle> pageInfo = new PageInfo<Ariticle>(list);

		List<Ariticle> l = pageInfo.getList();

		for (Ariticle a : l) {
			int count = ariticleCommonDao.getAriticleCommentCount(a.getId());
			a.setCommentCount(count);
		}

		return new Result(Result.SUCCEEDED, "success", l, pageInfo.getTotal());
	}

	@Override
	public Map<String, Ariticle> getAriticleById(int id, boolean getNext) throws Exception {

		// 添加访问记录
		ariticleDao.addVisitCount(id);

		Map<String, Ariticle> m = new HashMap<String, Ariticle>();

		Ariticle a = ariticleDao.getAriticleById(id);
		m.put("current", a);

		if (a != null) {
			int count = ariticleCommonDao.getAriticleCommentCount(a.getId());
			int commentUserCount = ariticleCommonDao.getAriticleCommentUserCount(a.getId());
			a.setCommentCount(count);
			a.setCommentUserCount(commentUserCount);
		} else {
			m.put("current", new Ariticle());
		}

		if (a != null && getNext) {
			Ariticle b = ariticleDao.getLastAriticleById(id);
			m.put("last", b);

			Ariticle c = ariticleDao.getNextAriticleById(id);
			m.put("next", c);
		} else {
			m.put("last", new Ariticle());
			m.put("next", new Ariticle());
		}

		return m;
	}

	@Override
	public void addVisitCount(int id) throws Exception {
		ariticleDao.addVisitCount(id);
	}

	@Override
	public void addZanCount(int id) throws Exception {
		ariticleDao.addZanCount(id);
	}

	@Override
	public Result getAriticleCommentList(int pageIndex, int pageSize, int ariticleId) throws Exception {

		PageHelper.startPage(pageIndex, pageSize);
		List<AriticleComment> list = ariticleCommonDao.getAriticleCommentList(ariticleId);
		PageInfo<AriticleComment> pageInfo = new PageInfo<AriticleComment>(list);

		return new Result(Result.SUCCEEDED, "success", pageInfo.getList(), pageInfo.getTotal());
	}

	@Override
	public List<Mod> getModList() throws Exception {
		return modDao.getModList();
	}

	@Override
	public List<Map<String, Object>> getAllAriticleSimpleInfo() throws Exception {

		List<Ariticle> list = ariticleDao.getAllAriticleSimpleInfo();

		Map<Integer, List<Ariticle>> m = new HashMap<>();

		for (Ariticle a : list) {
			if (m.containsKey(a.getYear())) {
				List<Ariticle> l = m.get(a.getYear());
				l.add(a);
			} else {
				List<Ariticle> l = new ArrayList<>();
				l.add(a);
				m.put(a.getYear(), l);
			}
		}

		List<Map<String, Object>> returnList = new ArrayList<>();

		for (Integer year : m.keySet()) {

			Map<String, Object> m1 = new HashMap<>();
			m1.put("year", year);
			m1.put("list", m.get(year));
			returnList.add(m1);
		}

		Collections.sort(returnList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {

				int year1 = (int) o1.get("year");
				int year2 = (int) o2.get("year");
				return year2 - year1;
			}
		});

		return returnList;
	}

	@Override
	public void addLikeCount(int id) throws Exception {
		ariticleCommonDao.addLike(id);
	}

	@Override
	public void addDisLikeCount(int id) throws Exception {
		ariticleCommonDao.addDisLike(id);
	}

	@Override
	public List<Tag> getTagAriticleCountList() throws Exception {
		return tagDao.getTagAriticleCountList();
	}

	@Transactional
	@Override
	public void sendEmail1(String email) throws Exception {

		List<BlogUser> list = blogUserDao.getBlogUserByEmail(email);
		if (list != null && list.size() < 1) {
			throw new Exception(email + " 邮箱尚未注册");
		}

		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}

		AuthCode a = new AuthCode();
		a.setAuthCode(result);
		a.setEmail(email);

		authCodeDao.deleteAuthCodeByEmail(email);
		authCodeDao.saveAuthCode(a);

		final String emailContext = "你的验证码是:" + result + ", 10分钟内有效";

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					SendMailUtil.send(email, emailContext,blogAuthCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();

		// banbanblog@126.com Liu@123456 Miaoge123

		// banbanblog666@souhu.com Miaoge123456
	}

	@Transactional
	@Override
	public void sendEmail(String usercode, String email) throws Exception {

		List<BlogUser> list = blogUserDao.getBlogUserByEmail(email);
		if (list != null && list.size() > 0) {

			if (!StringUtils.isBlank(usercode) && usercode.equals(list.get(0).getUserCode())) {

			} else {
				throw new Exception(email + " 已被注册，请更换新邮箱");
			}

		}

		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}

		AuthCode a = new AuthCode();
		a.setAuthCode(result);
		a.setEmail(email);

		authCodeDao.deleteAuthCodeByEmail(email);
		authCodeDao.saveAuthCode(a);

		final String emailContext = "你的验证码是:" + result + " ,10分钟内有效";

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					SendMailUtil.send(email, emailContext,blogAuthCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	@Override
	public void userRegister(BlogUser user, String authCode) throws Exception {

		AuthCode a = authCodeDao.getAuthCodeByEmail(user.getEmail());

		if (a == null) {
			throw new Exception("验证码错误");
		}

		long now = System.currentTimeMillis();
		long authCodeTime = a.getAddTime().getTime();
		if ((now - authCodeTime) > 600000) {
			throw new Exception("验证码过期");
		}

		if (!a.getAuthCode().equals(authCode)) {
			throw new Exception("验证码错误");
		}

		BlogUser u = blogUserDao.getUserByCode(user.getUserCode());
		if (u != null) {
			throw new Exception(user.getUserCode() + " 已经注册，请更改账号");
		}

		String decodePassword = Base64Utils.decodeText(user.getPassword());

		// MD5加密

		String md5Password = MD5Utils.getMD5String(decodePassword);
		user.setPassword(md5Password);

		// 头像 http://127.0.0.1:8066/images/059b222c-8f52-4db3-ba31-45f8ef63f979.jpg

		String path = Utils.getdefaulImage(domainName,port);
		user.setImage(path);

		blogUserDao.addBlogUser(user);

		authCodeDao.deleteAuthCodeByEmail(user.getEmail());

	}

	@Override
	public Map<String, Object> userLogin(String account, String password) throws Exception {

		Subject currentUser = SecurityUtils.getSubject();
		
		
		String decodePassword = Base64Utils.decodeText(password);
		BlogUserPasswordToken blogUserPasswordToken=new BlogUserPasswordToken(account, decodePassword);
		//blogUserPasswordToken.setRememberMe(true);
		currentUser.login(blogUserPasswordToken);
		
		BlogUser u=(BlogUser)currentUser.getPrincipal();
		
		
		Map<String, Object> m = new HashMap<>();
		
		
		BlogUser tokenUser=new BlogUser();
		tokenUser.setId(u.getId());
		tokenUser.setPassword(decodePassword);
		tokenUser.setUserCode(u.getUserCode());
		
		String token = BlogJwtUtil.createJwtToekn(tokenUser);
		m.put("blogUser", u);
		m.put("token", token);
		
		return m;

	}

	@Override
	public void updateUserName(String usercode, String newusername) throws Exception {
		blogUserDao.updateUserName(usercode, newusername);
	}

	@Override
	public void updatePassword(String usercode, String oldpassword, String newpassword) throws Exception {

		BlogUser u = blogUserDao.getUserByCode(usercode);
		if (u == null) {
			throw new Exception("修改失败");
		}

		String old_password = Base64Utils.decodeText(oldpassword);

		String oldpasswordmd5 = MD5Utils.getMD5String(old_password);

		if (!oldpasswordmd5.equals(u.getPassword())) {
			throw new Exception("旧密码错误");
		}

		String new_password = Base64Utils.decodeText(newpassword);
		String newpasswordmd5 = MD5Utils.getMD5String(new_password);

		if (newpasswordmd5.equals(u.getPassword())) {
			throw new Exception("新密码与旧密码相同，修改失败");
		}

		blogUserDao.updatePassword(usercode, newpasswordmd5);

	}

	@Override
	public void updateEmail(String usercode, String email, String authcode) throws Exception {

		BlogUser u = blogUserDao.getUserByCode(usercode);
		if (u == null) {
			throw new Exception("修改失败");
		}

		AuthCode a = authCodeDao.getAuthCodeByEmail(email);

		if (a == null) {
			throw new Exception("验证码错误");
		}

		long now = System.currentTimeMillis();
		long authCodeTime = a.getAddTime().getTime();
		if ((now - authCodeTime) > 600000) {
			throw new Exception("验证码过期");
		}

		if (!a.getAuthCode().equals(authcode)) {
			throw new Exception("验证码错误");
		}

		blogUserDao.updateEmail(usercode, email);
		authCodeDao.deleteAuthCodeByEmail(email);

	}

	@Override
	public BlogUser getBlogUserUserCode(String usercode) throws Exception {

		BlogUser u = blogUserDao.getUserByCode(usercode);
		if (u != null) {
			return u;
		}
		throw new Exception("获取用户信息失败");
	}

	@Override
	public void updateImage(String usercode, String image) throws Exception {
		blogUserDao.updateImage(usercode, image);

	}

	@Transactional
	@Override
	public void findBackPassword(String email, String password, String authcode) throws Exception {

		AuthCode a = authCodeDao.getAuthCodeByEmail(email);

		if (a == null) {
			throw new Exception("验证码错误");
		}

		long now = System.currentTimeMillis();
		long authCodeTime = a.getAddTime().getTime();
		if ((now - authCodeTime) > 600000) {
			throw new Exception("验证码过期");
		}

		if (!a.getAuthCode().equals(authcode)) {
			throw new Exception("验证码错误");
		}

		List<BlogUser> list = blogUserDao.getBlogUserByEmail(email);

		if (list == null || list.size() < 1) {
			throw new Exception("用户不存在");
		}

		BlogUser u = list.get(0);

		String base64password = Base64Utils.decodeText(password);
		String newpassword = MD5Utils.getMD5String(base64password);

		blogUserDao.updatePassword(u.getUserCode(), newpassword);
		authCodeDao.deleteAuthCodeByEmail(email);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// SendEmail TO User
		final String emailContext = "你的账号:" + u.getUserCode() + " ,已于 " + sdf.format(new Date()) + " 更改为:"
				+ base64password;

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					SendMailUtil.send(email, emailContext,updatePasword);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

	@Override
	public void addBlogComment(BlogComment b) throws Exception {
		blogCommentDao.addBlogComment(b);

	}

	@Override
	public PageInfo<BlogComment> getBlogComment(int pageIndex, int pageSize) throws Exception {

		PageHelper.startPage(pageIndex, pageSize);
		List<BlogComment> list = blogCommentDao.getBlogCommentList();
		PageInfo<BlogComment> pageInfo = new PageInfo<BlogComment>(list);

		return pageInfo;
	}

	@Override
	public void addBlogCommentLike(int id) throws Exception {
		blogCommentDao.addBlogCommentLike(id);
	}

	@Override
	public void addBlogCommentDisLike(int id) throws Exception {
		blogCommentDao.addBlogCommentDisLike(id);

	}

	@Override
	public Map<String, Integer> getBlogCommentCount() throws Exception {
		return blogCommentDao.getBlogCommentCount();
	}

	@Override
	public void addAriticleComment(AriticleComment a) throws Exception {
		
		int ariticleId = a.getAriticleId();
		
		Ariticle ariticle = ariticleDao.getAriticleById(ariticleId);
		if(ariticle==null) {
			return;
		}
		
		ariticleCommonDao.addAriticleComment(a);
	}

	@Override
	public List<Map> getTop2AriticleCount() throws Exception {
		return ariticleDao.getTop2AriticleCount();
	}

}
