package com.system.manager.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.MainHotSubclassTime;
import com.system.manager.web.model.MainCourseWareModel;
import com.system.manager.web.model.MainHotSubclassTimeModel;

public interface MainHotSubclassTimeService {
	
	/**
	 * 添加知识类型
	 * @param model 添加的数据封装类
	 */
	public Integer addHotSubclassTime(MainHotSubclassTimeModel model);
	
	 /**
	 * 分页查询(所有知识类型)
	 * @return
	 */
    public Page<MainHotSubclassTime> pageList(Pageable pageable,MainHotSubclassTimeModel model);
    
    /**
     * 分页查询(条件查询。例如课程名称)
     * @return
     */
    public Page<MainHotSubclassTime> findByPage(MainHotSubclassTimeModel model,Pageable pageable);
    
    /**
  	 * 手机端分页查询数据
  	 * @param model
  	 * @param pageable
  	 * @return
  	 */
  	public Page<Map<String,Object>> findByPagePhone(Integer id,MainHotSubclassTimeModel model,Pageable pageable);
}
