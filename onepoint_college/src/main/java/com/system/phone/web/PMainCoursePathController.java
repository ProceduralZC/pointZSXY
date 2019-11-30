package com.system.phone.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.jsonpacket.JsonResponse;
import com.system.manager.entity.MainCoursePath;
import com.system.manager.service.MainCoursePathService;
import com.system.manager.web.model.MainCoursePathModel;
import com.system.user.web.base.BaseController;
//知识路径
@RestController
@RequestMapping(value="/ms")
public class PMainCoursePathController extends BaseController{
	
	@Autowired
	private MainCoursePathService mainCoursePathService;
	
	/**
	 * 通过条件查询知识路径
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryAllPhoneCoursePathpage/{id}/{start}/{size}",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryAllPhoneCoursePathpage(@PathVariable Integer id,@PathVariable Integer start,@PathVariable Integer size,
			@ModelAttribute MainCoursePathModel model,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items= new ArrayList<Map<String,Object>>();
		Pageable pageable = new PageRequest(start-1, size);
		try {
			Page<Map<String,Object>> page = mainCoursePathService.findByPagePhone(id,model, pageable);
			items =page.getContent();
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
	}
	
	/**
	 * 查询所有知识路径
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllPhoneCoursePath", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryAllPhoneCoursePath(@ModelAttribute MainCoursePathModel model,HttpServletRequest request
			, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<MainCoursePath> items = new ArrayList<MainCoursePath>();
		try{
			List<MainCoursePath> list = mainCoursePathService.findByPageType(model);
			
			items.addAll(list);
			jsonResponse.setItems(items);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonResponse;
	}
	
}
