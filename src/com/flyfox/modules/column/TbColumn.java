package com.flyfox.modules.column;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table="tb_column")
public class TbColumn extends BaseModel<TbColumn> {

	private static final long serialVersionUID = 1L;
	public static final TbColumn dao = new TbColumn();
	
	public static final int ROOT = 1;
	

}
