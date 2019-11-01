package com.system.manager.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.KnowledgeCourse;
import com.system.manager.entity.MainCoursePath;
import com.system.manager.web.model.KnowledgeCourseModel;
import com.system.manager.web.model.MainCourseWareModel;

public interface KnowledgeCourseService {
	
	/**
	 * 添加知识
	 * @param model 添加的数据封装类
	 */
	public Integer addKnowledgeCourse(KnowledgeCourseModel model);
	
	 /**
	 * 分页查询(所有知识类型)
	 * @return
	 */
    public Page<KnowledgeCourse> pageList(Pageable pageable,KnowledgeCourseModel model);
    
    /**
     * 分页查询(条件查询。例如课程名称)
     * @return
     */
    public Page<KnowledgeCourse> findByPage(KnowledgeCourseModel model,Pageable pageable);
    
    /**
	 * 手机端分页查询数据
	 * @param model
	 * @param pageable
	 * @return
	 */
	public Page<Map<String,Object>> findByPagePhone(KnowledgeCourseModel model,Pageable pageable);
    
    
    /**
	 * 删除
	 * @param ids
	 */
	public  void delete(String ids);
	/**
	 * 查询一个信息-通过id
	 * @param ids
	 * @return
	 */
	public KnowledgeCourse get(Integer id);
	/**
	 * 编辑
	 * @param id
	 * @param model
	 */
	public void editKnowledgeCourse(Integer id,KnowledgeCourseModel model);
	
	/**
     * 根据id查询
     * @param id
     * @return
     */
     public abstract KnowledgeCourse getCollege(Integer id);
}
