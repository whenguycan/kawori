package com.whenguycan.kawori.common.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author whenguycan
 * @date 2017年4月20日 上午9:06:00
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {

	public abstract String value();

}
