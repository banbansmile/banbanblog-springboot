package com.master.application.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.master.application.ApplicationAutoConfiguration;
import com.master.application.service.FileService;

import com.master.application.util.Utils;

@RestController
@RequestMapping("/file")
public class FileController {

	@Value("${server.port:80}")
	public int port;

	@Value("${domain.name}")
	public String domainName;

	@Autowired
	FileService fileService;

	@PostMapping(value = "/fileUpload")
	public Map<String, Object> fileUpload(@RequestParam(value = "files") MultipartFile file, Model model,
			HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();

		if (file.isEmpty()) {
			System.out.println("文件为空空");
		}

		String fileName = file.getOriginalFilename(); // 文件名
		String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 后缀名

		fileName = UUID.randomUUID() + suffixName; // 新文件名
		File dest = new File(ApplicationAutoConfiguration.filePath + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String prefix = Utils.getUrlPrefix(domainName, port);
		String filename = prefix + ApplicationAutoConfiguration.filePrefix + "/" + fileName;

		list.add(filename);

		map.put("errno", 0);
		map.put("data", list);

		return map;
	}

}
