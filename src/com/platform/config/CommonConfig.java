package com.platform.config;

import com.flyfox.component.common.Include;
import com.flyfox.component.common.Mapping;
import com.flyfox.component.config.Config;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.platform.common.AuthInterceptor;
import com.platform.common.CommonController;
import com.platform.login.LoginController;
import com.platform.model.Dpet;

public class CommonConfig extends JFinalConfig {

	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		String contextPath = JFinal.me().getContextPath();
		me.setBaseViewPath(contextPath);
	}

	public void configRoute(Routes me) {
		Mapping.add(me);
		
		me.add("/", CommonController.class);
		me.add("login", LoginController.class);
	}

	public void configPlugin(Plugins me) {
		me.add(new Config());
		me.add(new Include());
		
		loadPropertyFile("db.properties");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), //
				getProperty("username"), getProperty("password"), getProperty("driverClass"));// 配置C3p0数据库连接池插件
		me.add(c3p0Plugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setDialect(new OracleDialect()); // 使用Oracle
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));  
		
		me.add(arp);
		
		Mapping.add(arp);

		arp.addMapping("SY_D_USER", Dpet.class); 
		
	}

	public void configInterceptor(Interceptors me) {
		me.add(new AuthInterceptor());
	}

	public void configHandler(Handlers me) {
	}

}
