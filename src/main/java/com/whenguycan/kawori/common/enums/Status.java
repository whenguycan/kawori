package com.whenguycan.kawori.common.enums;

/**
 * 
 * @author whenguycan
 * @date 2017年4月12日 下午4:59:29
 */
@Enum("status")
public enum Status implements IEnum {

	_("", "-- status --"),
	
	ING("ING", "ING"), END("END", "END");

	private String code;
	private String text;
	
	public String code(){
		return this.code;
	}
	public String text() {
		return this.text;
	}

	private Status(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public static Status forCode(String code) {
		Status[] arr = Status.values();
		for (Status i : arr) {
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
