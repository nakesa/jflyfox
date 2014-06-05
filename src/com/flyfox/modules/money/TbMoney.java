package com.flyfox.modules.money;

import com.flyfox.base.model.BaseModel;
import com.flyfox.component.model.ModelBind;

@ModelBind(table="tb_money")
public class TbMoney extends BaseModel<TbMoney> {

	private static final long serialVersionUID = 1L;
	public static final TbMoney dao = new TbMoney();

}
