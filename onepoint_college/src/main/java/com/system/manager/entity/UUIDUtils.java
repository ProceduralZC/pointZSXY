package com.system.manager.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 生成唯一码
 * 
 * @author arron.wu
 * 
 */
public class UUIDUtils {

	/**
	 * 生成UUID字符串
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 获得Token
	 * 
	 * @return
	 */
	public static String getToken() {
		String uuidString = UUID.randomUUID().toString().replaceAll("-", "");
		String dateString = DateUtil.format(new Date(), new SimpleDateFormat("yyyyMMdd"));
		return dateString + "-" + uuidString;
	}

	/**
	 * 返回服务器标识，比如商场的,一家商场一个标识，通过这个标识来区分服务器
	 * 
	 * @return
	 */
	public static String getServerSign() {
		String uuidString = UUID.randomUUID().toString().replaceAll("-", "");
		return uuidString;
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
	}

}
