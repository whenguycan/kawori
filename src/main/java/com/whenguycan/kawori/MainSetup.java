package com.whenguycan.kawori;

import javax.servlet.ServletContext;

import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MainSetup implements Setup {

	public void init(NutConfig config) {
		ServletContext sc = config.getServletContext();
		sc.setAttribute("ctx", sc.getContextPath());
	}

	public void destroy(NutConfig config) {

	}

}