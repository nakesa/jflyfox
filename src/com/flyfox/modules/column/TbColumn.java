package com.flyfox.modules.column;

import com.flyfox.base.model.BaseModel;
import com.flyfox.component.model.ModelBind;

@ModelBind(table="tb_column")
public class TbColumn extends BaseModel<TbColumn> {

	private static final long serialVersionUID = 1L;
	public static final TbColumn dao = new TbColumn();

}
