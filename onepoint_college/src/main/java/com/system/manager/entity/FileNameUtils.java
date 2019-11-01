package com.system.manager.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * 文件名生成类
 */
public class FileNameUtils {

	public static String separator = "-";

	private FileNameUtils() {

	}

	/** 类型:原图 */
	public static String FILE_NAME_TYPE_SRC = "src";

	/** 类型:原图切割成正方形 */
	public static String FILE_NAME_TYPE_SQUARE = "square";

	/**
	 * 通过文件名后缀判断文件名
	 * 
	 * @param ffName
	 * @return
	 */
	public static String getFileType(String fileName) {
		if (fileName.lastIndexOf(".") > 0) {
			return fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}
		return null;
	}

	/**
	 * 自动在文件名前加上日期的目录
	 * 
	 * @param oriFileName
	 * @return
	 */
	public static FileNameInfo getFtpFileName(String directory, String oriFileName, String sign) {
		if (oriFileName.lastIndexOf(".") == -1) {
			throw new RuntimeException(oriFileName + "的文件类型未知!");
		}
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
		String uuidString = UUIDUtils.getUUID();
		String dateString = DateUtil.format(new Date(), new SimpleDateFormat("yyyyMMdd"));

		FileNameInfo info = new FileNameInfo();
		info.setPath(SystemConfig.FTP_PATH + "/" + directory);
		info.setOriName(dateString + "-" + uuidString);
		info.setSign(sign);
		info.setSuffix(suffix);

		return info;
	}

	public static String getFileName(String oriFileName) {
		if (oriFileName.lastIndexOf(".") == -1) {
			throw new RuntimeException(oriFileName + "的文件类型未知!");
		}

		String uuidString = UUIDUtils.getUUID();
		String dateString = DateUtil.format(new Date(), new SimpleDateFormat("yyyyMMdd"));
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());

		return dateString + "-" + uuidString + suffix;
	}

	public static void main(String[] args) {
		System.out.println(FileNameUtils.getFtpFileName("ask", "xxxxx.jpg", "vs").getFullPathName());
		System.out.println(FileNameUtils.getFileName("ask", "123.png", "vs").getFullPathName());
	}

	/**
	 * 随机产生一个图片文件名
	 * 
	 * @return
	 */
	public static String getRandomImageFileName(String directory, String sign) {
		String uuidString = UUIDUtils.getUUID();
		String dateString = DateUtil.format(new Date(), new SimpleDateFormat("yyyyMMdd"));

		return SystemConfig.FTP_PATH + "/" + directory + "/" + dateString + "-" + uuidString + separator + sign + ".jpg";
	}
	/**
	 * 自动在文件名前加上日期的目录
	 * 
	 * @param oriFileName
	 * @return
	 */
	public static FileNameInfo getFileName(String directory, String oriFileName, String sign) {
		if (StrUtils.isNULL(directory)) {
			directory = "";
		}

		if (StrUtils.isNULL(sign)) {
			sign = "";
		}
		if (oriFileName.lastIndexOf(".") == -1) {
			throw new RuntimeException(oriFileName + "的文件类型未知!");
		}
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());

		Random random = new Random();
		int randomInt = random.nextInt(100);
		String uuidString = System.currentTimeMillis() + separator + randomInt;

		FileNameInfo info = new FileNameInfo();
		info.setPath(directory);
		info.setOriName(uuidString);
		info.setSign(sign);
		info.setSuffix(suffix);

		return info;
	}
	
	/**
	 * 自动在文件名前加上日期的目录
	 * 
	 * @param oriFileName
	 * @return
	 */
	public static FileNameInfo get2xFileName(String directory, String oriFileName, String sign) {
		if (StrUtils.isNULL(directory)) {
			directory = "";
		}
		
		if (StrUtils.isNULL(sign)) {
			sign = "";
		}
		if (oriFileName.lastIndexOf(".") == -1) {
			throw new RuntimeException(oriFileName + "的文件类型未知!");
		}
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
		
		Random random = new Random();
		int randomInt = random.nextInt(100);
		String uuidString = System.currentTimeMillis() + separator + randomInt;
		
		FileNameInfo info = new FileNameInfo();
		info.setPath(directory);
		info.setOriName(uuidString);
		info.setSign("@2x");
		info.setSuffix(suffix);
		
		return info;
	}
}
