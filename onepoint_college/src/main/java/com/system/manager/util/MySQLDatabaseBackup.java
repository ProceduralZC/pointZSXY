package com.system.manager.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.system.core.util.FileUtil;
/**
 * MySQL数据库备份
 */
public class MySQLDatabaseBackup {
	/**
	 * Java代码实现MySQL数据库导出
	 * 
	 * @param hostIP MySQL数据库所在服务器地址IP
	 * @param userName 进入数据库所需要的用户名
	 * @param password 进入数据库所需要的密码
	 * @param savePath 数据库导出文件保存路径
	 * @param fileName 数据库导出文件文件名
	 * @param databaseName 要导出的数据库名
	 * @return 返回true表示导出成功，否则返回false。
	 */
	public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException {
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {// 如果目录不存在
			saveFile.mkdirs();// 创建文件夹
		}
		if(!savePath.endsWith(File.separator)){
			savePath = savePath + File.separator;
		}
		InputStream is= null; 
		OutputStream os=null;
		try {
			// 备份语句  
	        String command = "cmd /c mysqldump -u" + userName + " -p" + password  
	                + " -h" + hostIP + " " + databaseName + " >" + fileName + " --lock-all-tables";  
	        System.out.println(command);  
			Process process = Runtime.getRuntime().exec(command);
			is=process.getInputStream();
			os=new FileOutputStream(savePath + fileName);
			FileUtil.createFile(is,os);
			process.waitFor();
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		try {
			if (exportDatabaseTool("127.0.0.1", "root", "root", "D:/backupDatabase", "2017-02-22.sql", "maxuser")) {
				System.out.println("数据库成功备份！！！");
			} else {
				System.out.println("数据库备份失败！！！");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}