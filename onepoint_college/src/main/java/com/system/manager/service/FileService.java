package com.system.manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService  extends IBaseService{

	/**
	 * 存储文件
	 * 
	 * @param file
	 *            已经上传的文件对象
	 * @return 文件存放的路径+文件名
	 */
	public String executeStoreFile(String directory, MultipartFile file);
	public String execute2xStoreFile(String directory, MultipartFile file);
	public void deleteFile(String directory);
	/**
	 * 上传的文件转化为普通文件对象,并返回文件的全路径
	 * 
	 * @param file
	 * @return
	 */
	public String multipartFile2File(String directory, MultipartFile file);
	
	public String executeApkStoreFile(String directory, MultipartFile file);

}
