package com.system.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.Cynamic;
import com.system.manager.entity.MainCoursePath;
import com.system.manager.web.model.CynamicModel;
import com.system.manager.web.model.MainCoursePathModel;

public interface CynamicService {
	
	/**
	 * 添加动态
	 * @param model 添加的数据封装类
	 */
	public Integer addCynamic(CynamicModel model);
	
	 /**
	 * 分页查询(所有动态)
	 * @return
	 */
    public Page<Cynamic> pageList(Pageable pageable,CynamicModel model);
    
    /**
     * 分页查询(条件查询。例如 标题)
     * @return
     */
    public Page<Cynamic> findByPage(CynamicModel model,Pageable pageable);
    
    /**
     * 查询所有动态
     * @return
     */
    public List<Cynamic> findByPageType(CynamicModel model);
    
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
	public Cynamic get(Integer id);
	/**
	 * 编辑
	 * @param id
	 * @param model
	 */
	public void editKnowledgeCourse(Integer id,CynamicModel model);
}
