package com.flyfox.component.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flyfox.util.Constants;
import com.flyfox.util.StrUtils;

public class Trans extends Dao {

	/**
	 * 根据序列名称查询序列当前最新值
	 * 
	 * @param sequenceName
	 * @return int
	 */
	public int getValueBySequence(String sequenceName) {
		String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
		return Integer.valueOf(getValue(sql)).intValue();
	}

	/**
	 * 关于数字字段可以调用此方法获得最大值的下一个值
	 * 
	 * @param columnName
	 * @param tableName
	 * @return int
	 */
	public int getValueByMaxInt(String columnName, String tableName) {
		String sql = "SELECT MAX(" + columnName + ") FROM " + tableName;
		String pkvalue = getValue(sql);
		return Integer.valueOf((pkvalue == null || pkvalue.length() <= 0) ? "0" : pkvalue) + 1;
	}

	/**
	 * @author flyfox
	 * @param sql
	 * @return 返回不确定列的一个Map 集合
	 */
	public List<Map<String, String>> getList(String sql) {
		return getList(sql, 0, 0, "normal");
	}

	/**
	 * @author flyfox
	 * @param sql
	 * @return 返回不确定列的一个Map 集合
	 */
	public Map<String, Map<String, String>> getMap(String sql) {
		return getMap(sql, 0, 0, "normal");
	}

	/**
	 * @author flyfox
	 * @return
	 * @param sql
	 * @param pageno
	 * @param pageSize
	 * @param type
	 *            key类型转换 normal 正常 upper 转大写 lower 转小写
	 * @return 返回不确定列的一个PO集合Result
	 */
	public Map<String, Map<String, String>> getMap(String sql, int pageno, int pageSize,
			String type, Object... para) {
		Map<String, Map<String, String>> mapMap = new HashMap<String, Map<String, String>>();

		try {
			ResultSet rs = getRsByPages(sql, pageno, pageSize, para);

			if (rs.next()) {
				ResultSetMetaData rm = rs.getMetaData();
				int count = rm.getColumnCount();
				do {
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0; i < count; i++) {
						String tmp = rm.getColumnName(i + 1);
						String key = getValueByType(type, tmp);
						map.put(key, rs.getString(tmp));
					}
					mapMap.put(rs.getString(1), map);
				} while (rs.next());
			}
		} catch (Exception e) {
			printError(e, sql);
		} finally {
			closeAll();
		}
		return mapMap;
	}

	/**
	 * @author flyfox
	 * @return
	 * @param sql
	 * @param pageno
	 * @param pageSize
	 * @param type
	 *            key类型转换 normal 正常 upper 转大写 lower 转小写
	 * @return 返回不确定列的一个PO集合Result
	 */
	public List<Map<String, String>> getList(String sql, int pageno, int pageSize, String type,
			Object... para) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 分页结束

		try {
			ResultSet rs = getRsByPages(sql, pageno, pageSize, para);

			if (rs.next()) {
				ResultSetMetaData rm = rs.getMetaData();
				int count = rm.getColumnCount();
				do {
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0; i < count; i++) {
						String tmp = rm.getColumnName(i + 1);
						String key = getValueByType(type, tmp);
						map.put(key, rs.getString(tmp));
					}
					list.add(map);
				} while (rs.next());
			}
		} catch (Exception e) {
			printError(e, sql);
		} finally {
			closeAll();
		}
		return list;
	}

	protected String getValueByType(String type, String tmp) {
		if ("upper".equals(type)) {
			tmp = StrUtils.toUpperCase(tmp);
		} else if ("lower".equals(type)) {
			tmp = StrUtils.toLowerCase(tmp);
		}
		return tmp;
	}

	protected ResultSet getRsByPages(String sql, int pageno, int pageSize, Object... para)
			throws SQLException {
		ResultSet rs = null;
		if (pageno > 0) {
			if (pageSize <= 0) {
				pageSize = Constants.DEFAULT_PAGE_SIZE;
			}
			sql = DBUtil.getSqlByPages(sql, pageno, pageSize);
			Object[] pages = new Integer[2];
			pages[0] = pageno * pageSize;
			pages[1] = (pageno - 1) * pageSize;
			rs = getResultSet(sql, concat(para, pages));
		} else {
			rs = getResultSet(sql, para);
		}
		return rs;
	}

	/**
	 * 根据自定义sql返回不确定列的一条记录
	 * 
	 * @author flyfox
	 * @param sql
	 * @param type
	 *            key类型转换 normal 正常 upper 转大写 lower 转小写
	 * @return
	 */
	public Map<String, Object> getMapByOneRow(String sql, String type, Object... para) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ResultSet rs = getResultSet(sql, para);

			ResultSetMetaData rm = null;
			if (rs.next()) {
				rm = rs.getMetaData();
				int count = rm.getColumnCount();
				for (int i = 0; i < count; i++) {
					String tmp = rm.getColumnName(i + 1);
					tmp = getValueByType(type, tmp);
					map.put(tmp, rs.getString(tmp));
				}
			}
		} catch (Exception e) {
			printError(e, sql, para);
		} finally {
			closeAll();
		}
		return map;
	}

	/**
	 * 查询两列值，作为map键值对返回，例如在select下拉框的使用
	 * 
	 * @author flyfox
	 * @param sql
	 * @return
	 */
	public Map<String, String> getMapByTwoColumns(String sql, Object... para) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			ResultSet rs = getResultSet(sql, para);

			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}

		} catch (Exception e) {
			printError(e, sql, para);
		} finally {
			closeAll();
		}
		return map;
	}

	/**
	 * 查询一个值:支持预编译
	 * 
	 * @param sql
	 * @param para
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-7-4 上午09:31:57
	 */
	public String getValue(String sql, Object... para) {
		try {
			ResultSet rs = getResultSet(sql, para);

			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			printError(e, sql, para);
		} finally {
			closeAll();
		}
		return null;
	}
}
