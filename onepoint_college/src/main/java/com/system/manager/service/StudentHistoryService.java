package com.system.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.Cynamic;
import com.system.manager.entity.MainCourseWare;
import com.system.manager.entity.StudentHistory;
import com.system.manager.web.model.CynamicModel;
import com.system.manager.web.model.MainCoursePathModel;
import com.system.manager.web.model.StudentHistoryModel;

public interface StudentHistoryService {
	
	/**
	 * 学习记录
	 * @param model 添加的数据封装类
	 */
	public Integer addStudentHistory(StudentHistoryModel model);
	/**
	 * 学习记录 多个类型
	 * @param model 添加的数据封装类
	 */
	public Integer addStudentHistoryType(Integer id,Integer type,Integer userid);
	
	 /**
	 * 分页查询(学习记录)
	 * @return
	 */
    public Page<StudentHistory> pageList(Pageable pageable,StudentHistoryModel model);
    
    /**
     * 分页查询(条件查询。例如 标题)
     * @return
     */
    public Page<StudentHistory> findByPage(StudentHistoryModel model,Pageable pageable);
    
    /**
   	 * 手机端分页查询数据
   	 * @param model
   	 * @param pageable
   	 * @return
   	 */
   	public Page<Map<String,Object>> findByPagePhone(Integer id,StudentHistoryModel model,Pageable pageable);
    
    /**
     * 查询学习记录
     * @return
     */
    public List<StudentHistory> findByPageType(StudentHistoryModel model);
    
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
	public StudentHistory get(Integer id);
	/**
	 * 编辑
	 * @param id
	 * @param model
	 */
	public void editKnowledgeCourse(Integer id,StudentHistoryModel model);
	
}
