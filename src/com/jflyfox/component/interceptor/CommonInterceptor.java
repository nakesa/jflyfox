package com.jflyfox.component.interceptor;

import com.jflyfox.component.util.JFlyFoxCache;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 用户认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class CommonInterceptor implements Interceptor {


	public void intercept(Invocation ai) {

		Controller controller = ai.getController();
		
		// 设置公共属性
		controller.setAttr("WEB_TITLE", JFlyFoxCache.getWebTitle());
		controller.setAttr(JFlyFoxUtils.TITLE_ATTR, JFlyFoxCache.getHeadTitle());

		ai.invoke();
	}
}
