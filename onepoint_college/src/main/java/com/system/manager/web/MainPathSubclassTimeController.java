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

import com.system.manager.entity.MainPathSubclassTime;
import com.system.manager.entity.MainSubclassTime;
import com.system.manager.service.MainPathSubclassTimeService;
import com.system.manager.web.model.MainPathSubclassTimeModel;
import com.system.manager.web.model.MainSubclassTimeModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/system")
public class MainPathSubclassTimeController extends BaseController{
	
	@Autowired
	private MainPathSubclassTimeService mainPathSubclassTimeService;
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 添加知识课件
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addPathSubclassTime" ,method = {RequestMethod.POST})
	public String addPathSubclassTime(@ModelAttribute MainPathSubclassTimeModel model,HttpServletRequest request
			,@RequestParam() String id,HttpServletResponse response){
		if(null == model.getTypeid()){
			model.setTypeid(id);
		}
		
        Integer idinfo = mainPathSubclassTimeService.addPathSubclassTime(model);
		return super.message(idinfo+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllPathSubclassTime", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainPathSubclassTime> findAll(@ModelAttribute MainPathSubclassTimeModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return mainPathSubclassTimeService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllPathSubclassTimeSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainPathSubclassTime> findSubclassTimeSearch(@ModelAttribute MainPathSubclassTimeModel model
			,@RequestParam() String id,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		if(null == model.getTypeid()){
			model.setTypeid(id);
		}
		
		Page<MainPathSubclassTime> page= mainPathSubclassTimeService.findByPage(model, pageable);
		
		return page;
	}
	
}
