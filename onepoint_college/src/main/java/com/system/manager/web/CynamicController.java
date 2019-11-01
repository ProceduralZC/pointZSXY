package com.system.manager.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.manager.entity.Cynamic;
import com.system.manager.service.CynamicService;
import com.system.manager.util.BarCodeService;
import com.system.manager.web.model.CynamicModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/system")
public class CynamicController extends BaseController{
	
	@Autowired
	private CynamicService cynamicService;
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
	@RequestMapping(value ="/addCynamic" ,method = {RequestMethod.POST})
	public String addCynamic(@ModelAttribute  CynamicModel model,HttpServletRequest request
			,@RequestParam() String imageurl,HttpServletResponse response){
		model.setCollegeimage(imageurl);
	    //生成图片
		if(null != imageurl && !imageurl.equals("")){
			Integer barCodeImg = barCodeService.createBarCode(imageurl);
			model.setCynamicimageid(barCodeImg);
		}
		
        Integer id = cynamicService.addCynamic(model);
		return super.message(id+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCynamic", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<Cynamic> findAll(@ModelAttribute CynamicModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return cynamicService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCynamicSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<Cynamic> findAllCourseWareSearch(@ModelAttribute CynamicModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		Page<Cynamic> page= cynamicService.findByPage(model, pageable);
		
		return page;
	}
	/**
	 * 删除信息
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/deleteCynamic" ,method = {RequestMethod.POST})
	public String deleteClaimRecord(@RequestParam  String ids,HttpServletRequest request,HttpServletResponse response){
		cynamicService.delete(ids);
		return super.message("","操作成功!","success");
	}
	
	/**
	 * 通过id查询详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/getCynamicById/{id}" ,method = {RequestMethod.GET})
	public Cynamic  getMaterialsById(@PathVariable Integer id,HttpServletRequest request){
		return cynamicService.get(id);
	}
	
	/**
	 * 编辑
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/eidtCynamic/{id}" ,method = {RequestMethod.POST})
	public String update(@PathVariable Integer id,@ModelAttribute  CynamicModel model
			,HttpServletRequest request,HttpServletResponse response){
		
		 //生成图片
		if(null != model.getCollegeimage() && !model.getCollegeimage().equals("")){
			Integer barCodeImg = barCodeService.createBarCode(model.getCollegeimage());
			model.setCynamicimageid(barCodeImg);
		}
		
		cynamicService.editKnowledgeCourse(id, model);
		return super.message("","添加成功","success");
	}
	
}
