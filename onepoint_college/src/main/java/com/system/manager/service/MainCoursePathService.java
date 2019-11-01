package com.system.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.MainCoursePath;
import com.system.manager.entity.MainCourseWare;
import com.system.manager.web.model.MainCoursePathModel;
import com.system.manager.web.model.MainCourseWareModel;

public interface MainCoursePathService {
	
	/**
	 * 添加路径类型
	 * @param model 添加的数据封装类
	 */
	public Integer addCoursePath(MainCoursePathModel model);
	
	 /**
	 * 分页查询(所有路径类型)
	 * @return
	 */
    public Page<MainCoursePath> pageList(Pageable pageable,MainCoursePathModel model);
    
    /**
     * 分页查询(条件查询。例如课程名称)
     * @return
     */
    public Page<MainCoursePath> findByPage(MainCoursePathModel model,Pageable pageable);
    
    /**
     * 分页查询(条件查询。课件类型)
     * @return
     */
    public List<MainCoursePath> findByPageType(MainCoursePathModel model);
    
    /**
   	 * 手机端分页查询数据
   	 * @param model
   	 * @param pageable
   	 * @return
   	 */
   	public Page<Map<String,Object>> findByPagePhone(Integer id,MainCoursePathModel model,Pageable pageable);
   	
    /**
     * 根据id查询
     * @param id
     * @return
     */
     public abstract MainCoursePath getCollege(Integer id);
}
