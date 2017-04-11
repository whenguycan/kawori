package com.whenguycan.kawori.anime;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.whenguycan.kawori.common.BaseAction;
import com.whenguycan.kawori.common.IBaseService;
import com.whenguycan.kawori.common.Page;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:11:05
 */
@At("/anime")
@IocBean
public class AnimeAction extends BaseAction{
	
	@Inject
	IBaseService baseService;
	@Inject
	Dao dao;
	
	@At("/init")
	@Ok("json")
	public Object init(HttpServletRequest req){
		clear();
		insert();
		return "ok";
	}
	private void insert(){
		for(int i=0;i<22;i++){
			Anime a = new Anime();
			a.setName("anime_"+i);a.setCurr(1);a.setStatus("x");
			dao.insert(a);
			Group g = new Group();
			g.setName("group_"+i);
			dao.insert(g);
		}
	}
	private void clear(){
		dao.clear(Anime.class);
		dao.clear(Group.class);
	}
	
	@At("/anime/?")
	@Ok("jsp:/WEB-INF/page/anime.jsp")
	public void anime(int pageNo, HttpServletRequest req){
		Page<Anime> page = new Page<Anime>(pageNo);
		page = baseService.findPage(Anime.class, page, null);
		req.setAttribute("page", page);
	}
	
	@At("/group")
	@Ok("jsp:/WEB-INF/page/group.jsp")
	public void group(HttpServletRequest req){
		
	}
	
	@At("/save/anime")
	@Ok("json")
	public Object saveAnime(HttpServletRequest req, @Param("::a.")Anime anime){
		if(anime != null){
			if(anime.getId() == null){
				anime = baseService.insert(anime);
			}else{
				baseService.update(anime);
			}
		}
		return getSuccessJson(anime.getId(), "");
	}
	
	@At("/save/group")
	@Ok("json")
	public Object saveGroup(HttpServletRequest req, @Param("::g.")Group group){
		if(group != null){
			if(group.getId() == null){
				group = baseService.insert(group);
			}else{
				baseService.update(group);
			}
		}
		return getSuccessJson(group.getId(), "");
	}
}
