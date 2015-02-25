package com.flyfox.modules.web;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.folder.TbFolder;
import com.flyfox.modules.user.SysUser;
import com.flyfox.modules.user.UserCache;
import com.flyfox.util.StrUtils;

@ControllerBind(controllerKey = "/web")
public class Home extends BaseController {

	private static final String path = "/pages/web/";

	public void index() {
		Integer folder_id = getParaToInt();
		if (folder_id == null || folder_id <= 0) {
			folder_id = TbFolder.ROOT;
		}
		// 根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));

		// 目录列表
		List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
		setAttr("folders", folders);
		setAttr("folders_selected", folder_id);
		// 数据列表,只查询展示的和类型为11,12的
		List<TbArticle> articles = TbArticle.dao.findByWhere(" where status = 1 and type in (11,12) " //
				+ "and folder_id = ? " //
				+ "order by sort,create_time desc", folder_id);
		setAttr("list", articles);

		renderAuto(path + "home.html");

	}

	public void showArticle() {
		// 根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));
		// 数据列表
		TbArticle article = TbArticle.dao.findById(getParaToInt());
		if (article != null) {
			setAttr("item", article);
		}

		renderAuto(path + "show_article.html");

	}

	/**
	 * 登陆
	 */
	public void login() {
		setAttr("pre_page", getPrePage());
		render(CommonController.loginPage);
	}

	/**
	 * 注册
	 */
	public void regist() {
		setAttr("pre_page", getPrePage());
		render(CommonController.registPage);
	}

	/**
	 * 登出
	 */
	public void logout() {
		removeSessionUser();
		redirect(getPrePage());
	}

	public String getPrePage() {
		return getRequest().getHeader("Referer");
	}

	/**
	 * 我的消息
	 */
	public void message() {
		index();
	}

	/**
	 * 个人信息
	 */
	public void person() {
		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		if (user == null) {
			index();
		} else {
			setAttr("model", user);

			// 目录列表
			List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
			setAttr("folders", folders);
			setAttr("folders_selected", "person");

			renderAuto(path + "show_person.html");
		}
	}

	/**
	 * 个人信息保存
	 */
	public void person_save() {
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

		if (StrUtils.isNotEmpty(model.getStr("email")) && model.getStr("email").indexOf("@") < 0) {
			json.put("msg", "email格式错误！");
			renderJson(json.toJSONString());
			return;
		}

		model.update();
		SysUser newUser = SysUser.dao.findById(userid);
		UserCache.updateUser(newUser); // 设置缓存
		setSessionUser(newUser); // 设置session
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

}
