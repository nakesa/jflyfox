package com.flyfox.modules.web;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.article.TbArticle;

@ControllerBind(controllerKey = "/oauth2")
public class Oauth2 extends BaseController {

	private static final String path = "/pages/oauth2/";
	private static final int OAUTH_ID = 10;

	public void index() {
		TbArticle model = TbArticle.dao.findById(OAUTH_ID);
		setAttr("model", model);
		render(path + "index.html");
	}
}
