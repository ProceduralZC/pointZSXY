package com.system.manager.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.manager.entity.Video;
import com.system.manager.service.VideoService;
import com.system.manager.web.model.VideoModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/system")
public class VideoController extends BaseController{
	
	@Autowired
	private VideoService videoService;
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 添加课程
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addVideo" ,method = {RequestMethod.POST})
	public String addQLD(@ModelAttribute  VideoModel model,HttpServletRequest request,HttpServletResponse response){
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
        model.setSysUsers(sysUsers);
        Integer id = videoService.addVideo(model,sysUsers);
		return super.message(id+"","添加成功","success");
	}
	
	/**
	 * 查询所有课程
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllVideo", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<Video> findAll(@ModelAttribute VideoModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return videoService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询课程
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllVideoSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<Video> findWorkshopInfoSearch(@ModelAttribute VideoModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
//		if(null!=sysUsers.getWorkshop()){
//			model.setWorkshop(sysUsers.getWorkshop().getId());
//		}
//		if(null!=sysUsers.getTeam()){
//			model.setTeam(sysUsers.getTeam().getId());
//		}
		if(null != model.getVideosourcetype()){
			model.setVideosourcetypeid(model.getVideosourcetype());
		}
		
		Page<Video> page= videoService.findByPage(model, pageable);
		
		return page;
	}
	
}
