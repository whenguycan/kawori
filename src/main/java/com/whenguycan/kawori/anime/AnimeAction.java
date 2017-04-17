package com.whenguycan.kawori.anime;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.whenguycan.kawori.common.BaseAction;
import com.whenguycan.kawori.common.IBaseService;
import com.whenguycan.kawori.common.Page;
import com.whenguycan.kawori.common.Search;
import com.whenguycan.kawori.common.Season;
import com.whenguycan.kawori.common.Select;
import com.whenguycan.kawori.common.Status;

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
	
	@At(value = {"/index/?", "/index"})
	@Ok("jsp:/WEB-INF/page/anime.jsp")
	public void anime(int pageNo, @Param("::s.")Search s, HttpServletRequest req){
		Page<Anime> page = new Page<Anime>(pageNo);
		page = baseService.findPage(Anime.class, page, null, getParmas(s));
		req.setAttribute("page", page);
		req.setAttribute("s", s);
		req.setAttribute("selectStatusSearch", Select.gen(Status.values(), s!=null?s.getEQ_status():null, "--choose status--"));
		req.setAttribute("selectStatusSave", Select.gen(Status.values(), s!=null?s.getEQ_status():null, null));
		req.setAttribute("selectSeason", Select.gen(Season.values(), "", null));
	}
	
	@At("/save")
	@Ok("json")
	public Object saveAnime(HttpServletRequest req, @Param("::a.")Anime anime){
		if(anime != null){
			if(!baseService.checkFieldNotRepeat(Anime.class, anime.getId(), "f_name", anime.getName())){
				return getFiledJson("", "name repeat");
			}
			if(anime.getId() == null){
				anime = baseService.insert(anime);
			}else{
				baseService.update(anime);
			}
			return getSuccessJson(anime.getId(), "");
		}
		return getFiledJson("", "");
	}
	
	@At("/del/?")
	@Ok("redirect:/anime/index")
	public void del(Long id, HttpServletRequest req){
		baseService.delete(Anime.class, id);
	}
}
