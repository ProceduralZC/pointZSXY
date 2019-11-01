package com.system.manager.entity;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.commons.lang3.StringUtils;
public class ImageHelper {
	public static final Float COM_QUALITY = 0.3F;
	public static final Long FILE_SIZE = 300 * 1024L;
	public static final Long BIGGER_FILE_SIZE = 1000 * 1024L;
	private static final String SUFFIX = "_COM";
	/**
	 * 压缩图片，保持图片尺寸不变，原图小于300KB则不进行压缩。
	 * </br>
	 * 本方法会删除原图，压缩率为0.3。
	 * 
	 * @param srcFilePath 原图路径
	 */
	public static String compressPic(String srcFilePath) {
		return compressPic(srcFilePath, true, COM_QUALITY);
	}
	
	/**
	 * 压缩图片，保持图片尺寸不变，原图小于300KB则不进行压缩。
	 * 
	 * @param srcFilePath 原图路径
	 * @param deleteInputFile 是否删除原文件
	 * @param quality 图片压缩率
	 * @return 压缩后文件路径
	 */
	@SuppressWarnings("static-access")
	public static String compressPic(String srcFilePath, boolean deleteInputFile, float quality) {
		if (StringUtils.isBlank(srcFilePath))
			return "";
		File file = null;
		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;
		
		try {
			String descFilePath = srcFilePath.substring(0, srcFilePath.lastIndexOf(".")) + SUFFIX + ".jpg";
			file = new File(srcFilePath);
			if(file.length() > FILE_SIZE){
				// String suffix = srcFilePath.substring(srcFilePath.lastIndexOf(".") + 1);
				// 指定写图片的方式为 jpg
				imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
				imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
				// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
				imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
				// 这里指定压缩的程度，参数qality是取值0~1范围内
				if(file.length() >= BIGGER_FILE_SIZE)
					quality = 0.1F;
				imgWriteParams.setCompressionQuality(quality);
				imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
				ColorModel colorModel = ColorModel.getRGBdefault();
				// 指定压缩时使用的色彩模式
				imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
						colorModel, colorModel.createCompatibleSampleModel(16, 16)));
				
				src = ImageIO.read(file);
				out = new FileOutputStream(descFilePath);

				imgWrier.reset();
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
				out.flush();
				out.close();
				
				if(deleteInputFile){
					file.delete();
					File outFile = new File(descFilePath);
					outFile.renameTo(file);
					return srcFilePath;
				}
				return descFilePath;
			}else
				return srcFilePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}