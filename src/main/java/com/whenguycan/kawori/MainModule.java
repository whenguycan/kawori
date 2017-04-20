package com.whenguycan.kawori;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.whenguycan.kawori.common.LoginFilter;

@Ok("json")
@Fail("json")
@Modules(scanPackage = true)
@Filters(value = { @By(type = LoginFilter.class) })
@IocBy(type = ComboIocProvider.class, args = { "*json", "ioc/", "*anno", "com.whenguycan.kawori" })
@SetupBy(value = MainSetup.class)
public class MainModule {

}