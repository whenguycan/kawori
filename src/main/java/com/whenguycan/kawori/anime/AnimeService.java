package com.whenguycan.kawori.anime;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

import com.whenguycan.kawori.common.BaseService;

/**
 * 
 * @author whenguycan
 * @date 2017年4月18日 下午1:20:17
 */
@IocBean(name = "animeService", args = {"refer:dao"})
public class AnimeService extends BaseService{

	protected Dao dao;
	
	public AnimeService(Dao dao){
		super(dao);
	}
	
	/**
	 * 查询anime是否可以保存（新增、修改专用）
	 * @return
	 */
	public boolean checkSaveUsable(Anime anime){
		Anime s = getStoredAnime(anime);
		return s==null?true:false;
	}
	
	/**
	 * 查询相同的anime（name，season，creator相同）（批量导入专用）
	 * @return
	 */
	public Anime getStoredAnime(Anime anime){
		if(anime == null){
			return null;
		}
		Cnd cnd = Cnd.where("f_name", "=", anime.getName());
		if(anime.getSeason() != null){
			cnd = cnd.and("f_season", "=", anime.getSeason().name());
		}
		if(anime.getCreator() != null){
			cnd = cnd.and("f_creator", "=", anime.getCreator());
		}
		if(anime.getId() != null){
			cnd = cnd.and("ID", "<>", anime.getId());
		}
		List<Anime> list = super.findList(Anime.class, cnd, null);
		return list!=null&&list.size()!=0?list.get(0):null;
	}
	
	@Override
	public <T> int update(T t) {
		Anime anime = (Anime)t;
		anime.setGroup(anime.generateGroup());
		return super.update(anime);
	}
}
