package com.whenguycan.kawori.common.utils;

/**
 * 
 * @author whenguycan
 * @date 2017年4月17日 上午8:31:37
 */
public class StringUtils {

	public static boolean isNotBlank(String s) {
		return s == null ? false : s.equals("") ? false : true;
	}
	
	public static boolean equals(String s0, String s1){
		if(s0 == null && s1 == null){
			return true;
		}
		if(s0 != null && s1 != null){
			return s0.equals(s1);
		}
		return false;
	}
	
}
