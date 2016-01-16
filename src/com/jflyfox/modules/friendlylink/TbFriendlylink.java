package com.jflyfox.modules.friendlylink;

import com.jflyfox.jfinal.base.BaseModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_friendlylink")
public class TbFriendlylink extends BaseModel<TbFriendlylink> {

	private static final long serialVersionUID = 1L;
	public static final TbFriendlylink dao = new TbFriendlylink();

}