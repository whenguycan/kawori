package com.whenguycan.kawori.common;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:15:50
 */
public interface IBaseService {

	public <T> List<T> findList(Class<T> clazz, Cnd cnd);
	
	public <T> Page<T> findPage(Class<T> clazz, Page<T> page, Cnd cnd, Map<String, Object> params);
	
	public <T> T insert(T t);
	
	public <T> int update(T t);
	
	public <T> int delete(Class<T> clazz, Long id);
	
	public <T> boolean checkFieldNotRepeat(Class<T> clazz, Long id, String fieldName, String fieldValue);
	
}
