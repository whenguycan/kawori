package com.whenguycan.kawori.common;

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
	
}
