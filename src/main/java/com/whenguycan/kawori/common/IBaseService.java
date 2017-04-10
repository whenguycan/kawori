package com.whenguycan.kawori.common;

import java.util.List;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:15:50
 */
public interface IBaseService {

	public <T extends IDEntity> List<T> findAll(Class<T> clazz);
	
	public <T extends IDEntity> T save(Class<T> clazz, T t);
	
}
