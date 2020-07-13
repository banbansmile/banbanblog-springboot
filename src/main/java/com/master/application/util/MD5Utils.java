package com.master.application.util;

import org.springframework.util.DigestUtils;

public class MD5Utils {

	public static String getMD5String(String encodeText) {
		return DigestUtils.md5DigestAsHex(encodeText.getBytes());
	}

	public static void main(String[] args) {
		System.out.println(getMD5String("123"));
	}

}
