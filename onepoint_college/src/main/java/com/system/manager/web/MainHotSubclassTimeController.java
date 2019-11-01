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

import com.system.manager.entity.MainHotSubclassTime;
import com.system.manager.service.MainHotSubclassTimeService;
import com.system.manager.web.model.MainHotSubclassTimeModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/system")
public class MainHotSubclassTimeController extends BaseController{
	
	@Autowired
	private MainHotSubclassTimeService mainHotSubclassTimeService;
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 添加知识课件
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addHotSubclassTime" ,method = {RequestMethod.POST})
	public String addHotSubclassTime(@ModelAttribute MainHotSubclassTimeModel model,HttpServletRequest request
			,@RequestParam() String id,HttpServletResponse response){
		if(null == model.getTypeid()){
			model.setTypeid(id);
		}
		
        Integer idinfo = mainHotSubclassTimeService.addHotSubclassTime(model);
		return super.message(idinfo+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllHotSubclassTime", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainHotSubclassTime> findAll(@ModelAttribute MainHotSubclassTimeModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return mainHotSubclassTimeService.pageList(pageable,model);
	}
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllHotSubclassTimeSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainHotSubclassTime> findSubclassTimeSearch(@ModelAttribute MainHotSubclassTimeModel model
			,@RequestParam() String id,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		if(null == model.getTypeid()){
			model.setTypeid(id);
		}
		
		Page<MainHotSubclassTime> page= mainHotSubclassTimeService.findByPage(model, pageable);
		
		return page;
	}
	
}
