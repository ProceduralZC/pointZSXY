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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.jsonpacket.JsonResponse;
import com.system.core.util.ParamUtil;
import com.system.manager.service.FeedbackService;
import com.system.manager.web.model.FeedbackModel;
import com.system.user.web.base.BaseController;
//首页动态
@RestController
@RequestMapping(value="/ms")
public class PFeedbackController extends BaseController{
	
	@Autowired
	private FeedbackService feedbackService;
	
	/**
	 * 通过条件查询反馈
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryAllfeedbackpage/{id}/{start}/{size}",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryFeedbackpage(@PathVariable Integer id,@PathVariable Integer start,@PathVariable Integer size,
			@ModelAttribute FeedbackModel model,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items= new ArrayList<Map<String,Object>>();
		Pageable pageable = new PageRequest(start-1, size);
		try {
			Page<Map<String,Object>> page = feedbackService.findByPagePhone(id,model, pageable);
			items =page.getContent();
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
	}
	
	/**
	 * 添加意见反馈
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addfeedback", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryFeedback(@ModelAttribute FeedbackModel model,HttpServletRequest request
			,@RequestParam String contact,@RequestParam String feedbackinfo, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		try{
			
			if(ParamUtil.isEmpty(contact)){
				return super.failureResponse("联系方式不能为空！");
			}
			if(ParamUtil.isEmpty(feedbackinfo)){
				return super.failureResponse("反馈信息不能为空！");
			}
			
			model.setFeedbackcontact(contact);
			model.setFeedbackproposal(feedbackinfo);
			Integer id = feedbackService.addFeedback(model);
			jsonResponse.setMsg("添加成功!");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonResponse;
	}
	
}
