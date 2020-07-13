package com.master.application.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.master.application.bean.Ariticle;
import com.master.application.bean.AriticleType;
import com.master.application.bean.BlogUser;
import com.master.application.bean.Mod;
import com.master.application.bean.Tag;
import com.master.application.service.ManagerService;
import com.master.application.util.Result;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerService managerService;

	@GetMapping("/getAriticleTypeList")
	public Result getAriticleTypeList() {

		try {
			List<AriticleType> list = managerService.getAriticleTypeList();
			return new Result(Result.SUCCEEDED, "SUCCESS", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}

	@GetMapping("/getTagList")
	public Result getTagList() {

		try {
			List<Tag> list = managerService.getTagList();
			return new Result(Result.SUCCEEDED, "SUCCESS", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}

	@PostMapping("/saveAriticle")
	public Result saveAriticle(Ariticle a, String tag_ids) {

		try {
			managerService.saveAriticle(a, tag_ids);
			Subject currentUser = SecurityUtils.getSubject();
			BlogUser u = (BlogUser)currentUser.getPrincipal();
			a.setUserId(u.getId());
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}

	}

	@GetMapping("/getAriticleList")
	public Result getAriticleList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "typeIds", required = false) String typeIds) {
		try {
			PageInfo<Ariticle> pageInfo = managerService.getAriticleList(pageIndex, pageSize,typeIds);
			return new Result(Result.SUCCEEDED, "SUCCESS", pageInfo.getList(), pageInfo.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED, e.getMessage());
		}
	}
	
	
	@DeleteMapping("/ariticle/{id}")
	public Result deleteAritile(@PathVariable("id") Integer id) {
		try {
			managerService.deleteAritile(id);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}
	
	
	@PostMapping("/addMod")
	public Result addMod(Mod m) {
		try {
			managerService.addMod(m);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}
	
	@GetMapping("/getModList")
	public Result getModList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "1000") int pageSize) {
		
		try {
			PageInfo<Mod> pageInfo = managerService.getModList(pageIndex,pageSize);
			return new Result(Result.SUCCEEDED, "SUCCESS", pageInfo.getList(), pageInfo.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
	}
	
	@DeleteMapping("/mod/{id}")
	public Result deleteMod(@PathVariable int id) {
		try {
			managerService.deleteMod(id);
			return new Result(Result.SUCCEEDED);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.FAILED);
		}
		
	}

}
