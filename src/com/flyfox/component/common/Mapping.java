package com.flyfox.component.common;

import com.flyfox.component.dict.Dict;
import com.flyfox.component.dict.DictController;
import com.flyfox.component.dict.DictMain;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * 组件初始化
 * 
 * @author flyfox
 * @since Jul 14, 2013
 * 
 */
public class Mapping {

	public static void add(Routes me) {
		me.add("dict", DictController.class);
	}

	public static void add(ActiveRecordPlugin arp) {
		arp.addMapping("T_DICT", "dict_id", Dict.class);
		arp.addMapping("T_DICT_MAIN", "dict_type", DictMain.class);
	}

}
