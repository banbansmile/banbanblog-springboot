package com.master.application.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;

public class Utils {

	public static String getdefaulImage(int port) {
		return getUrlPrefix(port) + "images/default-image.jpg";
	}
	
	public static String getdefaulImage(String domain,int port) {
		
		return getUrlPrefix(domain,port) + "images/default-image.jpg";
	}

	public static String getUrlPrefix(int port) {
		if (port == 80) {
			return "http://" + getLocalIp() + "/";
		} else {
			return "http://" + getLocalIp() + ":" + port + "/";
		}

	}

	public static String getUrlPrefix(String domain, int port) {

		if (StringUtils.isBlank(domain)) {
			return getUrlPrefix(port);
		}

		if (port == 80) {
			return "http://" + domain + "/";
		} else {
			return "http://" + domain + ":" + port + "/";
		}

	}

	public static String getLocalIp() {

		String ip = "127.0.0.1";
		InetAddress inet = null;
		try {
			inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return ip;
	}

	public static void main(String[] args) {

	}

}
