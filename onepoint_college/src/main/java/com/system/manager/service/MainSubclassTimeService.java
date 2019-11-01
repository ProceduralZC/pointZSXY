package com.system.manager.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.MainSubclassTime;
import com.system.manager.web.model.MainCourseWareModel;
import com.system.manager.web.model.MainSubclassTimeModel;

public interface MainSubclassTimeService {
	
	/**
	 * 添加知识类型
	 * @param model 添加的数据封装类
	 */
	public Integer addSubclassTime(MainSubclassTimeModel model);
	
	 /**
	 * 分页查询(所有知识类型)
	 * @return
	 */
    public Page<MainSubclassTime> pageList(Pageable pageable,MainSubclassTimeModel model);
    
    /**
     * 分页查询(条件查询。例如课程名称)
     * @return
     */
    public Page<MainSubclassTime> findByPage(MainSubclassTimeModel model,Pageable pageable);
    
    /**
	 * 手机端分页查询数据
	 * @param model
	 * @param pageable
	 * @return
	 */
	public Page<Map<String,Object>> findByPagePhone(MainSubclassTimeModel model,Pageable pageable);
}
