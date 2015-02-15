package com.flyfox.modules.user;

import javax.servlet.http.HttpServletRequest;

import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.util.StrUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.log.Logger;

/**
 * 用户认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class UserInterceptor implements Interceptor {

	private static final Logger log = Logger.getLogger(UserInterceptor.class);

	public void intercept(ActionInvocation ai) {

		Controller controller = ai.getController();

		HttpServletRequest request = controller.getRequest();
		String referrer = request.getHeader("referer");
		String site = "http://" + request.getServerName();
		log.debug("####IP:" + request.getRemoteAddr() + "\t port:" + request.getRemotePort() + "\t 请求路径:"
				+ request.getRequestURI());
		if (referrer == null || !referrer.startsWith(site)) {
			log.warn("####非法的请求");
		}

		String path_tmp = ai.getActionKey();

		if (path_tmp.startsWith("/")) {
			path_tmp = path_tmp.substring(1, path_tmp.length());
		}
		if (path_tmp.endsWith("/")) {
			path_tmp = path_tmp.substring(0, path_tmp.length() - 1);
		}

		if (StrUtils.isNotEmpty(path_tmp) //
				&& path_tmp.indexOf("login") < 0 // 登录
				&& !path_tmp.startsWith("admin") // 登录
				&& !path_tmp.endsWith("trans") // 过期
				&& !path_tmp.endsWith("logout") // 登出
				&& !path_tmp.startsWith("web") // 首页
				&& !path_tmp.startsWith("oauth2") // oauth2认证
		) {
			if (JFinal.me().getConstants().getDevMode()) {
				SysUser user = SysUser.dao.findFirst("select * from sys_user where userid = 1");
				controller.setSessionAttr(Attr.SESSION_NAME, user);
			} else {
				SysUser user = controller.getSessionAttr(Attr.SESSION_NAME);
				if (user == null || user.getUserid() <= 0) {
					controller.redirect("/trans");
					return;
				}
			}
		}

		ai.invoke();
	}
}
