package com.system.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.Feedback;
import com.system.manager.web.model.FeedbackModel;

public interface FeedbackService {
	
	/**
	 * 意见反馈
	 * @param model 添加的数据封装类
	 */
	public Integer addFeedback(FeedbackModel model);
	
	 /**
	 * 分页查询(所有动态)
	 * @return
	 */
    public Page<Feedback> pageList(Pageable pageable,FeedbackModel model);
    
    /**
     * 分页查询(条件查询。例如 标题)
     * @return
     */
    public Page<Feedback> findByPage(FeedbackModel model,Pageable pageable);
    
    /**
   	 * 手机端分页查询数据
   	 * @param model
   	 * @param pageable
   	 * @return
   	 */
   	public Page<Map<String,Object>> findByPagePhone(Integer id,FeedbackModel model,Pageable pageable);
    
    /**
     * 查询所有动态
     * @return
     */
    public List<Feedback> findByPageType(FeedbackModel model);
    
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
	public Feedback get(Integer id);
	/**
	 * 编辑
	 * @param id
	 * @param model
	 */
	public void editFeedback(Integer id,FeedbackModel model);
}
