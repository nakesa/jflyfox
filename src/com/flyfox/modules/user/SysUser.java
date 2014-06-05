package com.flyfox.modules.user;

import com.flyfox.base.model.BaseModel;
import com.flyfox.component.model.ModelBind;

@ModelBind(table = "sys_user", key = "userid")
public class SysUser extends BaseModel<SysUser> {

	private static final long serialVersionUID = 1L;
	public static final SysUser dao = new SysUser();

	public Integer getUserid() {
		return getInt("userid") == null ? -1 : getInt("userid");
	}
}
