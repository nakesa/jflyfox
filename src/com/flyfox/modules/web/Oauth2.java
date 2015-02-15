package com.flyfox.modules.web;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;

@ControllerBind(controllerKey = "/oauth2")
public class Oauth2 extends BaseController {

	private static final String path = "/pages/oauth2/";

	public void index() {
		render(path + "index.html");
	}
}
