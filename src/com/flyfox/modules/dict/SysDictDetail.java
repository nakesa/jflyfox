package com.flyfox.modules.dict;

import com.flyfox.base.model.BaseModel;
import com.flyfox.component.model.ModelBind;

@ModelBind(table = "sys_dict_detail", key = "detail_id")
public class SysDictDetail extends BaseModel<SysDictDetail> {

	private static final long serialVersionUID = 1L;
	public static final SysDictDetail dao = new SysDictDetail();

}
