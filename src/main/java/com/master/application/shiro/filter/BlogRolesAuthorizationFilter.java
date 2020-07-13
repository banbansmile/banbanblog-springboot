package com.master.application.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;


public class BlogRolesAuthorizationFilter extends RolesAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {

		return super.isAccessAllowed(request, response, mappedValue);

	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

		WebUtils.toHttp(response).setStatus(403);

		return false;
	}

}