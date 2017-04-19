package com.whenguycan.kawori.common;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpression;
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
	public <T> List<T> findList(Class<T> clazz, Cnd cnd, Map<String, Object> params) {
		if(params != null && params.size() != 0){
			cnd = cnd==null?Cnd.NEW():cnd;
			for(Entry<String, Object> e : params.entrySet()){
				if(e.getValue() != null && !e.getValue().toString().isEmpty()){
					cnd.and(explain(e));
				}
			}
		}
		return dao.query(clazz, cnd);
	}
	
	@Override
	public <T> Page<T> findPage(Class<T> clazz, Page<T> page, Cnd cnd, Map<String, Object> params){
		if(page == null){
			page = new Page<T>();
		}
		Pager pager = dao.createPager(page.getPageNo(), page.getPageSize());
		if(params != null && params.size() != 0){
			cnd = cnd==null?Cnd.NEW():cnd;
			for(Entry<String, Object> e : params.entrySet()){
				if(e.getValue() != null && !e.getValue().toString().isEmpty()){
					cnd.and(explain(e));
				}
			}
		}
		List<T> list = dao.query(clazz, cnd, pager);
		int count = dao.count(clazz, cnd);
		page.setTotalCount(count);
		page.setResult(list);
		return page;
	}
	private SqlExpression explain(Entry<String, Object> entry){
		String[] x = entry.getKey().split("_");
		String name = x[x.length-1];
		String op = x[x.length-2].equals("EQ")?"=":x[x.length-2];
		Object value = op.equals("LIKE")?"%"+entry.getValue()+"%":entry.getValue();
		return Cnd.exp(name, op, value);
	}
	

	@Override
	public <T> T insert(T t) {
		return dao.insert(t);
	}

	@Override
	public <T> int update(T t) {
		return dao.update(t);
	}
	
	@Override
	public <T> int delete(Class<T> clazz, Long id){
		return dao.delete(clazz, id);
	}

	@Override
	public <T> boolean checkFieldNotRepeat(Class<T> clazz, Long id, String fieldName, String fieldValue) {
		Cnd cnd = Cnd.where(fieldName, "=", fieldValue);
		if(id != null){
			cnd.and(Cnd.exp("ID", "<>", id));
		}
		int c = dao.count(clazz, cnd);
		return c==0?true:false;
	}
	
}
