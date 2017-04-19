package com.whenguycan.kawori.common;

/**
 * 
 * @author whenguycan
 * @date 2017年4月19日 上午9:27:01
 */
public class RegexUtils {

	public static final String SEARCH_CND_REG = "S_(S|I)_(LIKE|EQ)_[0-9a-zA-Z]+";
	
	public static boolean isSearchCnd(String s){
		return s==null?false:s.matches(SEARCH_CND_REG);
	}
	
	public static String cnd2DefaultSelect(String s){
		if(isSearchCnd(s)){
			String[] x = s.split("_");
			return "-- " + x[x.length-1] + " --";
		}
		return null;
	}
	
}
