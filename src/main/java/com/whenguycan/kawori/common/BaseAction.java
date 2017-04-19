package com.whenguycan.kawori.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.nutz.json.Json;
import org.nutz.mvc.Mvcs;

import com.whenguycan.kawori.anime.User;

/**
 * 
 * @author whenguycan
 * @date 2017年4月11日 下午1:15:13
 */
public class BaseAction {

	private static final boolean OPERATE_SUCCESS = true;
	private static final boolean OPERATE_FAILED = false;	//when exception

	public Object getSuccessJson(Object data, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", OPERATE_SUCCESS);
		map.put("data", data);
		map.put("msg", msg);
		return Json.toJson(map);
	}
	
	public Object getFiledJson(Object data, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", OPERATE_FAILED);
		map.put("data", data);
		map.put("msg", msg);
		return Json.toJson(map);
	}
	
	public Map<String, Object> getParmaMap(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<?, ?> m = req.getParameterMap();
		for(Entry<?, ?> entry : m.entrySet()){
			String key = (String)entry.getKey();
			String val = req.getParameter(key);
			if(RegexUtils.isSearchCnd(key) && StringUtils.isNotBlank(val)){
				map.put(key, val);
				req.setAttribute(key, val);
			}
		}
		return map;
	}
	
	public User getLoginUser(){
		String accessToken = (String)Mvcs.getHttpSession().getAttribute(LoginManager.TOKEN);
		Token token = LoginManager.getToken(accessToken);
		return token!=null?token.getLoginUser():new User();
	}
}
