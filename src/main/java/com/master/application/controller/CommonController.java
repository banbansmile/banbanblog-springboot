package com.master.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.master.application.bean.Ariticle;
import com.master.application.bean.BlogComment;
import com.master.application.bean.BlogUser;
import com.master.application.bean.Mod;
import com.master.application.bean.Tag;
import com.master.application.service.CommonService;
import com.master.application.util.BlogJwtUtil;
import com.master.application.util.Result;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	CommonService commonService;

	@GetMapping("/getAriticleList")
	public Result getAriticleList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "typeIds", required = false) String typeIds,
			@RequestParam(value = "tagIds", required = false) String tagIds) {
		try {
			Result r = commonService.getAriticleList(pageIndex, pageSize, typeIds, tagIds);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}

	@PostMapping("/ariticile/{id}")
	public Result getAriticleById(@PathVariable("id") int id,
			@RequestParam(value = "getNext", required = false, defaultValue = "false") Boolean getNext) {

		try {
			Map<String, Ariticle> m = commonService.getAriticleById(id, getNext);
			return new Result(Result.SUCCEEDED, "SUCCESS", m);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}

	@PostMapping("/ariticile/addZanCount/{id}")
	public Result addZanCount(@PathVariable("id") int id) {
		try {
			commonService.addZanCount(id);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}

	@GetMapping("/ariticleComment/getAriticleCommentList/{ariticleId}")
	public Result getAriticleCommentList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@PathVariable("ariticleId") int ariticleId) {

		try {
			Result r = commonService.getAriticleCommentList(pageIndex, pageSize, ariticleId);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}

	@GetMapping("/getModList")
	public Result getModList() {
		try {
			List<Mod> list = commonService.getModList();
			return new Result(Result.SUCCEEDED, "success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}

	@GetMapping("/getAllAriticleSimpleInfo")
	public Result getAllAriticleSimpleInfo() {
		try {
			List<Map<String, Object>> list = commonService.getAllAriticleSimpleInfo();
			return new Result(Result.SUCCEEDED, "success", list);

		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}

	@GetMapping("/ariticle/addLikeCount/{id}")
	public Result addLikeCount(@PathVariable("id") int id) {

		try {
			commonService.addLikeCount(id);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}

	}

	@GetMapping("/ariticle/addDisLikeCount/{id}")
	public Result addDisLikeCount(@PathVariable("id") int id) {
		try {
			commonService.addDisLikeCount(id);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}

	@GetMapping("/tag/getTagAriticleCountList")
	public Result getTagAriticleCountList() {
		try {
			List<Tag> list = commonService.getTagAriticleCountList();
			return new Result(Result.SUCCEEDED, "success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}

	@PostMapping("/user/sendEmail")
	public Result sendEmail(@RequestParam(value = "usercode", required = false) String usercode, String email) {

		try {
			commonService.sendEmail(usercode, email);
			return new Result(Result.SUCCEEDED, "发送邮件验证码成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}

	@PostMapping("/user/sendEmail1")
	public Result sendEmail1(String email) {

		try {
			commonService.sendEmail1(email);
			return new Result(Result.SUCCEEDED, "发送邮件验证码成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}

	@PostMapping("/user/userRegister")
	public Result userRegister(BlogUser user, @RequestParam(value = "authCode", required = true) String authCode) {
		try {
			commonService.userRegister(user, authCode);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage() + "");
		}
	}

	@PostMapping("/user/userLogin")
	public Result userLogin(@RequestParam(value = "account", required = true) String account,
			@RequestParam(value = "password", required = true) String password) {

		try {
			Map<String, Object> m = commonService.userLogin(account, password);
		

			return new Result(Result.SUCCEEDED, "success", m);
		} catch (UnknownAccountException e) {
			return new Result(Result.FAILED, "账号不存在");
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			return new Result(Result.FAILED, "密码错误");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage() + "");
		}
	}

	@PostMapping("/user/findBackPassword")
	public Result findBackPassword(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "authcode", required = true) String authcode) {

		try {
			commonService.findBackPassword(email, password, authcode);
			return new Result(Result.SUCCEEDED, "更改密码成功,请注意查收邮件");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage() + "");
		}
	}

	@PostMapping("/user/isUserLogin")
	public Result isUserLogin() {

		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			return new Result(Result.SUCCEEDED);
		} else {
			return new Result(Result.FAILED);
		}

	}

	@GetMapping("/blogcomment/getBlogComment")
	public Result getBlogComment(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

		try {
			PageInfo<BlogComment> pageInfo = commonService.getBlogComment(pageIndex, pageSize);
			return new Result(Result.SUCCEEDED, "success", pageInfo.getList(), pageInfo.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage() + "");
		}
	}

	@GetMapping("/blogcomment/addBlogCommentLike/{id}")
	public Result addBlogCommentLike(@PathVariable("id") int id) {
		try {
			commonService.addBlogCommentLike(id);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage() + "");
		}
	}

	@GetMapping("/blogcomment/addBlogCommentDisLike/{id}")
	public Result addBlogCommentDisLike(@PathVariable("id") int id) {

		try {
			commonService.addBlogCommentDisLike(id);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage() + "");
		}
	}

	@GetMapping("/blogcomment/getBlogCommentCount")
	public Result getBlogCommentCount() {
		try {
			Map<String, Integer> m = commonService.getBlogCommentCount();
			return new Result(Result.SUCCEEDED, "SUCCESS", m);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage() + "");
		}
	}

	@GetMapping("/ariticle/getTop2AriticleCount")
	public Result getTop2AriticleCount() {

		try {
			List<Map> list = commonService.getTop2AriticleCount();
			return new Result(Result.SUCCEEDED, "SUCCESS", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage() + "");
		}
	}

}
