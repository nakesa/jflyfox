package com.flyfox.modules.comment;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_comment")
public class TbComment extends BaseModel<TbComment> {
	private static final long serialVersionUID = 1L;

	public static final TbComment dao = new TbComment();
}
