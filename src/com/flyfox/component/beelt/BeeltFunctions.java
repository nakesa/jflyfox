package com.flyfox.component.beelt;

import com.flyfox.jfinal.template.TemplateFunctions;
import com.flyfox.modules.dict.DictCache;
import com.flyfox.util.DateUtils;
import com.flyfox.util.NumberUtils;
import com.flyfox.util.StrUtils;
import com.flyfox.util.extend.HtmlUtils;

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

	// //////////////////////自定义方法///////////////////////////
	public static String getNow() {
		return DateUtils.getNow();
	}

	public static String getNow(String regex) {
		return DateUtils.getNow(regex);
	}

	/**
	 * html预览
	 * 
	 * 2015年2月2日 下午3:40:34 flyfox 330627517@qq.com
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String showHTML(String htmlStr, int num, String endStr) {
		String tmpStr = HtmlUtils.delHTMLTag(htmlStr);
		tmpStr = StrUtils.suojin(tmpStr, num + endStr.length(), endStr);
		return tmpStr;
	}

}
