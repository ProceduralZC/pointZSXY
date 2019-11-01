package com.system.manager.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpServletResponse帮助类
 * 
 */
public final class ResponseUtils {
	public static final Logger log = LoggerFactory.getLogger(ResponseUtils.class);

	/**
	 * 发送文本。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderText(HttpServletResponse response, String text) {
		render(response, "text/plain;charset=UTF-8", text);
	}

	/**
	 * 发送json。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderJson(HttpServletResponse response, String text) {
		// String start = System.nanoTime();
		// System.out.print(text);
		// render(response, "text/xml;charset=UTF-8", text);
		render(response, "text/plain;charset=UTF-8", text);
		// render(response, "application/json;charset=UTF-8", text);

		// System.out.println("=============================================================="
		// + (System.nanoTime() - start));
	}

	/**
	 * 发送xml。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderXml(HttpServletResponse response, String text) {
		render(response, "text/xml;charset=UTF-8", text);
	}

	/**
	 * 发送内容。使用UTF-8编码。
	 * 
	 * @param response
	 * @param contentType
	 * @param text
	 */
	public static void render(HttpServletResponse response, String contentType, String text) {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void renderImage(HttpServletResponse response, InputStream inputStream) throws IOException {
		response.setContentType("image/jpeg");

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		OutputStream outputStream = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, len);
		}
	}
	
	public static void downPhotoByStudentId(InputStream inputStream, final HttpServletResponse response) throws IOException{
		byte[] data = new byte[inputStream.available()];
		Integer size=data.length;
		inputStream.read(data);
		String fileName = "xinyuan.apk";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.addHeader("Content-Length", size.toString());
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
	}
	
}
