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
import com.system.manager.service.StudentHistoryService;
import com.system.manager.web.model.StudentHistoryModel;
import com.system.user.web.base.BaseController;
//学习记录
@RestController
@RequestMapping(value="/ms")
public class PMainStudentHistoryController extends BaseController{
	
	@Autowired
	private StudentHistoryService studentHistoryService;
	/**
	 * 添加学习记录
	 * @param start
	 * @param size
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addStudentHistory/{id}/{type}/{userid}",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryAllZSCourseWarepage(@PathVariable Integer id,@PathVariable Integer type
			,@PathVariable Integer userid,@ModelAttribute StudentHistoryModel model,HttpServletRequest request
			, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		try{
			Integer idinfo = studentHistoryService.addStudentHistoryType(id,type,userid);
			jsonResponse.setMsg("添加成功!");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
	
	/**
	 * 通过条件查询学习记录
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryStudentHistorypage/{id}/{start}/{size}",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryStudentHistorypage(@PathVariable Integer id,@PathVariable Integer start,@PathVariable Integer size,
			@ModelAttribute StudentHistoryModel model,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items= new ArrayList<Map<String,Object>>();
		Pageable pageable = new PageRequest(start-1, size);
		try {
			Page<Map<String,Object>> page = studentHistoryService.findByPagePhone(id,model, pageable);
			items =page.getContent();
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
	}
	
}
