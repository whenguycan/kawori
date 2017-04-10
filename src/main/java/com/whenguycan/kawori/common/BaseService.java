package com.whenguycan.kawori.common;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:16:00
 */
@IocBean(name = "baseService", args = {"refer:dao"})
public class BaseService implements IBaseService{

	protected Dao dao;
	
	public BaseService(Dao dao){
		this.dao = dao;
	}

	@Override
	public <T extends IDEntity> List<T> findAll(Class<T> clazz) {
		return dao.query(clazz, null);
	}
	
	public <T extends IDEntity> T save(Class<T> clazz, T t){
		if(t.getId() == null){
			return dao.insert(t);
		}
		dao.update(t);
		return t;
	}
}
