package com.flyfox.component.beelt;

import com.flyfox.jfinal.template.TemplateFunctions;
import com.flyfox.modules.dict.DictCache;
import com.flyfox.util.NumberUtils;

public class BeeltFunctions extends TemplateFunctions {

	// //////////////////////////数据字典///////////////////////////////////////////

	public static String dictSelect(String type, int selected_value) {
		return DictCache.getSelect(type, selected_value);
	}

	public static String dictSelect(String type, String selected_value) {
		return dictSelect(type, NumberUtils.parseInt(selected_value));
	}

	public static String dictValue(int key) {
		return DictCache.getValue(key);
	}

	public static String dictValue(String key) {
		return dictValue(NumberUtils.parseInt(key));
	}

	public static String dictCode(int key) {
		return DictCache.getCode(key);
	}

	public static String dictCode(String key) {
		return dictCode(NumberUtils.parseInt(key));
	}

}
