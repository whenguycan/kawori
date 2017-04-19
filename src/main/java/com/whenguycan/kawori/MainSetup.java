package com.whenguycan.kawori;


import javax.servlet.ServletContext;

import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.whenguycan.kawori.common.Group;
import com.whenguycan.kawori.common.Season;
import com.whenguycan.kawori.common.Select;
import com.whenguycan.kawori.common.Status;

public class MainSetup implements Setup {

	public void init(NutConfig config) {
		
		ServletContext sc = config.getServletContext();
		sc.setAttribute("ctx", sc.getContextPath());

		sc.setAttribute("selectStatusSearch", Select.gen(Status.values(), true));
		sc.setAttribute("selectGroupSearch", Select.gen(Group.values(), true));
		sc.setAttribute("selectSeasonSearch", Select.gen(Season.values(), true));
		sc.setAttribute("selectStatus", Select.gen(Status.values(), false));
		sc.setAttribute("selectGroup", Select.gen(Group.values(), false));
		sc.setAttribute("selectSeason", Select.gen(Season.values(), false));
		
	}

	public void destroy(NutConfig config) {

	}

}