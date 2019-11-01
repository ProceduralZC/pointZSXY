package com.system.manager.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.KnowledgeSubclassTime;
import com.system.manager.web.model.KnowledgeSubclassTimeModel;
import com.system.manager.web.model.MainSubclassTimeModel;

public interface KnowledgeSubclassTimeService {
	
	/**
	 * 添加精选课程课时
	 * @param model 添加的数据封装类
	 */
	public Integer addKnowledgeSubclassTime(KnowledgeSubclassTimeModel model);
	
	 /**
	 * 分页查询(所有知识类型)
	 * @return
	 */
    public Page<KnowledgeSubclassTime> pageList(Pageable pageable,KnowledgeSubclassTimeModel model);
    
    /**
     * 分页查询(条件查询。例如课程名称)
     * @return
     */
    public Page<KnowledgeSubclassTime> findByPage(KnowledgeSubclassTimeModel model,Pageable pageable);
    

    /**
	 * 手机端分页查询数据
	 * @param model
	 * @param pageable
	 * @return
	 */
	public Page<Map<String,Object>> findByPagePhone(KnowledgeSubclassTimeModel model,Pageable pageable);
    
    /**
	 * 删除
	 * @param ids
	 */
	public  void delete(String ids);
}
