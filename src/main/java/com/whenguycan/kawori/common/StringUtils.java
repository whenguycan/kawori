package com.whenguycan.kawori.common;

/**
 * 
 * @author whenguycan
 * @date 2017年4月17日 上午8:31:37
 */
public class StringUtils {

	public static boolean isNotBlank(String s){
		return s==null?false:s.equals("")?false:true;
	}
	
}
