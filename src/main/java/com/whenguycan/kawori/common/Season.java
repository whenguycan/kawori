package com.whenguycan.kawori.common;

/**
 * 
 * @author whenguycan
 * @date 2017年4月12日 下午4:40:26
 */
public enum Season {
	
	I,II,III,IV,V,VI,VII,VIII,IX,X,
	
	SP1,SP2,SP3,SP4,SP5,
	
	OVA1,OVA2,OVA3,OVA4,OVA5,
	
	OAD1,OAD2,OAD3,OAD4,OAD5;
	
	public static Season forName(String sn){
		Season[] sa = Season.values();
		for(Season s : sa){
			if(s.name().equalsIgnoreCase(sn)){
				return s;
			}
		}
		return null;
	}
}
