package com.whenguycan.kawori.common.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.nutz.json.Json;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.resource.Scans;

import com.whenguycan.kawori.MainModule;

/**
 * 
 * @author whenguycan
 * @date 2017年4月20日 上午9:21:32
 */
public class Enums {

	public static final Map<String, Map<String, Object>> enumsMap = new HashMap();

	public static void scan(ServletContext sc) {
		scan(sc, MainModule.class);
	}

	public static void scan(ServletContext sc, Class<?> moduleClass) {
		IocBy iocBy = (IocBy) moduleClass.getAnnotation(IocBy.class);
		String[] args = iocBy.args();
		if (args != null && args.length > 3) {
			scan(sc, args[3]);
		}
	}

	public static void scan(ServletContext sc, String scanPackage) {
		List<Class<?>> domains = Scans.me().scanPackage(scanPackage);
		for (Class<?> clazz : domains) {
			Enum anno = (Enum) clazz.getAnnotation(Enum.class);
			if (anno != null) {
				Object[] fields = clazz.getEnumConstants();
				Map detailMap = new LinkedHashMap();
				for (Object field : fields) {
					if (field instanceof IEnum) {
						IEnum e = (IEnum) field;
						detailMap.put(e.code(), e.text());
					}
				}
				enumsMap.put(anno.value(), detailMap);
			}
		}
		sc.setAttribute("Enums", enumsMap);
	}
	
}
