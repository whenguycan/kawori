package com.whenguycan.kawori.common;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 
 * @author whenguycan
 * @date 2017年4月12日 下午4:22:05
 */
public class Select {

	private String html;
	
	private Select(String html){
		this.html = html;
	}
	
	@Override
	public String toString(){
		return this.html;
	}
	
	public static <T extends Enum<T>> Select gen(T[] values, String selectValue, String emptyName){
		String html = "";
		if(emptyName != null && !"".equals(emptyName)){
			html += "<option value='' >"+emptyName+"</option>";
		}
		for(T v : values){
			String id = v.name();
			Object name = v.name();
			if(selectValue != null && id.equals(selectValue)){
				html += "<option value='"+id+"' selected='selected'>"+name+"</option>";
			}else{
				html += "<option value='"+id+"'>"+name+"</option>";
			}
		}
		return new Select(html);
	}
	
	public static <E> Select gen(Class<E> clazz, List<E> list, String idField, String nameField, String selectValue, String emptyName){
		String html = "";
		if(emptyName != null && !"".equals(emptyName)){
			html += "<option value='' >"+emptyName+"</option>";
		}
		try {
			Field idf = clazz.getDeclaredField(idField);
			Field nmf = clazz.getDeclaredField(nameField);
			idf.setAccessible(true);
			nmf.setAccessible(true);
			for(E e : list){
				String id = idf.get(e).toString();
				Object name = nmf.get(e);
				if(selectValue != null && id.equals(selectValue)){
					html += "<option value='"+id+"' selected='selected'>"+name+"</option>";
				}else{
					html += "<option value='"+id+"'>"+name+"</option>";
				}
			}
			idf.setAccessible(false);
			nmf.setAccessible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Select(html);
	}

	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
}
