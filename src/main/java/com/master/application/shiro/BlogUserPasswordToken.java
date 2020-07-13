package com.master.application.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class BlogUserPasswordToken extends UsernamePasswordToken {

	private String passwd;

	public BlogUserPasswordToken(String username, String passwd) {
		super(username, passwd);
		this.passwd = passwd;
	}

	private static final long serialVersionUID = 1L;

	public String getPasswd() {
		return passwd;
	}

	@Override
	public Object getCredentials() {
		return getPasswd();
	}

}
