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

import com.system.manager.entity.MainCourseWare;
import com.system.manager.service.MainCourseWareService;
import com.system.manager.util.BarCodeService;
import com.system.manager.web.model.MainCourseWareModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;
//知识课件
@RestController
@RequestMapping(value="/system")
public class MainCourseWareController extends BaseController{
	
	@Autowired
	private MainCourseWareService mainCourseWareService;
	@Autowired
	private SysUsersService sysUsersService;
	@Autowired
	private BarCodeService barCodeService;
	
	/**
	 * 添加知识课件
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addCourseWare" ,method = {RequestMethod.POST})
	public String addCollegeType(@ModelAttribute  MainCourseWareModel model,HttpServletRequest request
			,@RequestParam() String imageurl,HttpServletResponse response){
		model.setCollegeimage(imageurl);
	    //生成图片
		Integer barCodeImg = barCodeService.createBarCode(imageurl);
		model.setCoursewareimageid(barCodeImg);
		
        Integer id = mainCourseWareService.addCourseWare(model);
		return super.message(id+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCourseWare", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainCourseWare> findAll(@ModelAttribute MainCourseWareModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return mainCourseWareService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCourseWareSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainCourseWare> findAllCourseWareSearch(@ModelAttribute MainCourseWareModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
//		if(null!=sysUsers.getWorkshop()){
//			model.setWorkshop(sysUsers.getWorkshop().getId());
//		}
//		if(null!=sysUsers.getTeam()){
//			model.setTeam(sysUsers.getTeam().getId());
//		}
		
		Page<MainCourseWare> page= mainCourseWareService.findByPage(model, pageable);
		
		return page;
	}
	
	/**
	 * 通过条件课件类型查询一类信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCourseWareBytype", method = {RequestMethod.GET,RequestMethod.POST})
	public List<MainCourseWare> findAllCourseWareBytype(@ModelAttribute MainCourseWareModel model,HttpServletRequest request, HttpServletResponse response) {
		List<MainCourseWare> list = mainCourseWareService.findByPageType(model);
		
		return list;
	}
	
}
