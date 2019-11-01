package com.system.manager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.Video;
import com.system.manager.web.model.VideoModel;
import com.system.user.entity.SysUsers;

public interface VideoService {
	
	/**
	 * 添加课程
	 * @param model 添加的数据封装类
	 */
	public Integer addVideo(VideoModel model,SysUsers sysUsers);
	
	 /**
	 * 分页查询(所有课程)
	 * @return
	 */
    public Page<Video> pageList(Pageable pageable,VideoModel model);
    
    /**
     * 分页查询(条件查询。例如课程名称)
     * @return
     */
    public Page<Video> findByPage(VideoModel model,Pageable pageable);
}
