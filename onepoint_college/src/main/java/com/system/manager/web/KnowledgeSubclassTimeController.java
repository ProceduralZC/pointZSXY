package com.system.manager.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.manager.entity.KnowledgeSubclassTime;
import com.system.manager.service.KnowledgeSubclassTimeService;
import com.system.manager.web.model.KnowledgeSubclassTimeModel;
import com.system.manager.web.model.MainSubclassTimeModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/system")
public class KnowledgeSubclassTimeController extends BaseController{
	
	@Autowired
	private KnowledgeSubclassTimeService knowledgeSubclassTimeService;
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 添加知识课件
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addKnowledgeSubclassTime" ,method = {RequestMethod.POST})
	public String addKnowledgeSubclassTime(@ModelAttribute KnowledgeSubclassTimeModel model,HttpServletRequest request
			,@RequestParam() String id,HttpServletResponse response){
		if(null == model.getTypeid()){
			model.setTypeid(Integer.parseInt(id));
		}
		
        Integer idinfo = knowledgeSubclassTimeService.addKnowledgeSubclassTime(model);
		return super.message(idinfo+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllKnowledgeSubclassTime", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<KnowledgeSubclassTime> findAll(@ModelAttribute KnowledgeSubclassTimeModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return knowledgeSubclassTimeService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllKnowledgeSubclassTimeSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<KnowledgeSubclassTime> findSubclassTimeSearch(@ModelAttribute KnowledgeSubclassTimeModel model
			,@RequestParam() String id,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		if(null == model.getTypeid()){
			model.setTypeid(Integer.parseInt(id));
		}
		
		Page<KnowledgeSubclassTime> page= knowledgeSubclassTimeService.findByPage(model, pageable);
		
		return page;
	}
	
	/**
	 * 删除信息
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/deleteKnowledgeSubclassTime" ,method = {RequestMethod.POST})
	public String deleteClaimRecord(@RequestParam  String ids,HttpServletRequest request,HttpServletResponse response){
		knowledgeSubclassTimeService.delete(ids);
		return super.message("","操作成功!","success");
	}
	
}
