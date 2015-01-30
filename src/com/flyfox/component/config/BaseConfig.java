package com.flyfox.component.config;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.beetl.functions.BeetlStrUtils;
import com.flyfox.component.beelt.BeeltFunctions;
import com.flyfox.jfinal.config.JflyfoxConfig;
import com.flyfox.jfinal.template.CRUDFactory;
import com.flyfox.jfinal.template.TemplateUtils;
import com.flyfox.modules.dict.DictCache;
import com.flyfox.modules.user.UserCache;
import com.flyfox.modules.user.UserInterceptor;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;

/**
 * API引导式配置
 */
public class BaseConfig extends JflyfoxConfig {

	public void configConstant(com.jfinal.config.Constants me) {
		super.configConstant(me);
		me.setMainRenderFactory(new BeetlRenderFactory());
		// 获取GroupTemplate ,可以设置共享变量等操作
		GroupTemplate groupTemplate = BeetlRenderFactory.groupTemplate;
		groupTemplate.registerFunctionPackage("strutil", BeetlStrUtils.class);
		groupTemplate.registerFunctionPackage("flyfox", BeeltFunctions.class);
		
		TemplateUtils.groupTemplate.registerFunctionPackage("strutil", BeetlStrUtils.class);
		TemplateUtils.groupTemplate.registerFunctionPackage("flyfox", BeeltFunctions.class);
	};

	@Override
	public void configHandler(Handlers me) {
		// Beelt
		// me.add(new BeeltHandler());
		super.configHandler(me);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		// 用户认证
		me.add(new UserInterceptor());
		// session model转换
		super.configInterceptor(me);
	}

	/**
	 * 初始化
	 */
	@Override
	public void afterJFinalStart() {
		reset();
		// 读取配置文件
		CRUDFactory.instance().clear();
		CRUDFactory.instance().init();
		// 类注入
		// TableConfig.init();
	}

	public static void reset() {
		DictCache.init();
		UserCache.init();
	}

}
