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

import com.system.manager.entity.MainSubclassTime;
import com.system.manager.service.MainSubclassTimeService;
import com.system.manager.web.model.MainSubclassTimeModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;
//知识课件--子课时
@RestController
@RequestMapping(value="/system")
public class MainSubclassTimeController extends BaseController{
	
	@Autowired
	private MainSubclassTimeService mainSubclassTimeService;
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 添加知识课件
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addSubclassTime" ,method = {RequestMethod.POST})
	public String addSubclassTime(@ModelAttribute MainSubclassTimeModel model,HttpServletRequest request
			,@RequestParam() String id,HttpServletResponse response){
		if(null == model.getTypeid()){
			model.setTypeid(id);
		}
		
        Integer idinfo = mainSubclassTimeService.addSubclassTime(model);
		return super.message(idinfo+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllSubclassTime", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainSubclassTime> findAll(@ModelAttribute MainSubclassTimeModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return mainSubclassTimeService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllSubclassTimeSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainSubclassTime> findSubclassTimeSearch(@ModelAttribute MainSubclassTimeModel model
			,@RequestParam() String id,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
//		if(null!=sysUsers.getWorkshop()){
//			model.setWorkshop(sysUsers.getWorkshop().getId());
//		}
//		if(null!=sysUsers.getTeam()){
//			model.setTeam(sysUsers.getTeam().getId());
//		}
		
		if(null == model.getTypeid()){
			model.setTypeid(id);
		}
		
		Page<MainSubclassTime> page= mainSubclassTimeService.findByPage(model, pageable);
		
		return page;
	}
	
}
