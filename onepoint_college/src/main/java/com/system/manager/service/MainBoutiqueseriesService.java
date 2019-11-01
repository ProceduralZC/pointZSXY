package com.system.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.MainBoutiqueseries;
import com.system.manager.entity.MainCoursePath;
import com.system.manager.web.model.MainBoutiqueseriesModel;
import com.system.manager.web.model.MainCourseWareModel;

public interface MainBoutiqueseriesService {
	
	/**
	 * 添加精品知识
	 * @param model 添加的数据封装类
	 */
	public Integer addBoutiqueseries(MainBoutiqueseriesModel model);
	
	 /**
	 * 分页查询(所有知识类型)
	 * @return
	 */
    public Page<MainBoutiqueseries> pageList(Pageable pageable,MainBoutiqueseriesModel model);
    
    /**
     * 分页查询(条件查询。例如课程名称)
     * @return
     */
    public Page<MainBoutiqueseries> findByPage(MainBoutiqueseriesModel model,Pageable pageable);
    
    /**
     * 分页查询(条件查询。课件类型)
     * @return
     */
    public List<MainBoutiqueseries> findByPageType(MainBoutiqueseriesModel model);
    
    /**
	 * 手机端分页查询数据
	 * @param model
	 * @param pageable
	 * @return
	 */
	public Page<Map<String,Object>> findByPagePhone(Integer id,MainBoutiqueseriesModel model,Pageable pageable);
    
	/**
     * 根据id查询
     * @param id
     * @return
     */
     public abstract MainBoutiqueseries getCollege(Integer id);
    
}
