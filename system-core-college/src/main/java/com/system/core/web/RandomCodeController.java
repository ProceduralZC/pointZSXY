package com.system.core.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.util.ParamUtil;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value = "/system")
public class RandomCodeController extends BaseController {

	@RequestMapping(value = { "/getRandomCode" }, method = {RequestMethod.GET })
	public void getRandomCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);

		int width = ParamUtil.getIntParamter(request, "width", 60);
		int height = ParamUtil.getIntParamter(request, "height", 20);
		int fontSize = ParamUtil.getIntParamter(request, "fontSize", 18);
		
		BufferedImage image = new BufferedImage(width, height, 1);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Times New Roman", 0, fontSize));

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = ParamUtil.getStrParamter(request, "sRand", "");
		if ("".equals(sRand)) {
			for (int i = 0; i < 4; i++) {
				sRand = sRand + random.nextInt(10);
			}
		}
		for (int i = 0; i < sRand.length(); i++) {
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(sRand.substring(i, i + 1), 13 * i + 6, 16);
		}
		//保存session
		HttpSession session = request.getSession();
		session.setAttribute("randomCode", sRand);
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception e) {
		}finally{
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
