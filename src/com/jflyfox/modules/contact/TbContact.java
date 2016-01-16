package com.jflyfox.modules.contact;

import com.jflyfox.jfinal.base.BaseModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_contact")
public class TbContact extends BaseModel<TbContact> {

	private static final long serialVersionUID = 1L;
	public static final TbContact dao = new TbContact();

}