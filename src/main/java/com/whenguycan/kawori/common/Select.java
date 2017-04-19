package com.whenguycan.kawori.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author whenguycan
 * @date 2017年4月19日 上午11:09:39
 */
public class Select {
	
	private String code;
	private String text;
	
	public Select(String code, String text){
		this.code = code;
		this.text = text;
	}

	public static <E extends Enum<E>> List<Select> gen(E[] arr, boolean hasAllDefault){
		List<Select> list = new ArrayList<Select>();
		if(hasAllDefault){
			list.add(new Select("", "ALL"));
		}
		for(E e : arr){
			list.add(new Select(e.name(), e.name()));
		}
		return list;
	}
	
	public String getCode(){
		return this.code;
	}
	public String getText(){
		return this.text;
	}
	
}
