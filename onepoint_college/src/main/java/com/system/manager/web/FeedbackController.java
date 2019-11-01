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

import com.system.manager.entity.Feedback;
import com.system.manager.service.FeedbackService;
import com.system.manager.util.BarCodeService;
import com.system.manager.web.model.FeedbackModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/system")
public class FeedbackController extends BaseController{
	
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 添加意见反馈
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addFeedback" ,method = {RequestMethod.POST})
	public String addFeedback(@ModelAttribute  FeedbackModel model,HttpServletRequest request
			,HttpServletResponse response){
		
        Integer id = feedbackService.addFeedback(model);
		return super.message(id+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllFeedback", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<Feedback> findAll(@ModelAttribute FeedbackModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return feedbackService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllFeedbackSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<Feedback> findAllFeedbackSearch(@ModelAttribute FeedbackModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		Page<Feedback> page= feedbackService.findByPage(model, pageable);
		
		return page;
	}
	/**
	 * 删除信息
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/deleteFeedback" ,method = {RequestMethod.POST})
	public String deleteClaimRecord(@RequestParam  String ids,HttpServletRequest request,HttpServletResponse response){
		feedbackService.delete(ids);
		return super.message("","操作成功!","success");
	}
	
	/**
	 * 通过id查询详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/getFeedbackById/{id}" ,method = {RequestMethod.GET})
	public Feedback  getMaterialsById(@PathVariable Integer id,HttpServletRequest request){
		return feedbackService.get(id);
	}
	
	/**
	 * 编辑
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/eidtFeedback/{id}" ,method = {RequestMethod.POST})
	public String update(@PathVariable Integer id,@ModelAttribute  FeedbackModel model
			,HttpServletRequest request,HttpServletResponse response){
		
		feedbackService.editFeedback(id, model);
		return super.message("","添加成功","success");
	}
	
}
