package com.system.manager.entity;

public class SystemConfig {

	public static String IOS_URL = "https://itunes.apple.com/cn/app/id781233546?mt=8";

	public static String ANDROID_URL = "http://hualin.fantasee.cn/mserver/hualin/hualin-android.apk";

	// FTP超时时间
	public static long FTP_TIMEOUT = 1000L;

	// FTP用户名
	public static String FTP_USERNAME = "static_resource";

	// FTP密码
	public static String FTP_PASSWORD = "ftp_rwx_@beta13";

	// FTP字符集
	public static String FTP_ENCODING = "UTF-8";

	// FTP路径
	public static String FTP_PATH = "hualin";

	// FTP 图片访问链接
	public static String FTP_IMAGE_URL = "notuse";

	// FTP IP
	public static String FTP_IP = "localhost";

	// FTP PORT
	public static int FTP_PORT = 21;

	// FTP临时目录
	public static String FTP_TMP_DIR = "/tmp/betatown_temp_dir/";

	// 手机服务器URL
	public static String MOBILE_SERVER_URL = "http://hualin.fantasee.cn/mserver/";

	// 默认个人图像
	public static String DEFAULT_PROFILEIMAGEURL = "member/20111104/fe9d9574fb3218ba1f38.jpg";

	// 将推送日志放到和ftp临时文件相同的目录
	public static String PUSH_LOG_FILE_DIR = FTP_TMP_DIR;

	public static void setDEFAULT_PROFILEIMAGEURL(String dEFAULT_PROFILEIMAGEURL) {
		DEFAULT_PROFILEIMAGEURL = dEFAULT_PROFILEIMAGEURL;
	}

	public static void setMOBILE_SERVER_URL(String mOBILE_SERVER_URL) {
		MOBILE_SERVER_URL = mOBILE_SERVER_URL;
	}

	public static void setFTP_TIMEOUT(Long fTP_TIMEOUT) {
		FTP_TIMEOUT = fTP_TIMEOUT;
	}

	public static void setFTP_USERNAME(String fTP_USERNAME) {
		FTP_USERNAME = fTP_USERNAME;
	}

	public static void setFTP_PASSWORD(String fTP_PASSWORD) {
		FTP_PASSWORD = fTP_PASSWORD;
	}

	public static void setFTP_ENCODING(String fTP_ENCODING) {
		FTP_ENCODING = fTP_ENCODING;
	}

	public static void setFTP_PATH(String fTP_PATH) {
		FTP_PATH = fTP_PATH;
	}

	public static void setFTP_IMAGE_URL(String fTP_IMAGE_URL) {
		FTP_IMAGE_URL = fTP_IMAGE_URL;
	}

	public static void setFTP_IP(String fTP_IP) {
		FTP_IP = fTP_IP;
	}

	public static void setFTP_PORT(int fTP_PORT) {
		FTP_PORT = fTP_PORT;
	}

	public static void setFTP_TMP_DIR(String fTP_TMP_DIR) {
		FTP_TMP_DIR = fTP_TMP_DIR;
	}

	public static String getPUSH_LOG_FILE_DIR() {
		return PUSH_LOG_FILE_DIR;
	}

	public static void setPUSH_LOG_FILE_DIR(String pUSH_LOG_FILE_DIR) {
		PUSH_LOG_FILE_DIR = pUSH_LOG_FILE_DIR;
	}

}
