package com.platform.login;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.platform.model.Dpet;

public class LoginController extends Controller {
	
	public void index() {
		List<Dpet> list = Dpet.dao.find("select * from SY_D_DEPT");
		for (Dpet dpet : list) {
			System.out.println(dpet.getStr("ORGNAME"));
		}
		render("/login.jsp");
	}
	
	@ActionKey("/auth")
	public void auth() {
		render("login.html");
	}
}
