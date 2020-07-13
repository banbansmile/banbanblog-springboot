package com.master.application.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.master.application.bean.BlogUser;
import com.master.application.dao.BlogUserDao;


public class BlogShiroRealm extends AuthorizingRealm {

	@Autowired
	private BlogUserDao dao;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		BlogUserPasswordToken blogUserToken = (BlogUserPasswordToken) token;

		String userCode = blogUserToken.getUsername();// user code
		

		BlogUser user = dao.getUserByCode(userCode);
		if (user == null) {
			throw new UnknownAccountException("账号不存在");
		}

		AuthenticationInfo kdapSimpleAuthenticationInfo = new BlogSimpleAuthenticationInfo(user,user.getPassword(),
				this.getName());

		return kdapSimpleAuthenticationInfo;
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

		//simpleAuthorInfo.addStringPermission("admin");
		//simpleAuthorInfo.addRole("admin");

		return simpleAuthorInfo;

	}


}
