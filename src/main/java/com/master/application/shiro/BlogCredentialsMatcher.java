package com.master.application.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import com.master.application.util.MD5Utils;



public class BlogCredentialsMatcher implements CredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken blogToken, AuthenticationInfo info) {

		BlogSimpleAuthenticationInfo kdapAuthInfo = (BlogSimpleAuthenticationInfo) info;

		// BlogUsernamePasswordToken passwd 
		String blogTokenCredentials = getCredentials(blogToken);
		String blogAuthInfoCredentials = getCredentials(kdapAuthInfo);

		return StringUtils.equals(blogTokenCredentials, blogAuthInfoCredentials);
	}

	protected String getCredentials(AuthenticationInfo info) {
		return (String) info.getCredentials();
	}

	protected String getCredentials(AuthenticationToken token) {

		BlogUserPasswordToken blogUserToken = (BlogUserPasswordToken) token;
		String passwd = blogUserToken.getPasswd();
		String md5password = MD5Utils.getMD5String(passwd);

		return md5password;

	}
}
