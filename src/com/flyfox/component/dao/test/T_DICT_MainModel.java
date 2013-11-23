package com.flyfox.component.dao.test;

import com.flyfox.component.dao.model.Model;

import java.util.HashMap;
import java.util.Map;

public class T_DICT_MainModel extends Model {

	public T_DICT_MainModel() {
	}

	public T_DICT_MainModel(String param) {
		 set("dict_type",param);
	}

	private static final long serialVersionUID = 1L;

	/**
	 * 表信息
	 */
	protected static final Map<String, String> tableInfo = new HashMap<String, String>();

	/**
	 * 属性
	 */
	protected static final Map<String, Class<?>> attrs = new HashMap<String, Class<?>>();

	public Map<String, String> getTableInfo() {
		return tableInfo;
	}

	public Map<String, Class<?>> getAttrs() {
		return attrs;
	}

	static {
		tableInfo.put(PRIMARY_KEY, "dict_type"); /* 主键 */
		tableInfo.put(TABLE_NAME, "T_DICT_MAIN"); /* 表名 */
		attrs.put("dict_type", String.class);
		attrs.put("dict_name", String.class);
		attrs.put("is_delete", Integer.class);
	}

	public T_DICT_MainModel getMe() {
		return new T_DICT_MainModel() ;
	}

	public java.lang.String getDict_type() {
		return getString("dict_type");
	}

	public void setDict_type(java.lang.String dict_type) {
		set("dict_type",dict_type);
	}

	public java.lang.String getDict_name() {
		return getString("dict_name");
	}

	public void setDict_name(java.lang.String dict_name) {
		set("dict_name",dict_name);
	}

	public int getIs_delete() {
		return getInteger("is_delete");
	}

	public void setIs_delete(int is_delete) {
		set("is_delete",is_delete);
	}

}