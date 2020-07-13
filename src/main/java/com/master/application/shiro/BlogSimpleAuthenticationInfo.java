package com.master.application.shiro;


import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

public class BlogSimpleAuthenticationInfo implements AuthenticationInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7386138292723815959L;
	protected PrincipalCollection principals;
	protected Object credentials;

	public BlogSimpleAuthenticationInfo(Object principal, Object kdapCredentials, String realmName) {
		this.principals = new SimplePrincipalCollection(principal, realmName);
		this.credentials = kdapCredentials;

	}

	@Override
	public PrincipalCollection getPrincipals() {
		return this.principals;
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

}
