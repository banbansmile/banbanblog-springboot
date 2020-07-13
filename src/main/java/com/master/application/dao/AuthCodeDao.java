package com.master.application.dao;

import org.apache.ibatis.annotations.Param;

import com.master.application.bean.AuthCode;

public interface AuthCodeDao {
	
	public void saveAuthCode(AuthCode a);
	
	public AuthCode getAuthCodeByEmail(@Param("email") String email);
	
	public void deleteAuthCodeByEmail(@Param("email") String email);
}
