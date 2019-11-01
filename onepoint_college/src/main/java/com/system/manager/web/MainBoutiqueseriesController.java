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

import com.system.manager.entity.MainBoutiqueseries;
import com.system.manager.service.MainBoutiqueseriesService;
import com.system.manager.util.BarCodeService;
import com.system.manager.web.model.MainBoutiqueseriesModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;
//精品知识
@RestController
@RequestMapping(value="/system")
public class MainBoutiqueseriesController extends BaseController{
	
	@Autowired
	private MainBoutiqueseriesService mainBoutiqueseriesService;
	@Autowired
	private SysUsersService sysUsersService;
	@Autowired
	private BarCodeService barCodeService;
	
	/**
	 * 添加精品知识
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addBoutiqueseries" ,method = {RequestMethod.POST})
	public String addCollegeType(@ModelAttribute  MainBoutiqueseriesModel model,HttpServletRequest request
			,@RequestParam() String imageurl,HttpServletResponse response){
		model.setCollegeimage(imageurl);
	    //生成图片
		Integer barCodeImg = barCodeService.createBarCode(imageurl);
		model.setBoutiqueseriesimageid(barCodeImg);
		
        Integer id = mainBoutiqueseriesService.addBoutiqueseries(model);
		return super.message(id+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllBoutiqueseries", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainBoutiqueseries> findAll(@ModelAttribute MainBoutiqueseriesModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return mainBoutiqueseriesService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllBoutiqueseriesSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainBoutiqueseries> findAllCourseWareSearch(@ModelAttribute MainBoutiqueseriesModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		Page<MainBoutiqueseries> page= mainBoutiqueseriesService.findByPage(model, pageable);
		
		return page;
	}
	
	/**
	 * 通过条件课件类型查询一类信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllBoutiqueseriesBytype", method = {RequestMethod.GET,RequestMethod.POST})
	public List<MainBoutiqueseries> findAllCourseWareBytype(@ModelAttribute MainBoutiqueseriesModel model,HttpServletRequest request, HttpServletResponse response) {
		List<MainBoutiqueseries> list = mainBoutiqueseriesService.findByPageType(model);
		
		return list;
	}
	
}
