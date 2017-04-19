package com.whenguycan.kawori.common;

/**
 * 
 * @author whenguycan
 * @date 2017年4月12日 下午4:40:26
 */
public enum Season{
	
	I(""),II(""),III(""),IV(""),V(""),VI(""),VII(""),VIII(""),IX(""),X(""),
	
	TD("特典"),
	
	SP1(""),SP2(""),SP3(""),SP4(""),SP5(""),
	
	OVA1(""),OVA2(""),OVA3(""),OVA4(""),OVA5(""),
	
	OAD1(""),OAD2(""),OAD3(""),OAD4(""),OAD5("");
	
	private String text;
	
	private Season(String text){
		this.text = text;
	}
	
	public String text(){
		return this.text;
	}
	
	public static Season forName(String name){
		Season[] arr = Season.values();
		for(Season i : arr){
			if(i.name().equalsIgnoreCase(name)){
				return i;
			}
		}
		return null;
	}
}
