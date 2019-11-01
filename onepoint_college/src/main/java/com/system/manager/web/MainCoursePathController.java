package com.system.manager.web;

import java.util.List;

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

import com.system.manager.entity.MainCoursePath;
import com.system.manager.service.MainCoursePathService;
import com.system.manager.util.BarCodeService;
import com.system.manager.web.model.MainCoursePathModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/system")
public class MainCoursePathController extends BaseController{
	
	@Autowired
	private MainCoursePathService mainCoursePathService;
	@Autowired
	private SysUsersService sysUsersService;
	@Autowired
	private BarCodeService barCodeService;
	
	/**
	 * 添加知识路径
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addCoursePath" ,method = {RequestMethod.POST})
	public String addCollegeType(@ModelAttribute  MainCoursePathModel model,HttpServletRequest request
			,HttpServletResponse response){
        Integer id = mainCoursePathService.addCoursePath(model);
		return super.message(id+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识路径
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCoursePath", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainCoursePath> findAll(@ModelAttribute MainCoursePathModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return mainCoursePathService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCoursePathSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainCoursePath> findAllCourseWareSearch(@ModelAttribute MainCoursePathModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		Page<MainCoursePath> page= mainCoursePathService.findByPage(model, pageable);
		
		return page;
	}
	
	/**
	 * 通过条件路径类型查询一类信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCoursePathBytype", method = {RequestMethod.GET,RequestMethod.POST})
	public List<MainCoursePath> findAllCourseWareBytype(@ModelAttribute MainCoursePathModel model,HttpServletRequest request, HttpServletResponse response) {
		List<MainCoursePath> list = mainCoursePathService.findByPageType(model);
		
		return list;
	}
	
}
