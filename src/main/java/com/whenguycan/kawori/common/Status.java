package com.whenguycan.kawori.common;

/**
 * 
 * @author whenguycan
 * @date 2017年4月12日 下午4:59:29
 */
public enum Status {

	ING, END;
	
	public static Status forName(String name){
		Status[] arr = Status.values();
		for(Status i : arr){
			if(i.name().equalsIgnoreCase(name)){
				return i;
			}
		}
		return null;
	}
	
}
