package com.system.manager.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.manager.dao.PhotoDao;
import com.system.manager.entity.Photo;
import com.system.manager.service.PhotoService;
@Service
public class PhotoServiceImpl extends BaseServiceImpl implements PhotoService{


	@Autowired
	private PhotoDao photoDao;

	@Override
	public void executeSavePhoto(Photo photo) {
		photoDao.savePhoto(photo);
	}

	@Override
	public void executeDeletePhoto(String pid) {
		photoDao.deletePhoto(pid);
	}

	@Override
	public List<Photo> queryPhotoById(String fkid) {
		return photoDao.queryPhotoListByFkId(fkid);
	}
}
