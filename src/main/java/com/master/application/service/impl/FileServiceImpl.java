package com.master.application.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.master.application.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String getConfig() throws Exception {

		String configContent = this.readFile("config.json");

		try {

			JSONObject config = JSON.parseObject(configContent);
			return config.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private String readFile(String path) throws IOException {

		StringBuilder builder = new StringBuilder();

		try {
			InputStream s = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(path);
			InputStreamReader reader = new InputStreamReader(s, "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			bfReader.close();

		} catch (UnsupportedEncodingException e) {
			// 忽略
		}

		return this.filter(builder.toString());

	}

	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter(String input) {

		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

	}

}
