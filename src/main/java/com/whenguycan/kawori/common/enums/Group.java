package com.whenguycan.kawori.common.enums;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 
 * @author whenguycan
 * @date 2017年4月18日 下午3:36:31
 */
@Enum("group")
public enum Group implements IEnum {

	_("", "-- group --"),
	
	A("A", "A"), B("B", "B"), C("C", "C"), D("D", "D"), E("E", "E"), 
	F("F", "F"), G("G", "G"), H("H", "H"), I("I", "I"), J("J", "J"), 
	K("K", "K"), L("L", "L"), M("M", "M"), N("N", "N"), O("O", "O"), 
	P("P", "P"), Q("Q", "Q"), R("R", "R"), S("S", "S"), T("T", "T"), 
	U("U", "U"), V("V", "V"), W("W", "W"), X("X", "X"), Y("Y", "Y"), 
	Z("Z", "Z");

	private String code;
	private String text;

	public String code(){
		return this.code;
	}
	public String text() {
		return this.text;
	}

	private Group(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public static Group forCode(String code) {
		Group[] arr = Group.values();
		for (Group i : arr) {
			if (i.code().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return null;
	}

	public static String generateGroup(String name) {
		if (name == null) {
			return null;
		}
		char c = name.charAt(0);
		char[] ca = { c };
		String head = new String(ca);
		Group g = null;
		if (head.matches("[\u4e00-\u9fa5]")) {
			String[] arr = PinyinHelper.toHanyuPinyinStringArray(c);
			g = forCode(arr[0].substring(0, 1));
		} else {
			g = forCode(head);
		}
		return g!=null?g.code():null;
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
