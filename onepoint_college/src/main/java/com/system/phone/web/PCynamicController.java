package com.system.phone.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.jsonpacket.JsonResponse;
import com.system.manager.entity.Cynamic;
import com.system.manager.entity.DefineData;
import com.system.manager.service.CynamicService;
import com.system.manager.util.ResponseUtils;
import com.system.manager.web.model.CynamicModel;
import com.system.user.web.base.BaseController;
//首页动态
@RestController
@RequestMapping(value="/ms")
public class PCynamicController extends BaseController{
	
	@Autowired
	private CynamicService cynamicService;
	
	/**
	 * 查询动态
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryAllPhoneCynamic", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryAllPhoneCynamic(@ModelAttribute CynamicModel model
			,HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<Cynamic> items = new ArrayList<Cynamic>();
		try{
			List<Cynamic> list = cynamicService.findByPageType(model);
			
			items.addAll(list);
			jsonResponse.setItems(items);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonResponse;
	}
	
	/**
	 * 手机端加载图片
	 * @param in
	 * @param w
	 * @param h
	 * @param response
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/showimage")
	public void showImage(String in, @RequestParam(defaultValue = "0") 
	Integer w, @RequestParam(defaultValue = "0") Integer h, HttpServletResponse response
	, HttpServletRequest request, ModelMap model)
			throws Exception {
		if(in ==null || in.trim().length() == 0){
			return ;
		}
		try{
//			logger.info("showImage------------start------------------");
			String fileName = DefineData.FILE_LOCAL_DIR + in;
			File file = new File(fileName);
			if (!file.exists()) {
				ResponseUtils.renderText(response, "Image not exists!");
				return;
			}
			if (w > 0) {
				BufferedImage image = ImageIO.read(file);
				if(h == 0){
					int height = image.getHeight();
					int width = image.getWidth();
					h = w * (height / width);
				}
				
				BufferedImage scaleImage = Scalr.resize(image, Scalr.Method.QUALITY, Mode.AUTOMATIC, w, h, Scalr.OP_ANTIALIAS);
				response.setContentType("image/jpeg");
				
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				
				ImageIO.write(scaleImage, "jpeg", response.getOutputStream());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} else {
				FileInputStream fis = new FileInputStream(file);
//				logger.info("showImage------------end------------------");
				ResponseUtils.renderImage(response, fis);
			}
		}catch(Exception e){
//			logger.info("showImage------------Exception------------------");
			e.printStackTrace();
		}
	}
	
	
}
