package com.whenguycan.kawori.anime;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.whenguycan.kawori.common.IBaseService;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:11:05
 */
@At("/anime")
@IocBean
public class AnimeAction {
	
	@Inject
	IBaseService baseService;
	
	@At("/init")
	@Ok("json")
	public Object init(HttpServletRequest req){
		for(int i=0;i<12;i++){
			Anime a = new Anime();
			a.setName("anime_"+i);a.setCurr(1);a.setStatus("x");
			baseService.save(Anime.class, a);
			Group g = new Group();
			g.setName("group_"+i);
			baseService.save(Group.class, g);
		}
		return "ok";
	}
	
	@At("/index")
	@Ok("jsp:/WEB-INF/page/index.jsp")
	public void index(HttpServletRequest req){
		List<Anime> animes = baseService.findAll(Anime.class);
		req.setAttribute("animes", animes);
		List<Group> groups = baseService.findAll(Group.class);
		req.setAttribute("groups", groups);
	}
	
	@At("/save/anime")
	@Ok("jsp:/WEB-INF/page/index.jsp")
	public void saveAnime(String type, @Param("::a.")Anime anime, HttpServletRequest req){
		if(anime != null){
			baseService.save(Anime.class, anime);
		}
		index(req);
	}
	
	@At("/save/group")
	@Ok("jsp:/WEB-INF/page/index.jsp")
	public void saveGroup(String type, @Param("::g.")Group group, HttpServletRequest req){
		if(group != null){
			baseService.save(Group.class, group);
		}
		index(req);
	}
}
