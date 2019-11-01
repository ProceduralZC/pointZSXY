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

import com.system.manager.entity.KnowledgeCourse;
import com.system.manager.service.KnowledgeCourseService;
import com.system.manager.util.BarCodeService;
import com.system.manager.web.model.KnowledgeCourseModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/system")
public class KnowledgeCourseController extends BaseController{
	
	@Autowired
	private KnowledgeCourseService knowledgeCourseService;
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
	@RequestMapping(value ="/addKnowledgeCourse" ,method = {RequestMethod.POST})
	public String addKnowledgeCourse(@ModelAttribute  KnowledgeCourseModel model,HttpServletRequest request
			,@RequestParam() String imageurl,HttpServletResponse response){
		model.setCollegeimage(imageurl);
	    //生成图片
		if(null != imageurl && !imageurl.equals("")){
			Integer barCodeImg = barCodeService.createBarCode(imageurl);
			model.setKnowledgeimageid(barCodeImg);
		}
		
        Integer id = knowledgeCourseService.addKnowledgeCourse(model);
		return super.message(id+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllKnowledgeCourse", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<KnowledgeCourse> findAll(@ModelAttribute KnowledgeCourseModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return knowledgeCourseService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllKnowledgeCourseSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<KnowledgeCourse> findAllCourseWareSearch(@ModelAttribute KnowledgeCourseModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
		Page<KnowledgeCourse> page= knowledgeCourseService.findByPage(model, pageable);
		
		return page;
	}
	/**
	 * 删除信息
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/deleteKnowledgeCourse" ,method = {RequestMethod.POST})
	public String deleteClaimRecord(@RequestParam  String ids,HttpServletRequest request,HttpServletResponse response){
		knowledgeCourseService.delete(ids);
		return super.message("","操作成功!","success");
	}
	
	/**
	 * 通过id查询详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/getKnowledgeCourseById/{id}" ,method = {RequestMethod.GET})
	public KnowledgeCourse  getMaterialsById(@PathVariable Integer id,HttpServletRequest request){
		return knowledgeCourseService.get(id);
	}
	
	/**
	 * 编辑
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/eidtKnowledgeCourse/{id}" ,method = {RequestMethod.POST})
	public String update(@PathVariable Integer id,@ModelAttribute  KnowledgeCourseModel model
			,HttpServletRequest request,HttpServletResponse response){
		
		 //生成图片
		if(null != model.getCollegeimage() && !model.getCollegeimage().equals("")){
			Integer barCodeImg = barCodeService.createBarCode(model.getCollegeimage());
			model.setKnowledgeimageid(barCodeImg);
		}
		
        knowledgeCourseService.editKnowledgeCourse(id, model);
		return super.message("","添加成功","success");
	}
	
}
