package com.system.core.web;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.system.core.config.SysConfig;
import com.system.core.entity.SysAffix;
import com.system.core.service.SysAffixService;
import com.system.core.util.FileUtil;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value = "/system")
public class SysAffixController extends BaseController {
	
	@Autowired
	private SysAffixService affixService;
	@Autowired
	private SysConfig config;

	private Object lock= new Object();
	
	public static final List<String> ALLOW_TYPES = Arrays.asList(
            "image/jpg","image/jpeg","image/png","image/gif"
    );
	
	/**
	 * 保存附件
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/affix" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public String add(HttpServletRequest request, HttpServletResponse response) {
		Integer id = 0;
		long imageFileSize = Long.parseLong(config.getImageUploadMaxsize());//图片上传大小的限额
		long fileMaxSize = Long.parseLong(config.getFileUploadMaxsize());//文件上传大小的限额
		String fileContentType = config.getFileUploadContentType();// 上传文件允许的类型
		String imageContentType = config.getImageUploadContentType();// 上传图片允许的类型
		String msg = "";
		SysAffix sysAffix = new SysAffix();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Calendar c = Calendar.getInstance();
			MultipartFile multipartFile = multipartRequest.getFile("file");
			String objId = UUID.randomUUID().toString();
			String fileName = multipartFile.getOriginalFilename();
			String extendName = fileName.substring(fileName.lastIndexOf(".") + 1);
			String fileExt = extendName.toLowerCase();
			String uploadPath;
			if (isImageType(multipartFile.getContentType().toLowerCase())){//上传的图片
				uploadPath = "/images/" + c.get(1) + "/" + (c.get(2) + 1);
				if (!Arrays.<String> asList(imageContentType.split(",")).contains(fileExt)) {
					msg = "上传的文件格式只能为" + imageContentType + "格式";
					return super.message(id.toString(), msg, "error");
				}
				if (imageFileSize < multipartFile.getSize()) {
					msg = "上传的图片大小不能超过"+ new BigDecimal(imageFileSize).divide(new BigDecimal(1024 * 1024)) + "M!";
					return super.message(id.toString(), msg, "error");
				}
			}else {
				if (!Arrays.<String> asList(fileContentType.split(",")).contains(fileExt)) {
					msg = "上传的文件格式只能为" + fileContentType + "格式";
					return super.message(id.toString(), msg, "error");
				}
				if (fileMaxSize < multipartFile.getSize()) {
					msg = "上传的文件大小不能超过"+ new BigDecimal(fileMaxSize).divide(new BigDecimal(1024 * 1024)) + "M!";
					return super.message(id.toString(), msg, "error");
				}
				uploadPath = "/file/" + c.get(1) + "/" + (c.get(2) + 1);
			}
			
			String filePath = config.getFileLocalDir() + uploadPath;
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(filePath + "/" + objId + "."+ extendName));
			BufferedInputStream bin1 = new BufferedInputStream(multipartFile.getInputStream());
			FileUtil.createFile(bin1, bout);
			sysAffix.setSource(uploadPath + "/" + objId + "." + extendName);
			String fileSizeStr = FileUtil.getFileSizeStr(multipartFile.getSize());
			sysAffix.setSize(fileSizeStr);
			sysAffix.setCreateDate(new Date());
			sysAffix.setExtname(extendName.toLowerCase());
			sysAffix.setType(multipartFile.getContentType());
			sysAffix.setName(fileName);
			//针对处理图片
			if (multipartFile.getContentType().toLowerCase().startsWith("image")){//上传的图片
				File photoFile = new File(config.getFileLocalDir()+sysAffix.getSource());
				BufferedImage image = ImageIO.read(photoFile);
				Integer width = image.getWidth();//得到图片的宽度
				Integer height = image.getHeight();//得到图片的高度
				sysAffix.setHeight(height);
				sysAffix.setWidth(width);
			}
			this.affixService.add(sysAffix);
			id = sysAffix.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			return super.message(id.toString(), "上传失败!", "error");
		}
		return super.message(id.toString(), "上传成功!", "success");
	}

	
	private boolean isImageType(String contentType){
		if(ALLOW_TYPES.contains(contentType)){
			return true;
		}
		return false;
	}
	/**
	 * 下载附件
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/affix/{id}/download" }, method = {RequestMethod.GET })
	public void download(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SysAffix sysAffix = this.affixService.get(id);
			response.reset();
			response.addHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(sysAffix.getName(), "UTF-8"));
			response.setContentType("application/octet-stream");
			String filePath = config.getFileLocalDir()+sysAffix.getSource();
			OutputStream out = response.getOutputStream();
			BufferedInputStream bin = new BufferedInputStream(
					new FileInputStream(filePath));
			FileUtil.createFile(bin, out);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 显示图片
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/affix/{id}/showImage" }, method = {RequestMethod.GET })
	public void showImage(@PathVariable Integer id, 
			@RequestParam(defaultValue = "0",required=false) Integer width, @RequestParam(defaultValue = "0",required=false) Integer height,
			HttpServletRequest request,
			HttpServletResponse response) {
		synchronized(lock){
			try {
				SysAffix sysAffix = this.affixService.get(id);
				String filePath = config.getFileLocalDir()+sysAffix.getSource();
				BufferedInputStream bin = new BufferedInputStream(new FileInputStream(filePath));
				response.reset();
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.addHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(sysAffix.getName(), "UTF-8"));
				response.setContentType(sysAffix.getType());
				OutputStream out = response.getOutputStream();
				if (width > 0){
					BufferedImage image = ImageIO.read(bin);
					if(height == 0){
						int h = image.getHeight();
						int w = image.getWidth();
						height = width * (h / w);
					}
					BufferedImage scaleImage = Scalr.resize(image, Scalr.Method.QUALITY, Mode.AUTOMATIC, width, height, Scalr.OP_ANTIALIAS);
					ImageIO.write(scaleImage, "jpeg", out);
					bin.close();
					out.flush();
					out.close();
				}else{
					FileUtil.createFile(bin, out);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@RequestMapping(value = { "/affix/{id}/delFile" }, method = {RequestMethod.GET })
	public String delete(@PathVariable Integer id) {
		this.affixService.del(id);
		return super.message(id.toString(), "删除成功!", "success");
	}
	 @RequestMapping(value={"/getAffix"}, method=RequestMethod.GET)
	  public SysAffix get(@RequestParam Integer id, HttpServletResponse response){
		 SysAffix beans = this.affixService.get(id);
	     return beans;
	  }
}
