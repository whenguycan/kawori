package com.whenguycan.kawori.anime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;
import org.nutz.mvc.view.ServerRedirectView;

import com.whenguycan.kawori.common.BaseAction;
import com.whenguycan.kawori.common.Group;
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
	@Inject
	AnimeService animeService;
	
	@At(value = {"/index/?", "/index"})
	@Ok("jsp:/WEB-INF/page/anime.jsp")
	public void anime(int pageNo, @Param("::s.")Search s, HttpServletRequest req){
		Page<Anime> page = new Page<Anime>(pageNo);
		page = baseService.findPage(Anime.class, page, Cnd.where("f_creator", "=", super.getLoginUser().getId()), getParmas(s));
		req.setAttribute("page", page);
		req.setAttribute("s", s); 
		req.setAttribute("selectStatusSearch", Select.gen(Status.values(), s!=null?s.getEQ_status():null, "-- status --"));
		req.setAttribute("selectGroupSearch", Select.gen(Group.values(), s!=null?s.getEQ_group():null, "-- group --"));
		req.setAttribute("selectGroup", Select.gen(Group.values(), "", null));
		req.setAttribute("selectStatus", Select.gen(Status.values(), "", null));
		req.setAttribute("selectSeason", Select.gen(Season.values(), "", null));
	}
	
	@At("/upload")
	@AdaptBy(type = UploadAdaptor.class, args = { "~/uploadTmp", "8192", "UTF-8", "10" })  
	public View upload(@Param("uploadFile")TempFile file, HttpServletRequest req){
		if(file != null){
			String uploadAbsolutePath = Mvcs.getActionContext().getServletContext().getRealPath("/upload");
//			String uploadFilePath = uploadAbsolutePath + File.separator + file.getSubmittedFileName();
			String uploadFilePath = uploadAbsolutePath + File.separator + System.currentTimeMillis();
			try {
				File folder = new File(uploadAbsolutePath);
				System.out.println(folder.exists());
				if(!folder.exists()){
					folder.mkdir();
				}
				file.write(uploadFilePath);
				BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String line = "";
				while((line = reader.readLine()) != null){
					Anime anime = new Anime(line.split("-+"));
					anime.setCreator(super.getLoginUser().getId());
					Anime stored = animeService.getStoredAnime(anime);
					if(stored == null){
						baseService.insert(anime);
					}else{
						stored.setCurr(anime.getCurr());
						stored.setAll(anime.getAll());
						animeService.update(stored);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ServerRedirectView("/anime/index");
	}
	
	@At("/download")
	@Ok("raw:stream")
	public Object download(HttpServletRequest req){
		String uploadAbsolutePath = Mvcs.getActionContext().getServletContext().getRealPath("/upload");
		String uploadFilePath = uploadAbsolutePath + File.separator + "anime.txt";
		File folder = new File(uploadAbsolutePath);
		File file = new File(uploadFilePath);
		try {
			if(!folder.exists()){
				folder.mkdir();
			}
			if(!file.exists()){
				file.createNewFile();
			}
			OutputStream os = new FileOutputStream(file);
			StringBuilder sb = new StringBuilder();
			List<Anime> list = baseService.findList(Anime.class, null, null);
			if(list == null || list.size() == 0){
				sb.append("NO RECORD");
			}else{
				sb.append("==========anime==========\r\n");
				sb.append("=========================\r\n");
				sb.append("\r\n");
				for(int i=65;i<=90;i++){
					String x = new String(new char[]{(char)i});
					sb.append("============"+x+"============\r\n");
					Group g = Group.forName(x);
					for(Anime anime : list){
						if(g.name().equals(anime.getGroup().name())){
							sb.append(anime.getName());
							sb.append("(");
							sb.append(anime.getAll());
							sb.append("/");
							sb.append(anime.getSeason());
							sb.append(")");
							sb.append("--------");
							sb.append(anime.getCurr()); 
							sb.append("\r\n");
						}
					}
					sb.append("\r\n");
				}
			}
			os.write(sb.toString().getBytes());
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
	
	@At("/save")
	@Ok("json")
	public Object saveAnime(HttpServletRequest req, @Param("::a.")Anime anime){
		if(anime != null){
			anime.setCreator(super.getLoginUser().getId());
			if(!animeService.checkSaveUsable(anime)){
				return getFiledJson("", "anime repeat");
			}
			if(anime.getId() == null){
				anime = baseService.insert(anime);
			}else{
				animeService.update(anime);
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
