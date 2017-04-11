package com.whenguycan.kawori.common;

import java.util.List;

import org.nutz.dao.Cnd;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:15:50
 */
public interface IBaseService {

	public <T> List<T> findList(Class<T> clazz, Cnd cnd);
	
	public <T> Page<T> findPage(Class<T> clazz, Page<T> page, Cnd cnd);
	
	public <T> T insert(T t);
	
	public <T> int update(T t);
	
}
