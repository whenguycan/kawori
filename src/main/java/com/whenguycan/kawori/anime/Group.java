package com.whenguycan.kawori.anime;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.whenguycan.kawori.common.IDEntity;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 下午1:15:07
 */
@Table("t_e_group")
public class Group extends IDEntity{

	@Column("f_name")
	private String name;
	@Column("f_pinyin_pref")
	private String pinyinPref;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		String[] pa = PinyinHelper.toHanyuPinyinStringArray(this.name.charAt(0));
		this.pinyinPref = pa!=null?pa[0].substring(0, 1):null;
	}
	
}
