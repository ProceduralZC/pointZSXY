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

import com.system.manager.entity.MainJPSubclassTime;
import com.system.manager.service.MainJPSubclassTimeService;
import com.system.manager.web.model.MainJPSubclassTimeModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;
//精品课时
@RestController
@RequestMapping(value="/system")
public class MainJPSubclassTimeController extends BaseController{
	
	@Autowired
	private MainJPSubclassTimeService mainJPSubclassTimeService;
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 添加知识课件
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addJPSubclassTime" ,method = {RequestMethod.POST})
	public String addJPSubclassTime(@ModelAttribute MainJPSubclassTimeModel model,HttpServletRequest request
			,@RequestParam() String id,HttpServletResponse response){
		if(null == model.getTypeid()){
			model.setTypeid(id);
		}
		
        Integer idinfo = mainJPSubclassTimeService.addJPSubclassTime(model);
		return super.message(idinfo+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllJPSubclassTime", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainJPSubclassTime> findAll(@ModelAttribute MainJPSubclassTimeModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return mainJPSubclassTimeService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllJPSubclassTimeSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainJPSubclassTime> findSubclassTimeSearch(@ModelAttribute MainJPSubclassTimeModel model
			,@RequestParam() String id,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		if(null == model.getTypeid()){
			model.setTypeid(id);
		}
		
		Page<MainJPSubclassTime> page= mainJPSubclassTimeService.findByPage(model, pageable);
		
		return page;
	}
	
}
