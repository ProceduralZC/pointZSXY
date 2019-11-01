package com.system.manager.dao;
import java.util.List;

import com.system.manager.entity.Photo;
import com.system.manager.service.impl.EntityRepository;
public interface PhotoDao extends EntityRepository<Photo>{

	/**
	 * 查询图片集和
	 * **/
	List<Photo> queryPhotoListByFkId(String id);

	/**
	 * 保存图片
	 * **/
	void savePhoto(Photo p);
	

	void deletePhoto(String pid);

}
