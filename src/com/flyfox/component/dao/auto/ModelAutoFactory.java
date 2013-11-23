package com.flyfox.component.dao.auto;

public class ModelAutoFactory {

	public static void main(String[] args) {
		String packageName = "dict";
		String enc = "UTF-8";
		String[] allNames = getAllTableAndViewName(packageName);
		ModelGenerate.generateAllPO(allNames, enc, "test.flyfox.model");
	}

	public static String[] getAllTableAndViewName(String key) {
		String[] allNames = new String[0];// 逗号隔开
		if (key.equals("dict")) {
			allNames = new String[] { "T_Dict", "T_DICT_Main" };
		} else if (key.equals("xtgl")) {
			allNames = new String[] { "T_User" };
		} else if (key.equals("yggl")) {
			allNames = new String[] { "TB_YGGL_Rules", "TB_YGGL_Personal","TB_YGGL_Pact", "TB_YGGL_Lzbl" };
		} else {
			System.err.println("模块名称错误!");
		}
		return allNames;
	}
}