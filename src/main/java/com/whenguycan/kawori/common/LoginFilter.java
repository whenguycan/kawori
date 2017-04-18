package com.whenguycan.kawori.common;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

/**
 * 
 * @author whenguycan
 * @date 2017年4月14日 下午4:04:07
 */
public class LoginFilter implements ActionFilter{

	@Override
	public View match(ActionContext actionContext) {
		String path = actionContext.getPath();
		if("/login".equals(path) || "/logout".equals(path)){
			return null;
		}
		String accessToken = (String)actionContext.getRequest().getSession().getAttribute(LoginManager.TOKEN);
		if(StringUtils.isNotBlank(accessToken)){
			Token token = LoginManager.getToken(accessToken);
			if(token != null){
				return null;
			}
		}
		return new ServerRedirectView("/login");
	}

}
