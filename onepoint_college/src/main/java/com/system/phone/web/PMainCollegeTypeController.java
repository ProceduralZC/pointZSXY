package com.system.phone.web;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.jsonpacket.JsonResponse;
import com.system.manager.entity.MainCollegeType;
import com.system.manager.service.MainCollegeTypeService;
import com.system.manager.web.model.MainCollegeTypeModel;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;
//首页课程主类型
@RestController
@RequestMapping(value="/ms")
public class PMainCollegeTypeController extends BaseController{
	
	@Autowired
	private MainCollegeTypeService mainCollegeTypeService;
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 通过条件查询主业分类
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCollegetypeBytypeInfo", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResponse findWorkshopInfoSearch(@ModelAttribute MainCollegeTypeModel model
			,HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items= new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			model.setCollegetypecode("1");
			List<MainCollegeType> list1 = mainCollegeTypeService.findByPageType(model,"1");
			model.setCollegetypecode("2");
			List<MainCollegeType> list2 = mainCollegeTypeService.findByPageType(model,"2");
			model.setCollegetypecode("3");
			List<MainCollegeType> list3 = mainCollegeTypeService.findByPageType(model,"3");
			model.setCollegetypecode("4");
			List<MainCollegeType> list4 = mainCollegeTypeService.findByPageType(model,"4");
			
			map.put("courseware",list1);
			map.put("path",list2);
			map.put("boutique",list3);
			map.put("hot",list4);
			
			items.add(map);
			jsonResponse.setItems(items);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonResponse;
	}
}
