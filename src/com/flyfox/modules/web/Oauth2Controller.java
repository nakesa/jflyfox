package com.flyfox.modules.web;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.oauth.OauthBaidu;
import com.flyfox.jfinal.component.oauth.OauthQQ;
import com.flyfox.jfinal.component.oauth.OauthSina;
import com.flyfox.jfinal.component.oauth.util.TokenUtil;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.user.SysUser;
import com.flyfox.util.StrUtils;

@ControllerBind(controllerKey = "/oauth2")
public class Oauth2Controller extends BaseController {

	private static final String path = "/pages/oauth2/";
	private static final int OAUTH_ID = 10;

	public void index() {
		TbArticle model = TbArticle.dao.findById(OAUTH_ID);
		setAttr("model", model);
		render(path + "index.html");
	}

	public void login() {
		String login_type = getPara("loin_type");
		String url = "";
		// 设置pre_page到session
		String prePage = getPara("pre_page");
		setSessionAttr("pre_page", prePage);
		
		try {
			if ("qq".equals(login_type)) {
				OauthQQ qq = OauthQQ.me();
				url = qq.getAuthorizeUrl(TokenUtil.randomState());
			} else if ("sina".equals(login_type)) {
				OauthSina sina = OauthSina.me();
				url = sina.getAuthorizeUrl(TokenUtil.randomState());
			} else if ("baidu".equals(login_type)) {
				OauthBaidu baidu = OauthBaidu.me();
				url = baidu.getAuthorizeUrl(TokenUtil.randomState());
			} else {
				redirect("admin");
				return;
			}
			redirect(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void qq_callback() {
		String code = getPara("code");
		OauthQQ baidu = OauthQQ.me();
		JSONObject json = null;
		try {
			json = baidu.getUserInfoByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		toMainPage(json);
	}

	public void sina_callback() {
		String code = getPara("code");
		OauthSina baidu = OauthSina.me();
		JSONObject json = null;
		try {
			json = baidu.getUserInfoByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		toMainPage(json);
	}

	public void baidu_callback() {
		String code = getPara("code");
		JSONObject json = null;
		OauthBaidu baidu = OauthBaidu.me();
		try {
			json = baidu.getUserInfoByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		toMainPage(json);

	}
	
	protected void toMainPage(JSONObject json) {
		if (json==null) {
			setAttr("msg", "认证解析错误，请您重新输入。");
			render(CommonController.loginPage);
			return;
		}
		
		String username = json.getString("username");
		if (username == null) {
			setAttr("msg", "认证失败，请您重新输入。");
			render(CommonController.loginPage);
			return;
		} else {
			SysUser user = new SysUser();
			user.set("username", username);
			user.set("realname", username);
			user.set("userid", Integer.MAX_VALUE);
			setSessionUser(user);
		}
		
		// 新加入，判断是否有上一个页面
		String prePage = getSessionAttr("pre_page");
		removeSessionAttr("pre_page");
		String toPage = StrUtils.isEmpty(prePage) ? CommonController.mainPage : prePage;
		
		redirect(toPage);
	}
}
