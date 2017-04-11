package com.whenguycan.kawori.common;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
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
	public <T> List<T> findList(Class<T> clazz, Cnd cnd) {
		return dao.query(clazz, null);
	}
	
	public <T> Page<T> findPage(Class<T> clazz, Page<T> page, Cnd cnd){
		if(page == null){
			page = new Page<T>();
		}
		Pager pager = dao.createPager(page.getPageNo(), page.getPageSize());
		List<T> list = dao.query(clazz, cnd, pager);
		int count = dao.count(clazz, cnd);
		page.setTotalCount(count);
		page.setResult(list);
		page.generatePager();
		return page;
	}

	@Override
	public <T> T insert(T t) {
		return dao.insert(t);
	}

	@Override
	public <T> int update(T t) {
		return dao.update(t);
	}
}
