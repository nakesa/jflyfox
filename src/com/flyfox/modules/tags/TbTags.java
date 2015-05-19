package com.flyfox.modules.tags;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_tags")
public class TbTags extends BaseModel<TbTags> {

	private static final long serialVersionUID = 1L;
	public static final TbTags dao = new TbTags();

}