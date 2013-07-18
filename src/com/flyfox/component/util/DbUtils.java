package com.flyfox.component.util;

public class DbUtils {

	/**
	 * 生成符合条件的sql语句,解决in问题
	 * 
	 * @param sqlParam
	 * @param columnName
	 * @return
	 */
	public static String getSqlIn(String sqlParam, String columnName) {
		if (sqlParam != null && sqlParam.length() > 0) {
			String[] str_arr = sqlParam.split(",");
			return getSqlIn(str_arr, columnName);
		} else {
			return sqlParam;
		}
	}
	// not in 
	public static String getSqlNotIn(String sqlParam, String columnName) {
		if (sqlParam != null && sqlParam.length() > 0) {
			String[] str_arr = sqlParam.split(",");
			return getSqlNotIn(str_arr, columnName);
		} else {
			return sqlParam;
		}
	}

	/**
	 * 生成符合条件的sql语句,解决in问题
	 * 
	 * @param sqlParam
	 * @param columnName
	 * @param sDelim
	 *            分隔符
	 * @return
	 */
	public static String getSqlIn(String sqlParam, String columnName,
			String sDelim) {
		if (sqlParam != null && sqlParam.length() > 0 && sDelim != null
				&& sDelim.length() > 0) {
			String[] str_arr = sqlParam.split(sDelim);
			return getSqlIn(str_arr, columnName);
		} else {
			return sqlParam;
		}
	}
	/**
	 * 生成符合条件的sql语句,解决in问题
	 * 
	 * @param sqlParam
	 * @param columnName
	 * @param sDelim
	 *            分隔符
	 * @return
	 */
	public static String getSqlIn(String[] str_arr, String columnName) {
		if (str_arr != null && str_arr.length > 0 && columnName != null
				&& columnName.length() > 0) {
			int buff_length = 0;
			int spIndex = 500;// 500 default
			int width = str_arr.length;
			int arr_width = width / spIndex;
			if (width % spIndex != 0) {
				arr_width += 1;
			}
			StringBuffer buffer = new StringBuffer("");
			for (int i = 0; i < arr_width; i++) {
				buffer.append(" " + columnName + " IN (");
				for (int j = i * spIndex, k = 0; j < width && k < spIndex; j++, k++) {
					buffer.append("'" + str_arr[j] + "',");
				}
				buff_length = buffer.length();
				buffer = buffer.delete(buff_length - 1, buff_length).append(
						") OR");
			}
			return buffer.substring(0, buffer.length() - 2);
		} else {
			return str_arr.toString();
		}
	}
	//not in
	public static String getSqlNotIn(String[] str_arr, String columnName) {
		if (str_arr != null && str_arr.length > 0 && columnName != null
				&& columnName.length() > 0) {
			int buff_length = 0;
			int spIndex = 500;// 500 default
			int width = str_arr.length;
			int arr_width = width / spIndex;
			if (width % spIndex != 0) {
				arr_width += 1;
			}
			StringBuffer buffer = new StringBuffer("");
			for (int i = 0; i < arr_width; i++) {
				buffer.append(" " + columnName + " NOT IN (");
				for (int j = i * spIndex, k = 0; j < width && k < spIndex; j++, k++) {
					buffer.append("'" + str_arr[j] + "',");
				}
				buff_length = buffer.length();
				buffer = buffer.delete(buff_length - 1, buff_length).append(
						") OR");
			}
			return buffer.substring(0, buffer.length() - 2);
		} else {
			return str_arr.toString();
		}
	}
	
}
