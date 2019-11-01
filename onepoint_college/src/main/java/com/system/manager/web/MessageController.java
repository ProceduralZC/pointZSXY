package com.system.manager.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.manager.entity.MessageSys;
import com.system.manager.service.MessageService;
import com.system.manager.util.BarCodeService;
import com.system.manager.web.model.MessageSysModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;
//添加消息
@RestController
@RequestMapping(value="/system")
public class MessageController extends BaseController{
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private SysUsersService sysUsersService;
	@Autowired
	private BarCodeService barCodeService;
	
	/**
	 * 添加消息
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addMessage" ,method = {RequestMethod.POST})
	public String addKnowledgeCourse(@ModelAttribute  MessageSysModel model,HttpServletRequest request
			,HttpServletResponse response){
		
        Integer id = messageService.addMessage(model);
		return super.message(id+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllMessage", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MessageSys> findAll(@ModelAttribute MessageSysModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return messageService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllMessageSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MessageSys> findAllCourseWareSearch(@ModelAttribute MessageSysModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		Page<MessageSys> page= messageService.findByPage(model, pageable);
		
		return page;
	}
	/**
	 * 删除信息
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/deleteMessage" ,method = {RequestMethod.POST})
	public String deleteClaimRecord(@RequestParam  String ids,HttpServletRequest request,HttpServletResponse response){
		messageService.delete(ids);
		return super.message("","操作成功!","success");
	}
	
	/**
	 * 通过id查询详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/getMessageById/{id}" ,method = {RequestMethod.GET})
	public MessageSys  getMaterialsById(@PathVariable Integer id,HttpServletRequest request){
		return messageService.get(id);
	}
	
	/**
	 * 编辑
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/eidtMessage/{id}" ,method = {RequestMethod.POST})
	public String update(@PathVariable Integer id,@ModelAttribute  MessageSysModel model
			,HttpServletRequest request,HttpServletResponse response){
		
		//生成图片
//		if(null != model.getCollegeimage() && !model.getCollegeimage().equals("")){
//			Integer barCodeImg = barCodeService.createBarCode(model.getCollegeimage());
//			model.setKnowledgeimageid(barCodeImg);
//		}
		
		messageService.editKnowledgeCourse(id, model);
		return super.message("","编辑成功","success");
	}
	
}
