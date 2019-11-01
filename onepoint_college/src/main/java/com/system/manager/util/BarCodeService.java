package com.system.manager.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.core.config.SysConfig;
import com.system.core.entity.SysAffix;
import com.system.core.service.SysAffixService;

@Service
public class BarCodeService {
	
	@Autowired
	private SysConfig sysConfig;
	@Autowired
	private SysAffixService affixService;
	
	@Transactional
	public Integer createBarCode(String barCodeStr){
		SysAffix sysAffix = new SysAffix();
		try {
//			String uploadPath = "/barcode";
			String uploadPath = "";
			String filePath = sysConfig.getFileLocalDir() + uploadPath;
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			//生成条形码图片
//			BarcodeUtil.generateFile(barCodeStr, filePath+"/"+barCodeStr+".png");
//			BarcodeUtil.generateFile(barCodeStr, filePath+"/"+barCodeStr);
//			sysAffix.setSource(uploadPath+"/"+barCodeStr + ".png");
			sysAffix.setSource(uploadPath+"/"+barCodeStr);
			sysAffix.setSize("1000kB");
			sysAffix.setCreateDate(new Date());
			sysAffix.setExtname("png");
			sysAffix.setType("image/png");
//			sysAffix.setName(barCodeStr+".png");
			sysAffix.setName(barCodeStr);
			File photoFile = new File(sysConfig.getFileLocalDir()+sysAffix.getSource());
			BufferedImage image = ImageIO.read(photoFile);
			Integer width = image.getWidth();//得到图片的宽度
			Integer height = image.getHeight();//得到图片的高度
			sysAffix.setHeight(height);
			sysAffix.setWidth(width);
			affixService.add(sysAffix);
			return sysAffix.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	private  boolean saveUrlAs(String photoUrl, String fileName) {  
        try {  
              URL url = new URL(photoUrl);  
              HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
              DataInputStream in = new DataInputStream(connection.getInputStream());  
              DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));  
              byte[] buffer = new byte[4096];  
              int count = 0;  
              while ((count = in.read(buffer)) > 0) {  
                out.write(buffer, 0, count);  
              }  
              out.close();  
              in.close();  
              return true;  
        }  
        catch (Exception e) {  
          return false;  
        }  
    }*/
}
