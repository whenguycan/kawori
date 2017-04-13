package com.whenguycan.kawori.anime;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 下午1:15:07
 */
@Table("t_e_group")
public class Group{

	@Id
	@Column("ID")
	private Long id;
	
	@Column("f_name")
	private String name;
	@Prev(els = {@EL("$me.genPinyinPref()")})
	@Column("f_pinyin_pref")
	private String pinyinPref;

	public void setName(String name) {
		this.name = name;
	}
	
	public String genPinyinPref(){
		String p = null;
		if(this.name != null && !this.name.equals("")){
			if(this.name.matches("[a-zA-Z_0-9]+")){
				p = this.name.substring(0, 1);
			}else{
				String[] pa = PinyinHelper.toHanyuPinyinStringArray(this.name.charAt(0));
				p = pa!=null?pa[0].substring(0, 1):null;
			}
		}
		return p;
	}

	public String getName() {
		return name;
	}
	public String getPinyinPref() {
		return pinyinPref;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
