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
import com.system.manager.entity.MainBoutiqueseries;
import com.system.manager.service.MainBoutiqueseriesService;
import com.system.manager.web.model.MainBoutiqueseriesModel;
import com.system.user.web.base.BaseController;
//精品知识
@RestController
@RequestMapping(value="/ms")
public class PMainBoutiqueseriesController extends BaseController{
	
	@Autowired
	private MainBoutiqueseriesService mainBoutiqueseriesService;
	/**
	 * 分页查询精品知识
	 * @param start
	 * @param size
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryAllBoutiqueseriespage/{id}/{start}/{size}",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryAllBoutiqueseriespage(@PathVariable Integer id,@PathVariable Integer start,@PathVariable Integer size,
			@ModelAttribute MainBoutiqueseriesModel model,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items= new ArrayList<Map<String,Object>>();
		Pageable pageable = new PageRequest(start-1, size);
		try {
			Page<Map<String,Object>> page = mainBoutiqueseriesService.findByPagePhone(id,model, pageable);
			items =page.getContent();
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
	}
	/**
	 * 查询精品知识
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllBoutiqueseries", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryAllBoutiqueseries(@ModelAttribute MainBoutiqueseriesModel model,HttpServletRequest request
			, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<MainBoutiqueseries> items = new ArrayList<MainBoutiqueseries>();
		try{
			List<MainBoutiqueseries> list = mainBoutiqueseriesService.findByPageType(model);
			
			items.addAll(list);
			jsonResponse.setItems(items);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonResponse;
	}
	
}
