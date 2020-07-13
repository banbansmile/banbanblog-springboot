package com.master.application.globalexception;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.master.application.util.Result;


@ControllerAdvice(basePackages = "com.master")
public class AppGlobalExceptionHandler {

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public void resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		response.setStatus(403);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result errorResult(Exception e) {	
		e.printStackTrace();
		return new Result(-1, e.getMessage(), "出错了");
	}

}
