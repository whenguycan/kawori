var ioc = {
	dataSource : {
		type : "com.mchange.v2.c3p0.ComboPooledDataSource",
		fields : {
			driverClass : "com.mysql.jdbc.Driver",
			jdbcUrl : "jdbc:mysql://127.0.0.1:3306/anime?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
			user : "wang",
			password : "123456",
			initialPoolSize : 5,
			minPoolSize : 10, 
			maxPoolSize : 30,
			maxIdleTime : 120,
			maxConnectionAge : 180,
			maxStatements : 20,
			acquireIncrement : 5,
			acquireRetryAttempts : 30,
			acquireRetryDelay : 1000,
			checkoutTimeout : 15000, 
			numHelperThreads : 2,
			testConnectionOnCheckin : true
		},
		events : {
			depose : 'close'
		}
	},
	dao : {
		type : "org.nutz.dao.impl.NutDao",
		args : [{refer : "dataSource"}]
	}
};