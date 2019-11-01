package com.system.manager.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.system.core.web.base.BaseController;
import com.system.manager.entity.DefineData;
import com.system.manager.util.ResponseUtils;
@Controller 
public class AttachmentController extends BaseController{
	@RequestMapping("/si.do")
	public void showImage(String in, @RequestParam(defaultValue = "0") Integer w
			, @RequestParam(defaultValue = "0") Integer h, HttpServletResponse response
			, HttpServletRequest request, ModelMap model)
			throws Exception {
		String fileName = DefineData.FILE_LOCAL_DIR + in;
		File file = new File(fileName);
		if (!file.exists()) {
			ResponseUtils.renderText(response, "Image not exists!");
			return;
		}
		if (w > 0 && h > 0) {
			BufferedImage image = ImageIO.read(file);
			BufferedImage scaleImage = Scalr.resize(image, Scalr.Method.QUALITY
				, Mode.AUTOMATIC, w, h, Scalr.OP_ANTIALIAS);

			response.setContentType("image/jpeg");

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			ImageIO.write(scaleImage, "jpeg", response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} else {
			FileInputStream fis = new FileInputStream(file);
			ResponseUtils.renderImage(response, fis);
		}

	}
}
