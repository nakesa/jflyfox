package com.flyfox.modules.dict;

import com.flyfox.base.model.BaseModel;
import com.flyfox.component.model.ModelBind;
import com.jfinal.plugin.activerecord.TableInfo;
import com.jfinal.plugin.activerecord.TableInfoMapping;

@ModelBind(table = "sys_dict", key = "dict_id")
public class SysDict extends BaseModel<SysDict> {

	private static final long serialVersionUID = 1L;
	public static final SysDict dao = new SysDict();

	public TableInfo getTableInfo() {
		return TableInfoMapping.me().getTableInfo(getClass());
	}

}
