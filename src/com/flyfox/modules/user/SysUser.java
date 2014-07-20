package com.flyfox.modules.user;

import com.flyfox.jfinal.base.SessionUser;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_user", key = "userid")
public class SysUser extends SessionUser<SysUser> {

	private static final long serialVersionUID = 1L;
	public static final SysUser dao = new SysUser();

	public Integer getUserid() {
		return getInt("userid") == null ? -1 : getInt("userid");
	}
}
