package com.system.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.MainCollegeType;
import com.system.manager.entity.MainCourseWare;
import com.system.manager.web.model.MainCourseWareModel;
//知识课件
public interface MainCourseWareService {
	
	/**
	 * 添加知识类型
	 * @param model 添加的数据封装类
	 */
	public Integer addCourseWare(MainCourseWareModel model);
	
	 /**
	 * 分页查询(所有知识类型)
	 * @return
	 */
    public Page<MainCourseWare> pageList(Pageable pageable,MainCourseWareModel model);
    
    /**
     * 分页查询(条件查询。例如课程名称)
     * @return
     */
    public Page<MainCourseWare> findByPage(MainCourseWareModel model,Pageable pageable);
    
    /**
     * 分页查询(条件查询。课件类型)
     * @return
     */
    public List<MainCourseWare> findByPageType(MainCourseWareModel model);
    
    
    /**
	 * 手机端分页查询数据
	 * @param model
	 * @param pageable
	 * @return
	 */
	public Page<Map<String,Object>> findByPagePhone(Integer id,MainCourseWareModel model,Pageable pageable);
	
	 /**
    * 根据id查询
    * @param id
    * @return
    */
    public abstract MainCourseWare getCollege(Integer id);
}
