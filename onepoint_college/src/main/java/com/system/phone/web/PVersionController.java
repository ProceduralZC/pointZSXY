package com.system.phone.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.config.SysConfig;
import com.system.core.jsonpacket.JsonResponse;
import com.system.core.util.DateUtils;
import com.system.core.util.FileUtil;
import com.system.manager.entity.SysVersion;
import com.system.manager.service.SysVersionService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/ms")
public class PVersionController  extends BaseController{

	@Autowired
	private SysVersionService versionService;
	@Autowired
	private SysConfig config;
	
	@RequestMapping(value="/versionUpdate",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse versionUpdate(HttpServletRequest request,HttpServletResponse response){
		JsonResponse jsonResponse = super.sucessResponse();
		List<SysVersion> items = new ArrayList<SysVersion>();
		try{
			SysVersion version=versionService.findSysVersion();
			if(version!=null){
				items.add(version);
			}else{
				jsonResponse.setMsg("暂无新版本!");
			}
			jsonResponse.setItems(items);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = { "/downloadApk" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void download(@RequestParam String fileUrl,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String filePath = config.getFileLocalDir()+fileUrl;
			File file = new File(filePath);
			if(!file.exists()){
				response.getWriter().write("File not Found!");
				return;
			}
			FileInputStream inputStream =new FileInputStream(file);
			byte[] data = new byte[inputStream.available()];
			Integer size=data.length;
			
			String fileName = DateUtils.getNowDateTimeAsNumber()+".apk";
			response.reset();
			response.addHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(fileName, "UTF-8"));
			response.addHeader("Content-Length", size.toString());
			response.setContentType("application/octet-stream");
			OutputStream out = response.getOutputStream();
			BufferedInputStream bin = new BufferedInputStream(inputStream);
			FileUtil.createFile(bin, out);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
