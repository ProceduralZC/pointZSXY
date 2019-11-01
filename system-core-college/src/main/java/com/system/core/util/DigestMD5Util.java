package com.system.core.util;

import java.security.MessageDigest;

public class DigestMD5Util {
	// MD5加码。32位
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			char[] charArray = inStr.toCharArray();
			byte[] byteArray = new byte[charArray.length];
			for (int i = 0; i < charArray.length; i++){
				byteArray[i] = (byte) charArray[i];
			}
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString().toUpperCase();
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	// 可逆的加密算法
	public static String EncryMd5(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 测试主函数
	public static void main(String args[]) {
		System.out.println("admin MD5后：" + MD5("123456"));
		System.out.println("admin MD5后在加密：" + EncryMd5("E10ADC3949BA59ABBE56E057F20F883E"));
	}


}
