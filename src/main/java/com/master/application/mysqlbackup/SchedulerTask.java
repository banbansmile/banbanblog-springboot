package com.master.application.mysqlbackup;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SchedulerTask {

	public final String userDir = System.getProperty("user.dir");
	public final String filePath = userDir + File.separator + "mysqlbackup";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Value("${dbName:banban1}")
	private String dbName;

	@Scheduled(cron = "0 0 2 * * *")
	private void process() {

		File backupFile = new File(filePath + File.separator + sdf.format(new Date()) + ".sql");

		if (!backupFile.getParentFile().exists()) {
			backupFile.getParentFile().mkdirs();
		}

		try {
			Process process = Runtime.getRuntime()
					.exec("cmd  /c  mysqldump -u root -P 4432 -proot " + dbName + " > " + backupFile);
			System.out.println("BackMySQLSuccess at " + sdf.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
