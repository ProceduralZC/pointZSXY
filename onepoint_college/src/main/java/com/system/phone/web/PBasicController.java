package com.system.phone.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.entity.SysDataDictionary;
import com.system.core.jsonpacket.JsonResponse;
import com.system.core.service.SysDataDictionaryService;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping(value="/ms")
public class PBasicController  extends BaseController{

	@Autowired
	private SysDataDictionaryService dataDictionaryService;
	
	@RequestMapping(value = { "/getTeamList/{workshopId}" }, method = {RequestMethod.GET,RequestMethod.POST })
	public JsonResponse getTeamList(@PathVariable Integer workshopId,HttpServletRequest request,HttpServletResponse response){
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		try{
			List<SysDataDictionary> list =dataDictionaryService.findByParentId(workshopId);
			for (SysDataDictionary sysDataDictionary : list) {
				Map<String,Object> map  = new HashMap<String,Object>();
				map.put("id", sysDataDictionary.getId());
				map.put("name", sysDataDictionary.getName());
				items.add(map);
			}
			jsonResponse.setItems(items);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
	
}
