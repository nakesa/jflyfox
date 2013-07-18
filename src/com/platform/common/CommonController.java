package com.platform.common;

import com.jfinal.core.Controller;

public class CommonController extends Controller {

	public void index() {
		render("/login.jsp");
	}
	
	public void main() {
		render("/main.jsp");
	}
}
