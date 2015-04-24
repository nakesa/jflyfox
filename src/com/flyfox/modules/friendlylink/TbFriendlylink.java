package com.flyfox.modules.friendlylink;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_friendlylink")
public class TbFriendlylink extends BaseModel<TbFriendlylink> {

	private static final long serialVersionUID = 1L;
	public static final TbFriendlylink dao = new TbFriendlylink();

}