package com.master.application.util;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {

	public static String encodeText(String text) throws Exception {
		final Base64.Encoder encoder = Base64.getEncoder();
		final byte[] textByte = text.getBytes("UTF-8");
		final String encodedText = encoder.encodeToString(textByte);
		return encodedText;
	}
	
	public static String decodeText(String text) {
		final Base64.Decoder decoder = Base64.getDecoder();
		String decodeText="";
		try {
			decodeText = new String(decoder.decode(text), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}catch(IllegalArgumentException e) {
			
		}
		return decodeText;
	}
	
	public static void main(String[] args) throws Exception {
		
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		final String text = "123";
		final byte[] textByte = text.getBytes("UTF-8");
		//编码
		final String encodedText = encoder.encodeToString(textByte);
		System.out.println(encodedText);
		//解码
		System.out.println(new String(decoder.decode("YWFhYWE="), "UTF-8"));

		
	}
}
