package com.flyfox.modules.contact;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_contact")
public class TbContact extends BaseModel<TbContact> {

	private static final long serialVersionUID = 1L;
	public static final TbContact dao = new TbContact();

}