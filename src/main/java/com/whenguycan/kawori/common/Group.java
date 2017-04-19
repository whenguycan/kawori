package com.whenguycan.kawori.common;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 
 * @author whenguycan
 * @date 2017年4月18日 下午3:36:31
 */
public enum Group {

	A,B,C,D,E,F,G,
	H,I,J,K,L,M,N,
	O,P,Q,R,S,T,U,
	V,W,X,Y,Z;

	public static Group forName(String name){
		Group[] arr = Group.values();
		for(Group i : arr){
			if(i.name().equalsIgnoreCase(name)){
				return i;
			}
		}
		return null;
	}
	
	public static Group generateGroup(String name){
		if(name == null){
			return null;
		}
		char c = name.charAt(0);
		char[] ca = {c};
		String head = new String(ca);
		if(head.matches("[\u4e00-\u9fa5]")){
			String[] arr = PinyinHelper.toHanyuPinyinStringArray(c);
			return forName(arr[0].substring(0, 1));
		}else{
			return forName(head);
		}
	}
	
}
