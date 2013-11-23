package com.flyfox.component.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.flyfox.util.DateUtils;
import com.flyfox.util.NumberUtils;
import com.flyfox.util.StrUtils;

public abstract class Model implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private final static String PREFIX = "FF_";
	protected final static String PRIMARY_KEY = PREFIX + "PRIMARY_KEY";
	protected final static String SEQUENCE = PREFIX + "SEQUENCE";
	protected final static String TABLE_NAME = PREFIX + "TABLE_NAME";

	public abstract Model getMe();

	public abstract Map<String, String> getTableInfo();

	public abstract Map<String, Class<?>> getAttrs();

	/**
	 * 属性值
	 */
	protected Map<String, Object> attrsValue = new HashMap<String, Object>();

	public Map<String, Object> getAttrsValue() {
		return attrsValue;
	}

	protected Model setAttrsValue(Map<String, Object> attrsValue) {
		this.attrsValue = attrsValue;
		return this;
	}

	/**
	 * 获取主键Name
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 上午11:19:33
	 */
	public String getPrimaryKeyName() {
		return getTableInfo().get(PRIMARY_KEY);
	}

	/**
	 * 获取主键类型，用字符串标示
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 上午11:19:44
	 */
	public Class<?> getPrimaryKeyType() {
		return getAttrs().get(PRIMARY_KEY);
	}

	/**
	 * 获取表名
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 上午11:20:01
	 */
	public String getTableName() {
		return getTableInfo().get(TABLE_NAME);
	}

	/**
	 * 获取表字段
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 上午11:27:46
	 */
	public String getColumnNames() {
		StringBuffer sb = new StringBuffer();
		for (String column : getAttrs().keySet()) {
			sb.append(column + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public String getString(String key) {
		Object obj = get(key);
		return obj == null ? null : String.valueOf(obj);
	}

	public Integer getInteger(String key) {
		return NumberUtils.parseInt(get(key));
	}

	public BigDecimal getBigDecimal(String key) {
		return NumberUtils.parseBigDecimal(get(key));
	}

	public Timestamp getTimestamp(String key) {
		Object obj = get(key);
		if (obj == null) {
			return null;
		}
		return DateUtils.parseTimestamp(obj.toString());
	}

	/**
	 * 动态取属性值
	 * 
	 * @param col_name
	 *            要取的属性名
	 * @return String类型的属性值
	 */
	public Object get(String key) {
		return getAttrsValue().get(key);
	}

	/**
	 * 动态设置属性值
	 * 
	 * @param col_name
	 *            要设置属性名
	 * @param value
	 *            要设置属性值
	 */
	public void set(String key, Object value) {
		getAttrsValue().put(key, value);
	}

	// ////////////////////////////////////////////////////////////////////////

	/**
	 * Where条件
	 */
	private String sqlWhere = null;

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	/**
	 * 删除SQL
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 下午03:21:47
	 */
	public String getSQLByDelete() {
		StringBuffer sql = new StringBuffer(256);
		sql.append("DELETE FROM ");
		sql.append(getTableName());

		getSQLByWhere(sql);

		return sql.toString();
	}

	/**
	 * 插入SQL
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 下午03:21:55
	 */
	public String getSQLByInsert() {
		if (getAttrsValue().size() <= 0) {
			return "";
		}

		StringBuffer sql = new StringBuffer(256);
		sql.append("INSERT INTO ");
		sql.append(getTableName());

		sql.append("(");

		int n = 0;
		if (!getAttrsValue().containsKey(getPrimaryKeyName())) {
			sql.append(getPrimaryKeyName() + ",");
		}
		for (String column : getAttrsValue().keySet()) {
			if (getAttrs().containsKey(column)) {
				sql.append(column + ",");
				n = n + 1;
			}
		}
		sql.deleteCharAt(sql.length() - 1);

		sql.append(") VALUES (");

		if (!getAttrsValue().containsKey(getPrimaryKeyName())) {
			if (getPrimaryKeyType() == Integer.class) {
				if (getTableInfo().containsKey(SEQUENCE)) {
					sql.append("SELECT " + getTableInfo().get(SEQUENCE) + ".NEXTVAL FROM DUAL ,");
				} else {
					sql.append("nvl((select max(" + PRIMARY_KEY + ")+1 from " + getTableName() + "),1),");
				}
			} else {
				sql.append("newid(),");
			}
		}
		for (int i = 0; i < n; i++) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);

		sql.append(")");

		return sql.toString();
	}

	/**
	 * 拼接插入参数
	 * 
	 * @param sql
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 下午02:02:27
	 */
	public Object[] getSQLByInsertPara() {
		return getAttrsValue().values().toArray();
	}

	/**
	 * 更新SQL
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 下午03:22:12
	 */
	public String getSQLByUpdate() {
		if (getAttrsValue().size() <= 0) {
			return "";
		}

		StringBuffer sql = new StringBuffer(256);
		sql.append("UPDATE ");
		sql.append(getTableName());
		sql.append(" SET ");

		for (String column : getAttrsValue().keySet()) {
			if (getAttrs().containsKey(column)) {
				sql.append(column + "=?,");
			}
		}
		sql.deleteCharAt(sql.length() - 1);

		getSQLByWhere(sql);

		return sql.toString();
	}

	/**
	 * 拼接更新参数
	 * 
	 * @param sql
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 下午02:02:27
	 */
	public Object[] getSQLByUpdatePara() {
		return getAttrsValue().values().toArray();
	}

	/**
	 * 查询SQL
	 * 
	 * @param columns
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 下午03:22:03
	 */
	public String getSQLByQuery(String columns) {
		StringBuffer sql = new StringBuffer(300);
		sql.append("SELECT ");
		sql.append(columns);
		sql.append(" FROM ");
		sql.append(getTableName());

		getSQLByWhere(sql);

		return sql.toString();
	}

	/**
	 * 查询SQL
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 下午03:22:03
	 */
	public String getSQLByQuery() {
		return getSQLByQuery(getColumnNames());
	}

	/**
	 * 拼接Where
	 * 
	 * @param sql
	 * @author zb 330627517@qq.com
	 * @create 2013-9-6 下午02:02:27
	 */
	private void getSQLByWhere(StringBuffer sql) {
		sql.append(" WHERE ");
		if (StrUtils.isNotEmpty(getSqlWhere())) {
			sql.append(getSqlWhere());
		} else {
			String primaryKey = getPrimaryKeyName();
			if (getAttrs().get(primaryKey) == Integer.class && getInteger(primaryKey) > 0) {
				sql.append(getPrimaryKeyName() + "=" + getInteger(primaryKey) + " ");
			} else if (getAttrs().get(primaryKey) == String.class && StrUtils.isNotEmpty(getString(primaryKey))) {
				sql.append(getPrimaryKeyName() + "='" + getString(primaryKey) + "' ");
			} else {
				sql.append("1 <> 1");
			}
		}
	}

	// TODO 给Model复制 需要看是否需要实现
	public Model fetchResult(ResultSet rs) throws SQLException {
		// T_UserPO po = new T_UserPO();
		// po.setUserid(rs.getInt("USERID"));
		// po.setUsername(rs.getString("USERNAME"));
		// po.setLoginid(rs.getString("LOGINID"));
		// po.setPassword(rs.getString("PASSWORD"));
		// po.setIsadmin(rs.getInt("ISADMIN"));
		return null;
	}

	/**
	 * 使用克隆的话，必须要实现cloneable，现在的esspo层已经实现了，异常自己处理
	 */
	@Override
	protected Model clone() {
		try {
			return (Model) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
