package com.whenguycan.kawori;

import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Ok("json")
@Fail("json")
@Modules(scanPackage = true)
@IocBy(type = ComboIocProvider.class, args = {
	"*json", "ioc/",
	"*anno", "com.whenguycan.kawori"
})
@SetupBy(value = MainSetup.class)
public class MainModule {

}
