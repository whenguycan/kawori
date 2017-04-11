package com.whenguycan.kawori.anime;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
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
	@Column("f_pinyin_pref")
	private String pinyinPref;

	public void setName(String name) {
		this.name = name;
		if(name != null && !name.equals("")){
			String[] pa = PinyinHelper.toHanyuPinyinStringArray(this.name.charAt(0));
			this.pinyinPref = pa!=null?pa[0].substring(0, 1):null;
		}
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
