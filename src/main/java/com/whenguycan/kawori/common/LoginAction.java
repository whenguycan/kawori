package com.whenguycan.kawori.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

import com.whenguycan.kawori.anime.User;
import com.whenguycan.kawori.common.utils.Encrypt;
import com.whenguycan.kawori.common.utils.StringUtils;

/**
 * 
 * @author whenguycan
 * @date 2017年4月14日 下午4:13:55
 */
@IocBean
public class LoginAction extends BaseAction {

	@Inject
	private IBaseService baseService;

	@At("/login")
	public View login(HttpServletRequest req) {
		String username = req.getParameter("u.username");
		String password = req.getParameter("u.password");
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			List<User> uList = baseService.findList(User.class, Cnd.where("f_username", "=", username), null);
			if (uList != null && uList.size() != 0) {
				User user = uList.get(0);
				if (Encrypt.md5PasswordCheck(password, user.getPassword())) {
					String accessToken = LoginManager.login(user);
					req.getSession().setAttribute(LoginManager.TOKEN, accessToken);
					return new ServerRedirectView("/anime/index");
				}
			}
		} else {
			String accessToken = (String) req.getSession().getAttribute(LoginManager.TOKEN);
			if (StringUtils.isNotBlank(accessToken)) {
				Token token = LoginManager.getToken(accessToken);
				if (token != null) {
					return new ServerRedirectView("/anime/index");
				}
			}
		}
		return new JspView("page/login");
	}

	@At("/logout")
	public View logout(HttpServletRequest req) {
		String accessToken = (String) req.getSession().getAttribute(LoginManager.TOKEN);
		if (StringUtils.isNotBlank(accessToken)) {
			LoginManager.logOut(accessToken.toString());
		}
		return new JspView("page/login");
	}
}
