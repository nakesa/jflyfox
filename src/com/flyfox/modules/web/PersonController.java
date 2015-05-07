package com.flyfox.modules.web;

import com.alibaba.fastjson.JSONObject;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.web.service.WebService;
import com.flyfox.system.user.SysUser;
import com.flyfox.system.user.UserCache;
import com.flyfox.util.StrUtils;

/**
 * 个人信息
 * 
 * 2015年3月10日 下午5:36:22 flyfox 330627517@qq.com
 */
@ControllerBind(controllerKey = "/web_person")
public class PersonController extends BaseController {

	/**
	 * 个人信息
	 */
	public void index() {
		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		if (user == null) {
			redirect(CommonController.firstPage);
		} else {
			setAttr("model", user);

			new WebService().showDirectory(this, "person");

			renderAuto(Home.path + "show_person.html");
		}
	}

	/**
	 * 个人信息保存
	 */
	public void save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		int userid = user.getInt("userid");
		SysUser model = getModel(SysUser.class);

		if (userid != model.getInt("userid")) {
			json.put("msg", "提交数据错误！");
			renderJson(json.toJSONString());
			return;
		}

		// 第三方用户不需要密码
		if (user.getInt("usertype") != 4) {
			String oldPassword = getPara("old_password");
			String newPassword = getPara("new_password");
			String newPassword2 = getPara("new_password2");
			if (!user.getStr("password").equals(JFlyFoxUtils.passwordEncrypt(oldPassword))) {
				json.put("msg", "密码错误！");
				renderJson(json.toJSONString());
				return;
			}
			if (StrUtils.isNotEmpty(newPassword) && !newPassword.equals(newPassword2)) {
				json.put("msg", "两次新密码不一致！");
				renderJson(json.toJSONString());
				return;
			} else if (StrUtils.isNotEmpty(newPassword)) { // 输入密码并且一直
				model.set("password", JFlyFoxUtils.passwordEncrypt(newPassword));
			}
		}

		if (StrUtils.isNotEmpty(model.getStr("email")) && model.getStr("email").indexOf("@") < 0) {
			json.put("msg", "email格式错误！");
			renderJson(json.toJSONString());
			return;
		}

		model.update();
		UserCache.init(); // 设置缓存
		SysUser newUser = SysUser.dao.findById(userid);
		setSessionUser(newUser); // 设置session
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}
}
