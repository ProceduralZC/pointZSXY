package com.system.manager.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
/**
 * 字符串的帮助类，提供静态方法，不可以实例化。
 */
public class StrUtils {
	/**
	 * 禁止实例化
	 */
	private StrUtils() {
	}

	/**
	 * 判断字符串是否为邮件地址
	 * 
	 * @param text
	 *            字符串
	 * @return
	 */
	public static boolean isMail(String text) {
		// 若为空，返回false
		if (isNULL(text)) {
			return false;
		}
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(text);
		return m.matches();
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param text
	 *            字符串
	 * @return
	 */
	public static boolean isNULL(String text) {
		if (text == null || text.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param text
	 *            字符串
	 * @return
	 */
	public static boolean isNotNULL(String text) {
		return !isNULL(text);
	}

	/**
	 * 处理url
	 * 
	 * url为null返回null，url为空串或以http://或https://开头，则加上http://，其他情况返回原参数。
	 * 
	 * @param url
	 * @return
	 */
	public static String handelUrl(String url) {
		if (url == null) {
			return null;
		}
		url = url.trim();
		if (url.equals("") || url.startsWith("http://") || url.startsWith("https://")) {
			return url;
		} else {
			return "http://" + url.trim();
		}
	}
	/**
	 * 去除文字中Html标签,为手机服务
	 * 
	 * @param text
	 *            文本
	 * @return 去除掉
	 */
	public static String removeHtml(String text) {
		if (isNULL(text)) {
			return "";
		}
		return text.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "").replaceAll("&nbsp;", "").replaceAll("\r", "").replaceAll("\n", "");
	}
	/**
	 * 如果为null 返回 ""
	 * 如果如果不为null则去除两端空格 
	 * @param obj
	 * @return
	 * 作者：程明    2014-01-07
	 */
	public static String obj2str(Object obj){
		if(null==obj||"null".equals(obj)){
			return "";
		}
		return obj.toString().trim();
	}
	
	public static String double2str(double dou){
		String re="";
		String astr=dou+"";
		String[] arr=astr.split("\\.");
		if(arr[1].length()==1){
			re=arr[0]+"."+arr[1]+"0";
			return obj2str(re);
		}
		return obj2str(dou+"");
	}
	
}
