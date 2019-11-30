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
import com.system.manager.service.MainHotSubclassTimeService;
import com.system.manager.web.model.MainHotSubclassTimeModel;
import com.system.user.web.base.BaseController;
//热门知识
@RestController
@RequestMapping(value="/ms")
public class PMainHotSubclassTimeController extends BaseController{
	
	@Autowired
	private MainHotSubclassTimeService mainHotSubclassTimeService;
	
	/**
	 * 分页查询热门知识
	 * @param start
	 * @param size
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryAllPhoneHotSubclassTime/{typeid}/{start}/{size}",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryAllPhoneHotSubclassTime(@PathVariable Integer typeid,@PathVariable Integer start,@PathVariable Integer size,
			@ModelAttribute MainHotSubclassTimeModel model,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items= new ArrayList<Map<String,Object>>();
		Pageable pageable = new PageRequest(start-1, size);
		try {
			Page<Map<String,Object>> page = mainHotSubclassTimeService.findByPagePhone(typeid,model, pageable);
			items =page.getContent();
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
	}
	
}
