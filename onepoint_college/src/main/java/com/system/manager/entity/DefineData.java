package com.system.manager.entity;

import java.util.ResourceBundle;

public class DefineData {

	private static ResourceBundle res = ResourceBundle.getBundle("com/system/manager/util/Appkey");
	//图片展现URL
	public static final String FILE_SHOW_URL = res.getString("file.show.url");
	//上传目录
	public static final String FILE_LOCAL_DIR = res.getString("file.local.dir");
	//退出跳转
	public static final String FILE_QUIT_URL = res.getString("file.quit.url");
}
