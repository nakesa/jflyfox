package com.flyfox.modules;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.dict.DictCache;
import com.flyfox.modules.user.SysUser;
import com.flyfox.modules.user.UserCache;
import com.flyfox.util.Config;
import com.flyfox.util.StrUtils;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "/")
public class CommonController extends BaseController {

	private String loginPage = "/login.html";

	public void index() {
		redirect("/web");
	}

	public void admin() {
		if (getSessionUser() != null) {
			// 如果session存在，不再验证
			redirect("/contact/list");
		} else {
			render(loginPage);
		}

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
			render(loginPage);
			return;
		} else if (StrUtils.isEmpty(password)) {
			setAttr("msg", "密码不能为空");
			render(loginPage);
			return;
		}
		SysUser user = SysUser.dao.findFirstByWhere(" where username = ? and password = ? ", username, password);
		if (user == null || user.getInt("userid") <= 0) {
			setAttr("msg", "认证失败，请您重新输入。");
			render(loginPage);
			return;
		} else {
			setSessionUser(user);
		}
		redirect("/contact/list");
	}

	/**
	 * 登出
	 * 
	 * @author flyfox 2013-11-13
	 */
	public void logout() {
		removeSessionUser();
		setAttr("msg", "您已退出");
		render(loginPage);
	}

	public void update_cache() {
		DictCache.init();
		UserCache.init();
		renderHtml("1");
	}
	
	public void trans(){
		String redirectPath =  Config.getStr("PAGES.TRANS");
		render(redirectPath);
	}
}
