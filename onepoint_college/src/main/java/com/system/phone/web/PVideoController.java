package com.system.phone.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.config.SysConfig;
import com.system.manager.entity.Video;
import com.system.manager.service.VideoService;
import com.system.manager.web.model.VideoModel;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/ms")
public class PVideoController  extends BaseController{

	@Autowired
	private SysConfig config;
	@Autowired
	private VideoService videoService;
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 查询所有课程资源
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllVideo", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<Video> findAll(@ModelAttribute VideoModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return videoService.pageList(pageable,model);
	}
	
}
