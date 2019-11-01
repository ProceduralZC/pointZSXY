package com.system.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.MainCollegeType;
import com.system.manager.entity.SysVersion;
import com.system.manager.web.model.MainCollegeTypeModel;
import com.system.user.entity.SysUsers;

public interface MainCollegeTypeService {
	
	/**
	 * 添加知识类型
	 * @param model 添加的数据封装类
	 */
	public Integer addCollegeType(MainCollegeTypeModel model,SysUsers sysUsers);
	
	 /**
	 * 分页查询(所有知识类型)
	 * @return
	 */
    public Page<MainCollegeType> pageList(Pageable pageable,MainCollegeTypeModel model);
    
    /**
     * 分页查询(条件查询。例如课程名称)
     * @return
     */
    public Page<MainCollegeType> findByPage(MainCollegeTypeModel model,Pageable pageable);
    /**
     * 分页查询(条件查询。课件类型)
     * @return
     */
    public List<MainCollegeType> findByPageType(MainCollegeTypeModel model,String collegetypecode);
    
    /**
    * 根据id查询
    * @param id
    * @return
    */
    public abstract MainCollegeType getCollege(Integer id);
}
