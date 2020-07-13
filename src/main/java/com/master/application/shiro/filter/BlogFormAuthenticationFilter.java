package com.master.application.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.master.application.bean.BlogUser;
import com.master.application.shiro.BlogUserPasswordToken;
import com.master.application.util.BlogJwtUtil;


public class BlogFormAuthenticationFilter extends FormAuthenticationFilter {
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		
		if(WebUtils.toHttp(request).getMethod().equals("OPTIONS")){
			return true;
		}
		
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		if (isLoginRequest(request, response)) {
			return true;
		}

		try {
			Subject currentUser = SecurityUtils.getSubject();

			if (!currentUser.isAuthenticated()) {
				String token = WebUtils.toHttp(request).getHeader("token");
				if (!StringUtils.isBlank(token)) {
					// 验证用户登陆
					BlogUser u = BlogJwtUtil.parseJwtToken(token);
					BlogUserPasswordToken kdapToken = new BlogUserPasswordToken(u.getUserCode(), u.getPassword());
					
					kdapToken.setRememberMe(false);
					currentUser.login(kdapToken);
				} else {
					WebUtils.toHttp(response).setStatus(401);
					return false;
				}

			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		WebUtils.toHttp(response).setStatus(401);
		return false;
	}

}
