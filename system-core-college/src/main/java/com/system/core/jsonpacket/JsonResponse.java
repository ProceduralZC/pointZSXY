package com.system.core.jsonpacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.core.util.DateUtils;


public class JsonResponse{

	/** 分页信息 */
	private List<?> items;
	/***额外的参数**/
	private Map<Object,Object> extra;

	private String code = "";
	// 异常信息
	private String msg = "";

	private String updateTime = DateUtils.getNowDateTime();

	public void setContent(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	
	public List<?> getItems() {
		return items == null ? new ArrayList<Object>() : items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	public Map<Object, Object> getExtra() {
		return extra==null?new HashMap<>():extra;
	}

	public void setExtra(Map<Object, Object> extra) {
		this.extra = extra;
	}
}
