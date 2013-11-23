package com.flyfox.component.dao;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBUtil {

	// 支持的数据库文件
	public static final String DIALECT_ORACLE = "oracle";
	public static final String DIALECT_MSSQL = "mssql";

	private DBUtil() {

	}

	public static String getSqlByPages(String sql, int pageno, int pageSize) {
		// 确定方言
		String dialect = DaoManager.getDialect();
		// 控制器，可以添加其他的数据库支持
		if (dialect.startsWith(DIALECT_ORACLE)) {// 如果是oracle的话
			return getOraUnSureMapSql(sql, pageno, pageSize);
		} else if (dialect.startsWith(DIALECT_MSSQL)) {// 如果是mssql的话
			return getMSUnSureMapSql(sql, pageno, pageSize);
		} else {
			// you's database dialect
			throw new RuntimeException("sql分页查询失败，不支持的数据类型");
		}
	}

	/**
	 * @see getUnSureMapSql
	 * @param sql
	 * @param pageno
	 * @param pageSize
	 * @param SQL
	 * @return
	 */
	private static String getMSUnSureMapSql(String sql, int pageno, int pageSize) {
		StringBuffer SQL = new StringBuffer(300);
		String orderBy = getOrderBy(sql);
		// 拼接SQL
		SQL.append("select * from ");
		SQL.append("(select a.*,ROW_NUMBER() OVER(ORDER BY ").append(orderBy)
				.append("  ) as rn from (");
		SQL.append(sql);
		SQL.append(") a ");
		SQL.append(" ) b where ").append("b.rn<=?").append(" and b.rn>?");
		return SQL.toString();
	}

	/**
	 * @see getUnSureMapSql
	 * @param sql
	 * @param pageno
	 * @param pageSize
	 * @param SQL
	 * @return
	 */
	private static String getOraUnSureMapSql(String sql, int pageno, int pageSize) {
		StringBuffer SQL = new StringBuffer(300);
		SQL.append("select * from (select a.*,rownum rn from (");
		SQL.append(sql);
		SQL.append(") a where rownum<=? ");
		SQL.append(") b where  b.rn>? ");
		return SQL.toString();
	}

	private static String getOrderBy(String sql) {
		sql = sql.toUpperCase();
		String orderBy = null;
		int n1 = sql.lastIndexOf("ORDER");
		if (n1 != -1) {
			int n2 = sql.indexOf("BY", n1) + 2;
			orderBy = sql.substring(n2).trim();
			int n3 = orderBy.indexOf(" ");
			if (n3 != -1) {
				orderBy = orderBy.substring(0, n3).trim();
			}
		} else {
			orderBy = sql.substring(sql.indexOf("SELECT") + 6, sql.indexOf("FROM")).split(",")[0];
		}
		return orderBy;
	}

	/**
	 * 按照数据库方言获取查询字符串
	 * 
	 * @param po
	 * @param SQL
	 * @param SQL_counter
	 */
	public static String getTestSql() {
		// 确定方言
		String dialect = DaoManager.getDialect();
		String sql = "";
		// 记录数sql
		// 控制器，可以添加其他的数据库支持
		if (dialect.startsWith(DIALECT_ORACLE)) {
			sql = " SELECT 1 FROM DUAL ";
		} else if (dialect.startsWith(DIALECT_MSSQL)) {
			sql = " SELECT 1 ";
		} else {
			// you's database dialect
			sql = " SELECT 1 ";
		}
		return sql;
	}

	// 查询记录时去掉order by已提升 查询速度
	public static String replaceFormatSqlOrderBy(String sql) {
		sql = sql.replaceAll("(\\s)+", " ");
		int index = sql.toLowerCase().lastIndexOf("order by");
		if (index > sql.toLowerCase().lastIndexOf(")")) {
			String sql1 = sql.substring(0, index);
			String sql2 = sql.substring(index);
			sql2 = sql2
					.replaceAll(
							"[oO][rR][dD][eE][rR] [bB][yY] [a-zA-Z0-9_.]+((\\s)+(([dD][eE][sS][cC])|([aA][sS][cC])))?(( )*,( )*[a-zA-Z0-9_.]+(( )+(([dD][eE][sS][cC])|([aA][sS][cC])))?)*",
							"");
			return sql1 + sql2;
		}
		return sql;
	}

	/**
	 * 防止sql注入
	 */
	public static String TransactSQLInjection(String str) {
		return str.replaceAll(".*([';]+|(--)+).*", " ");
	}

	public static boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}

	public static String firstToUpperCase(String s) {
		String ret = "";
		if (s != null && s.length() > 1) {
			ret = s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return ret;
	}

	/**
	 * 清除str中出现的 有str2字符序列 直到结果中再也找不出str2为止 str2 == null 返回str
	 * 
	 * @author wangp
	 * @since 2009.02.06
	 * @param str
	 *            原始字符
	 * @param str2
	 *            清除的目
	 * @return
	 */
	public static String clear(String str, String regex) {
		if (str == null || str.equals(""))
			return str;
		if (regex == null)
			return str;
		String reg = "(" + regex + ")+";
		Pattern p = Pattern.compile(reg);
		while (p.matcher(str).find()) {
			str = str.replaceAll(reg, "");
		}
		return str;
	}

	public static String getGUID() {
		return UUID.randomUUID().toString().toUpperCase();
	}

	/**
	 * 判断一个字符串是否是GUID
	 * 
	 * @param guid
	 * @return
	 */
	public static boolean isNotGUID(String guid) {
		return !isGUID(guid);
	}

	/**
	 * 判断一个字符串是否是GUID
	 * 
	 * @param guid
	 * @return
	 */
	public static boolean isGUID(String guid) {
		if (guid != null && guid.trim().length() == 36 && guid.indexOf('_') == -1) {
			String regex = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(guid);
			return matcher.find();
		}
		return false;
	}

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
	public static String getSqlIn(String sqlParam, String columnName, String sDelim) {
		if (sqlParam != null && sqlParam.length() > 0 && sDelim != null && sDelim.length() > 0) {
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
		if (str_arr != null && str_arr.length > 0 && columnName != null && columnName.length() > 0) {
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
				buffer = buffer.delete(buff_length - 1, buff_length).append(") OR");
			}
			return buffer.substring(0, buffer.length() - 2);
		} else {
			return str_arr.toString();
		}
	}

	// not in
	public static String getSqlNotIn(String[] str_arr, String columnName) {
		if (str_arr != null && str_arr.length > 0 && columnName != null && columnName.length() > 0) {
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
				buffer = buffer.delete(buff_length - 1, buff_length).append(") OR");
			}
			return buffer.substring(0, buffer.length() - 2);
		} else {
			return str_arr.toString();
		}
	}
}
