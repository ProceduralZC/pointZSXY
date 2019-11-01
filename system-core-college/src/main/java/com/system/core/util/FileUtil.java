package com.system.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 *文件生成、复制帮助类
 */
public class FileUtil {
	
	public static int fileSize = 1024*1024;
	/**
	 * 文件拷贝
	 * @param srcFile 源文件
	 * @param targetFile 目标文件
	 * @return  是否拷贝成功
	 */
	public static boolean copyFile(File srcFile, File targetFile) {
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		boolean isCopied = false;
		if (srcFile.exists())
			try {
				bin = new BufferedInputStream(new FileInputStream(srcFile));
				byte[] bf = new byte[fileSize];
				bout = new BufferedOutputStream(
						new FileOutputStream(targetFile));
				int len = -1;
				while ((len = bin.read(bf)) != -1) {
					bout.write(bf, 0, len);
				}
				isCopied = true;
			} catch (FileNotFoundException fne) {
				System.out
						.println("原文件[" + srcFile.getName() + "]找不到。。。无法复制文件");
				fne.printStackTrace();
			} catch (IOException ioe) {
				System.out.println("原文件拷贝出错了...");
				ioe.printStackTrace();
			} finally {
				try {
					if (bin != null)
						bin.close();
					if (bout != null)
						bout.close();
				} catch (IOException e) {
				}
			}
		return isCopied;
	}

	/**
	 * 生成文件
	 * @param in  输入流
	 * @param out	输出流
	 * @throws IOException  文件异常
	 */
	public static void createFile(InputStream in, OutputStream out){
		try {
			byte[] bf = new byte[fileSize];
			int len = -1;
			while ((len = in.read(bf)) != -1){
				out.write(bf, 0, len);
			}
		} catch (IOException e) {
			
		} finally {
			try {
				if (in != null){
					in.close();
				}
				if (out != null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 转换文件大小为KB、MB格式
	 * @param fileSize 字节码文件大小
	 * @return  KB、MB格式
	 */
	public static String getFileSizeStr(long fileSize) {
		NumberFormat formater = DecimalFormat.getInstance();
		formater.setMaximumFractionDigits(2);
		formater.setMinimumFractionDigits(2);
		String fileSizeStr;
		if (fileSize < 1048576L)
			fileSizeStr = String.valueOf(formater.format(fileSize / 1024L))
					+ "KB";
		else {
			fileSizeStr = String.valueOf(formater.format(fileSize / 1048576L))
					+ "MB";
		}
		return fileSizeStr;
	}

	/**
	 * 生成文件
	 * @param filePath 文件路径
	 * @param content	文件内容
	 * @param charsetName 字符编码
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static void createFile(String filePath, String content,
			String charsetName) throws UnsupportedEncodingException,
			IOException {
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(filePath),
					charsetName);
			out.write(content);
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null)
				out.close();
		}
	}
	/**
	 * 获取文件字节数组
	 * @param in 输入流
	 * @return  字节数组
	 * @throws IOException
	 */
	public static byte[] getFileByte(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		createFile(in, out);
		return out.toByteArray();
	}

	/**
	 * 获取文件字节数组
	 * @param filePath 文件路径
	 * @return  private
	 * @throws IOException
	 */
	public static byte[] getFileByte(String filePath) throws IOException {
		return getFileByte(new FileInputStream(filePath));
	}
}