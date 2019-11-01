package com.system.manager.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.MessageSys;
import com.system.manager.web.model.MessageSysModel;
//消息
public interface MessageService {
	
	/**
	 * 添加消息
	 * @param model 添加的数据封装类
	 */
	public Integer addMessage(MessageSysModel model);
	
	 /**
	 * 分页查询(所有)
	 * @return
	 */
    public Page<MessageSys> pageList(Pageable pageable,MessageSysModel model);
    
    /**
     * 分页查询(条件查询。例如消息)
     * @return
     */
    public Page<MessageSys> findByPage(MessageSysModel model,Pageable pageable);
    
    /**
	 * 手机端分页查询数据
	 * @param model
	 * @param pageable
	 * @return
	 */
	public Page<Map<String,Object>> findByPagePhone(MessageSysModel model,Pageable pageable);
    
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
	public MessageSys get(Integer id);
	/**
	 * 编辑
	 * @param id
	 * @param model
	 */
	public void editKnowledgeCourse(Integer id,MessageSysModel model);
}
