package com.jflyfox.modules.comment;

import com.jflyfox.jfinal.base.BaseModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_comment")
public class TbComment extends BaseModel<TbComment> {
	private static final long serialVersionUID = 1L;

	public static final TbComment dao = new TbComment();
}
