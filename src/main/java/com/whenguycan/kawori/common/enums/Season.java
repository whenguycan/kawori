package com.whenguycan.kawori.common.enums;

/**
 * 
 * @author whenguycan
 * @date 2017年4月12日 下午4:40:26
 */
@Enum("season")
public enum Season implements IEnum {
	
	_("", "-- season --"),

	I("I", "第1季"), II("II", "第2季"), III("III", "第3季"), IV("IV", "第4季"), V("V", "第5季"), 
	VI("VI", "第7季"), VII("VII", "第7季"), VIII("VIII", "第8季"), IX("IX", "第9季"), X("X", "第10季"),

	// 特殊的名称，以后在这加
	//TS("TS", "特殊名"),

	SP1("SP1", "特典1"), SP2("SP2", "特典2"), SP3("SP3", "特典3"), SP4("SP4", "特典4"), SP5("SP5", "特典5"),

	OVA1("OVA1", "OVA1"), OVA2("OVA2", "OVA2"), OVA3("OVA3", "OVA3"), OVA4("OVA4", "OVA4"), OVA5("OVA5", "OVA5"),

	OAD1("OAD1", "OAD1"), OAD2("OAD2", "OAD2"), OAD3("OAD3", "OAD3"), OAD4("OAD4", "OAD4"), OAD5("OAD5", "OAD5");

	private String code;
	private String text;

	public String code(){
		return this.code;
	}
	public String text() {
		return this.text;
	}

	private Season(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public static Season forCode(String code) {
		Season[] arr = Season.values();
		for (Season i : arr) {
			if (i.code().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return null;
	}
	
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}
	
}
