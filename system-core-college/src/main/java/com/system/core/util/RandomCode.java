package com.system.core.util;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RandomCode {
	/**
	 * 产生订单号（毫秒数+6位随机数）
	 * */
	public static String getOrdersCode(){
		return new Date().getTime()+createRandom(true, 2);
	}
	
	/**
	 * 创建指定数量的随机字符串
	 * @param flag
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean flag, int length) {
		String retStr = "";
		String strTable = flag ? "1234567890":"1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);
		return retStr;
	}
	
	/**
	 * 手机号验证
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if(mobile==null||"".equals(mobile.trim())){
			return false;
		}
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(mobile);
		b = m.matches(); 
		return b;
	}
	
	/**
	 * 邮箱验证
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if(email==null||"".equals(email.trim())){
			return false;
		}
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"); // 验证邮箱
		m = p.matcher(email);
		b = m.matches(); 
		return b;
	}
}