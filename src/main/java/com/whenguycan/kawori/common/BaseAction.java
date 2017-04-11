package com.whenguycan.kawori.common;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

/**
 * 
 * @author whenguycan
 * @date 2017年4月11日 下午1:15:13
 */
public class BaseAction {

	private static final boolean OPERATE_SUCCESS = true;
	private static final boolean OPERATE_FAILED = false;	//when exception

	public String getSuccessJson(Object data, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", OPERATE_SUCCESS);
		map.put("data", data);
		map.put("msg", msg);
		return Json.toJson(map);
	}
	
	public String getFiledJson(Object data, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", OPERATE_FAILED);
		map.put("data", data);
		map.put("msg", msg);
		return Json.toJson(map);
	}
	
}
