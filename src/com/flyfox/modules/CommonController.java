package com.flyfox.modules;

import com.flyfox.base.controller.BaseController;
import com.flyfox.component.route.ControllerBind;
import com.flyfox.modules.column.ColumnCache;
import com.flyfox.modules.dict.DictCache;
import com.flyfox.modules.user.SysUser;
import com.flyfox.modules.user.UserCache;
import com.flyfox.util.StrUtils;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "/")
public class CommonController extends BaseController {

	public void index() {
		redirect("/web");
	}

	public void admin() {
		render("/login.jsp");
	}

	/**
	 * 登陆
	 * 
	 * @author flyfox 2013-11-11
	 */
	public void login() {
		// 初始化数据字典Map
		String username = getPara("username");
		String password = getPara("password");
		if (StrUtils.isEmpty(username)) {
			setAttr("msg", "用户名不能为空");
			render("/login.jsp");
			return;
		} else if (StrUtils.isEmpty(password)) {
			setAttr("msg", "密码不能为空");
			render("/login.jsp");
			return;
		}
		SysUser user = SysUser.dao.findFirstByWhere(" where username = ? and password = ? ", username, password);
		if (user == null || user.getInt("userid") <= 0) {
			setAttr("msg", "认证失败，请您重新输入。");
			render("/login.jsp");
			return;
		} else {
			setSessionUser(user);
		}
		redirect("/money/list");
	}

	/**
	 * 登出
	 * 
	 * @author flyfox 2013-11-13
	 */
	public void logout() {
		removeSessionUser();
		setAttr("msg", "您已退出");
		render("/login.jsp");
	}

	public void update_cache() {
		DictCache.init();
		UserCache.init();
		ColumnCache.init();
		renderHtml("1");
	}
}
