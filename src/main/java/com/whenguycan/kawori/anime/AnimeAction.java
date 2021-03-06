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
import com.whenguycan.kawori.common.IBaseService;
import com.whenguycan.kawori.common.Page;
import com.whenguycan.kawori.common.enums.Group;
import com.whenguycan.kawori.common.utils.StringUtils;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:11:05
 */
@At("/anime")
@IocBean
public class AnimeAction extends BaseAction {

	@Inject
	IBaseService baseService;
	@Inject
	AnimeService animeService;

	@At(value = { "/index/?", "/index" })
	@Ok("jsp:/WEB-INF/page/anime.jsp")
	public void anime(int pageNo, HttpServletRequest req) {
		Page<Anime> page = new Page<Anime>(pageNo).asc("f_group");
		page = baseService.findPage(Anime.class, page, Cnd.where("f_creator", "=", getLoginUser().getId()),
				getParmaMap(req));
		req.setAttribute("page", page);
	}

	@At("/upload")
	@AdaptBy(type = UploadAdaptor.class, args = { "~/uploadTmp", "8192", "UTF-8", "10" })
	public View upload(@Param("uploadFile") TempFile file, HttpServletRequest req) {
		if (file != null) {
			String uploadAbsolutePath = Mvcs.getActionContext().getServletContext().getRealPath("/upload");
			// String uploadFilePath = uploadAbsolutePath + File.separator +
			// file.getSubmittedFileName();
			String uploadFilePath = uploadAbsolutePath + File.separator + System.currentTimeMillis();
			try {
				file.write(uploadFilePath);
				BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null) {
					Anime anime = Anime.create(line);
					if (anime != null) {
						anime.setCreator(getLoginUser().getId());
						anime.generateGroup();
						anime.generateStatus();
						Anime stored = animeService.getStoredAnime(anime);
						if (stored == null) {
							baseService.insert(anime);
						} else {
							anime.setId(stored.getId());
							baseService.update(anime);
						}
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
	public Object download(HttpServletRequest req) {
		String uploadAbsolutePath = Mvcs.getActionContext().getServletContext().getRealPath("/upload");
		String uploadFilePath = uploadAbsolutePath + File.separator + "anime.txt";
		File folder = new File(uploadAbsolutePath);
		File file = new File(uploadFilePath);
		try {
			if (!folder.exists()) {
				folder.mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStream os = new FileOutputStream(file);
			StringBuilder sb = new StringBuilder();
			List<Anime> list = baseService.findList(Anime.class, null, null);
			if (list == null || list.size() == 0) {
				sb.append("NO RECORD");
			} else {
				sb.append("==========anime==========\r\n");
				sb.append("=========================\r\n");
				sb.append("\r\n");
				for (int i = 65; i <= 90; i++) {
					String x = new String(new char[] { (char) i });
					sb.append("============" + x + "============\r\n");
					Group g = Group.forCode(x);
					for (Anime anime : list) {
						if (StringUtils.equals(g.code(), anime.getGroup())) {
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
	public Object saveAnime(HttpServletRequest req, @Param("::a.") Anime anime) {
		if (anime != null) {
			anime.setCreator(getLoginUser().getId());
			if (!animeService.checkSaveUsable(anime)) {
				return getFiledJson("", "anime repeat");
			}
			if (anime.getId() == null) {
				anime = baseService.insert(anime);
			} else {
				baseService.update(anime);
			}
			return getSuccessJson(anime.getId(), "");
		}
		return getFiledJson("", "");
	}

	@At("/del/?")
	@Ok("redirect:/anime/index")
	public void del(Long id, HttpServletRequest req) {
		baseService.delete(Anime.class, id);
	}

	@At("/rebuild")
	@Ok("redirect:/anime/index")
	public void rebuild() {
		List<Anime> list = baseService.findList(Anime.class, null, null);
		for (Anime anime : list) {
			String origin = anime.getGroup();
			if (!StringUtils.equals(anime.generateGroup(), origin)) {
				baseService.update(anime);
			}
		}
	}

	@At("/clear")
	@Ok("redirect:/anime/index")
	public void clear() {
		baseService.clear(Anime.class, null);
	}

}
