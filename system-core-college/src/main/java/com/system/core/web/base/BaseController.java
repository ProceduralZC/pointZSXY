package com.system.core.web.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.system.core.jsonpacket.JsonResponse;
import com.system.core.jsonpacket.StatusCode;
import com.system.core.util.ParamUtil;

public class BaseController {
	
	public Pageable getPageable(HttpServletRequest request) {
		int number = ParamUtil.getIntParamter(request, "number", 0);
		int size = ParamUtil.getIntParamter(request, "size", 10);
		String sortField = ParamUtil.getStrParamter(request, "sortField", "");
		String sortOrder = ParamUtil.getStrParamter(request, "sortOrder", "desc");
		Sort sort = null;
		if (!"".equals(sortField)) {
			String[] sortFields = sortField.split(",");
			String[] sortOrders = sortOrder.split(",");
			List<Order> list = new ArrayList<Order>();
			for(int i=0;i<sortFields.length;i++){
				Order order = new Order(Sort.Direction.fromString(sortOrders[i]), sortFields[i]);
				list.add(order);
			}
			sort = new Sort(list);
		}
		Pageable pageable = new PageRequest(number, size, sort);
		return pageable;
	}
	public String message(Object id, String message,String flag){
		String res = "{\"id\":\"" + id.toString() + "\",\"message\":\"" + message + "\",\"flag\":\"" + flag + "\"}";
	    return res;
	}

    public void setResponseHeader(HttpServletResponse response,String fileName){  
        try{  
        	//response.setContentType("application/msexcel;charset=UTF-8");  //两种方法都可�? 
            response.setContentType("application/octet-stream;charset=iso-8859-1");  
            response.setHeader("Content-Disposition", "filename=" +new String(fileName.getBytes("gb2312"), "iso8859-1"));  
            //客户端不缓存  
            response.addHeader("Pargam", "no-cache");  
            response.addHeader("Cache-Control", "no-cache");  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
    } 
    
    public JsonResponse sucessResponse() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(StatusCode.CODE_SUCCESS);
        jsonResponse.setMsg(StatusCode.CODE_SUCCESS);
        return jsonResponse;
      }

      public JsonResponse failureResponse() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(StatusCode.CODE_FAIL);
        jsonResponse.setMsg(StatusCode.CODE_FAIL);
        return jsonResponse;
      }

      public JsonResponse failureResponse(String message) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(StatusCode.CODE_FAIL);
        jsonResponse.setMsg(message);
        return jsonResponse;
      }
 }
