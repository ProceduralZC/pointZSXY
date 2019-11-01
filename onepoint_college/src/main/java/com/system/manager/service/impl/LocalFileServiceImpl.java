package com.system.manager.service.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.system.core.config.SysConfig;
import com.system.core.util.UUIDUtils;
import com.system.manager.dao.IAttachmentDAO;
import com.system.manager.entity.Attachment;
import com.system.manager.entity.DateUtil;
import com.system.manager.entity.FileNameInfo;
import com.system.manager.entity.FileNameUtils;
import com.system.manager.entity.ImageHelper;
import com.system.manager.service.FileService;
@Service
public class LocalFileServiceImpl extends BaseServiceImpl implements FileService {

	@Autowired
	private IAttachmentDAO iAttachmentDAO;
	@Autowired
	private SysConfig sysConfig;

	@Override
	public String executeStoreFile(String directory, MultipartFile file) {
		// 创建根目录
//		File rootDir = new File(DefineData.FILE_LOCAL_DIR+directory);
		File rootDir = new File(sysConfig.getFileLocalDir()+"/"+directory);
		if (!rootDir.exists()) {
			rootDir.mkdirs();
		}
		FileNameInfo fileNameInfo = FileNameUtils.getFileName(directory, file.getOriginalFilename(), null);
		String fullPathName = fileNameInfo.getFullPathName();

//		File oriFile = new File(DefineData.FILE_LOCAL_DIR + fullPathName);
		File oriFile = new File(sysConfig.getFileLocalDir() +"/"+ fullPathName);
		
		if(!oriFile.exists()){
		    //先得到文件的上级目录，并创建上级目录，在创建文件
			oriFile.getParentFile().mkdir();
		    try {
		        //创建文件
		    	oriFile.createNewFile();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
		try {
			file.transferTo(oriFile);
			fullPathName = ImageHelper.compressPic(sysConfig.getFileLocalDir()+"/"+fullPathName);
			if(!"".equals(fullPathName))
				fullPathName = fullPathName.substring(fullPathName.indexOf(sysConfig.getFileLocalDir()) + 
						sysConfig.getFileLocalDir().length(), fullPathName.length());
			
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return fullPathName;
	}

	
	@Override
	public String execute2xStoreFile(String directory, MultipartFile file) {
		// 创建根目录
//		File rootDir = new File(DefineData.FILE_LOCAL_DIR+directory);
		File rootDir = new File(sysConfig.getFileLocalDir()+"/"+directory);
		if (!rootDir.exists()) {
			rootDir.mkdirs();
		}
		FileNameInfo fileNameInfo = FileNameUtils.get2xFileName(directory, file.getOriginalFilename(), null);
		String fullPathName = fileNameInfo.getFullPathName();
		
//		File oriFile = new File(DefineData.FILE_LOCAL_DIR + fullPathName);
		File oriFile = new File(sysConfig.getFileLocalDir()+"/"+fullPathName);
		try {
			file.transferTo(oriFile);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		Attachment attachment = new Attachment();
		attachment.setId(UUIDUtils.getUUID());
		attachment.setFileSize(file.getSize());
		attachment.setFileUrl(fullPathName);
		attachment.setTimeAt(DateUtil.getCurrentDate());
		attachment.setFileType(FileNameUtils.getFileType(fullPathName));
		iAttachmentDAO.save(attachment);
		return fullPathName;
	}
	
	public String executeApkStoreFile(String directory, MultipartFile file) {
		// 创建根目录
//		File rootDir = new File(DefineData.FILE_LOCAL_DIR+directory);
		File rootDir = new File(sysConfig.getFileLocalDir()+"/"+directory);
		if (!rootDir.exists()) {
			rootDir.mkdirs();
		}
		FileNameInfo fileNameInfo = FileNameUtils.getFileName(directory, file.getOriginalFilename(), null);
		String fullPathName = fileNameInfo.getFullPathName();
		
//		File oriFile = new File(DefineData.FILE_LOCAL_DIR + fullPathName);
		File oriFile = new File(sysConfig.getFileLocalDir()+"/"+fullPathName);
		try {
			file.transferTo(oriFile);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		Attachment attachment = new Attachment();
		attachment.setId(UUIDUtils.getUUID());
		attachment.setFileSize(file.getSize());
		attachment.setFileUrl(fullPathName);
		attachment.setTimeAt(DateUtil.getCurrentDate());
		attachment.setFileType(FileNameUtils.getFileType(fullPathName));
		iAttachmentDAO.save(attachment);
		return fullPathName;
	}

	@Override
	public String multipartFile2File(String directory,MultipartFile file) {
		// 创建根目录
//		File rootDir = new File(DefineData.FILE_LOCAL_DIR);
		File rootDir = new File(sysConfig.getFileLocalDir());
		if (!rootDir.exists()) {
			rootDir.mkdirs();
		}

		FileNameInfo fileNameInfo = FileNameUtils.getFileName("common", file.getOriginalFilename(), null);
//		String fileName = DefineData.FILE_LOCAL_DIR + fileNameInfo.getOriName();
		String fileName = sysConfig.getFileLocalDir() + fileNameInfo.getOriName();

		File oriFile = new File(fileName);
		try {
			file.transferTo(oriFile);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return fileName;
	}

	@Override
	public void deleteFile(String filePath) {
//		String fileName = DefineData.FILE_LOCAL_DIR + filePath;
		String fileName = sysConfig.getFileLocalDir()+"/"+filePath;
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}
	
	public void compressPicFile(String filePath){
		
	}

}
