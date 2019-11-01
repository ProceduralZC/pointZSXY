package com.system.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SysConfig {
	
	@Value("${image.upload.maxsize}")
	private String imageUploadMaxsize;
	
	@Value("${image.upload.content_type}")
	private String imageUploadContentType;
	
	@Value("${file.upload.maxsize}")
	private String fileUploadMaxsize;
	
	@Value("${file.upload.content_type}")
	private String fileUploadContentType;
	
	@Value("${file.local.dir}")
	private String fileLocalDir;
	
	public String getImageUploadMaxsize() {
		return imageUploadMaxsize;
	}

	public void setImageUploadMaxsize(String imageUploadMaxsize) {
		this.imageUploadMaxsize = imageUploadMaxsize;
	}

	public String getImageUploadContentType() {
		return imageUploadContentType;
	}

	public void setImageUploadContentType(String imageUploadContentType) {
		this.imageUploadContentType = imageUploadContentType;
	}

	public String getFileUploadMaxsize() {
		return fileUploadMaxsize;
	}

	public void setFileUploadMaxsize(String fileUploadMaxsize) {
		this.fileUploadMaxsize = fileUploadMaxsize;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileLocalDir() {
		return fileLocalDir;
	}

	public void setFileLocalDir(String fileLocalDir) {
		this.fileLocalDir = fileLocalDir;
	}
}
