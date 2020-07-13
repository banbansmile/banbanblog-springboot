package com.master.application.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.master.application.bean.AriticleComment;
import com.master.application.bean.BlogComment;
import com.master.application.bean.BlogUser;
import com.master.application.service.CommonService;
import com.master.application.util.Result;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	CommonService commonService;

	@PostMapping("/updateUserName")
	public Result updateUserInfo(@RequestParam(value = "usercode", required = true) String usercode,
			@RequestParam(value = "username", required = true) String newusername) {

		try {
			commonService.updateUserName(usercode, newusername);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}

	@PostMapping("/updatePassword")
	public Result updatePassword(@RequestParam(value = "usercode", required = true) String usercode,
			@RequestParam(value = "oldpassword", required = true) String oldpassword,
			@RequestParam(value = "newpassword", required = true) String newpassword) {

		try {
			commonService.updatePassword(usercode, oldpassword, newpassword);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}

	@PostMapping("/updateEmail")
	public Result updateEmail(@RequestParam(value = "usercode", required = true) String usercode,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "authcode", required = true) String authcode) {

		try {
			commonService.updateEmail(usercode, email, authcode);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}

	}

	@PostMapping("/getUserInfoByUserCode")
	public Result getUserInfoByUserCode(@RequestParam(value = "usercode", required = true) String usercode) {

		try {
			BlogUser u = commonService.getBlogUserUserCode(usercode);
			return new Result(Result.SUCCEEDED, "success", u);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}

	@PostMapping("/updateImage")
	public Result updateImage(@RequestParam(value = "usercode", required = true) String usercode,
			@RequestParam(value = "image", required = true) String image) {

		try {
			commonService.updateImage(usercode, image);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}

	@PostMapping("/blogcomment/addBlogComment")
	public Result addBlogComment(BlogComment b) {

		Subject currentUser = SecurityUtils.getSubject();
		BlogUser u = (BlogUser)currentUser.getPrincipal();
		b.setUserId(u.getId());
		try {
			commonService.addBlogComment(b);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage()+"");
		}
	}
	
	
	@PostMapping("/ariticle/addAriticleComment")
	public Result addAriticleComment(AriticleComment a) {
		
		Subject currentUser = SecurityUtils.getSubject();
		BlogUser u = (BlogUser)currentUser.getPrincipal();
		a.setUserId(u.getId());
		try {
			commonService.addAriticleComment(a);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage()+"");
		}
	}
}
