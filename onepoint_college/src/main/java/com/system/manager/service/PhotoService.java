package com.system.manager.service;

import java.util.List;

import com.system.manager.entity.Photo;

public interface PhotoService extends IBaseService{

	public List<Photo> queryPhotoById(String fkid);
	public void executeSavePhoto(Photo photo);
	public void executeDeletePhoto(String pid);
	
}
