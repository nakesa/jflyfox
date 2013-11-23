package com.flyfox.component.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.flyfox.util.Constants;
import com.flyfox.util.DateUtils;
import com.flyfox.util.NumberUtils;
import com.flyfox.util.StrUtils;

public class Dao {

	private static final Logger logger = Logger.getLogger(Dao.class);

	/**
	 * sql 不存在
	 */
	protected static final int ERROR_NOSQL = -4444;

	/**
	 * sql 执行异常
	 */
	protected static final int ERROR_ERRSQL = -999999999;

	private Connection con = null;

	private boolean trans = false;

	private ResultSet rs;
	private PreparedStatement ps;

	protected Dao() {
	}

	public void open() {
		try {
			con = getConnection();
			con.setAutoCommit(false);
			trans = !con.getAutoCommit();
		} catch (Exception e) {
			logger.error("openTrans", e);
		}
	}

	public void rollback() {
		try {
			con.rollback();
			con.setAutoCommit(true);
			trans = !con.getAutoCommit();
			close(con);
		} catch (Exception e) {
			logger.error("rollback", e);
		}
	}

	public void commit() {
		try {
			con.commit();
			con.setAutoCommit(true);
			trans = !con.getAutoCommit();
			close(con);
		} catch (Exception e) {
			logger.error("commit", e);
		}
	}

	/**
	 * @author flyfox
	 * @return Connection
	 */
	protected Connection getConnection() {
		return DaoManager.getConnection();
	}

	protected void closeAll() {
		// 关闭RS
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			logger.error("closeRs", e);
		}
		// 关闭PS
		try {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		} catch (Exception e) {
			logger.error("closePs", e);
		}
		if (!trans)
			close(con);
	}

	/**
	 * @author flyfox
	 * @param con
	 */
	// 释放链接
	protected void close(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				if (Constants.DEBUG) {
					logger.info("##CLOSE:关闭数据库连接");
				}
				con.close();
				con = null;
			}
		} catch (Exception e) {
			logger.error("closeConnection", e);
		}
	}

	/**
	 * 获取结果集SQL查询
	 * 
	 * @param sql
	 * @param para
	 * @return
	 * @throws SQLException
	 * @author zb 330627517@qq.com
	 * @create 2013-8-22 下午03:37:53
	 */
	protected ResultSet getResultSet(String sql, Object... para) throws SQLException {
		PreparedStatement ps = prepareSql(sql, para);
		rs = ps.executeQuery();
		return rs;
	}

	/**
	 * 预编译SQL
	 * 
	 * @param sql
	 * @param para
	 * @return
	 * @throws SQLException
	 * @author zb 330627517@qq.com
	 * @create 2013-8-22 下午03:53:41
	 */
	protected PreparedStatement prepareSql(String sql, Object... para) throws SQLException {
		rs = null;
		ps = null;
		if (!trans)
			con = getConnection();
		ps = con.prepareStatement(sql);
		int para_int = para.length;
		while (para_int-- > 0) {
			setParaToPs(para_int + 1, para[para_int]);
		}
		if (Constants.DEBUG) {
			print(sql, para);
		}
		return ps;
	}

	// /////////////////////////////////////////////////整理///////////////////////////////////////////////////

	// 单条预编译语句
	public int execute(String sql, Object... para) {
		if (StrUtils.isEmpty(sql)) {
			return ERROR_NOSQL;
		}

		int row = -1;
		try {
			prepareSql(sql, para);

			row = ps.executeUpdate();
		} catch (Exception e) {
			return printError(e, sql, para);
		} finally {
			closeAll();
		}
		return row;
	}

	// 多条预编译语句
	public int executeByParas(String sql, Object[][] para) {
		if (StrUtils.isEmpty(sql)) {
			return ERROR_NOSQL;
		}

		if (Constants.DEBUG) {
			print(sql);
		}
		int[] row = { 0 };
		try {
			if (!trans)
				con = getConnection();
			ps = con.prepareStatement(sql);
			int i = para.length;
			while (i-- > 0) {
				for (int j = 0; j < para[i].length; j++) {
					setParaToPs(j + 1, para[i][j]);
				}
				ps.addBatch();
			}

			row = ps.executeBatch();
		} catch (Exception e) {
			return printError(e, sql);
		} finally {
			closeAll();
		}
		return sumByArray(row);
	}

	public int executeByBatch(String[] sqls) {
		if (sqls == null || sqls.length <= 0) {
			return ERROR_NOSQL;
		}

		if (Constants.DEBUG) {
			printSqls(sqls);
		}
		int[] row = new int[0];
		try {
			if (!trans)
				con = getConnection();
			for (String sql : sqls) {
				ps = con.prepareStatement(sql);
				ps.addBatch();
			}
			row = ps.executeBatch();
		} catch (Exception e) {
			return printSqlsError(e, sqls);
		} finally {
			closeAll();
		}
		return sumByArray(row);
	}

	private void setParaToPs(int num, Object obj) throws SQLException {
		if (obj instanceof String) {
			ps.setString(num, String.valueOf(obj));
		} else if (obj instanceof BigDecimal) {
			ps.setBigDecimal(num, NumberUtils.parseBigDecimal(obj));
		} else if (obj instanceof Timestamp) {
			ps.setTimestamp(num, DateUtils.parseTimestamp(obj.toString()));
		} else if (obj instanceof Integer) {
			ps.setInt(num, NumberUtils.parseInt(obj));
		} else {
			ps.setObject(num, obj);
		}
	}

	// ///////////////////////////////////////////////////////////

	protected void printSqls(String[] sqls) {
		logger.info("\r\nsqls-->" + arrayToString(sqls));
	}

	protected int printSqlsError(Exception e, String[] sqls) {
		logger.info("\r\nsqls-->" + arrayToString(sqls), e);
		return ERROR_ERRSQL;
	}

	protected void print(String sql, Object... para) {
		String paraStr = arrayToString(para);
		logger.info("\r\nsql-->" + sql + (paraStr == null ? "" : "\r\npara-->" + paraStr));
	}

	protected int printError(Exception e, String sql, Object... para) {
		String paraStr = arrayToString(para);
		logger.info("\r\nsql-->" + sql + (paraStr == null ? "" : "\r\npara-->" + paraStr), e);
		return ERROR_ERRSQL;
	}

	/**
	 * 对象转字符串
	 * 
	 * 2013年9月18日 下午10:12:42 flyfox 330627517@qq.com
	 * 
	 * @param para
	 * @return
	 */
	protected String arrayToString(Object[] para) {
		if (para == null || para.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < para.length - 1; i++) {
			sb.append(para[i] + ",");
		}
		sb.append(para[para.length - 1]);
		return sb.toString();
	}

	/**
	 * 数组求和
	 * 
	 * 2013年9月19日 上午12:07:37 flyfox 330627517@qq.com
	 * 
	 * @param row
	 * @return
	 */
	protected int sumByArray(int[] row) {
		int res = -1;
		for (int i = 0; i < row.length; i++) {
			res += row[i];
		}
		res = Math.abs(res);
		return res;
	}

	/**
	 * 数组拷贝
	 * 
	 * 2013年9月18日 下午4:18:00 flyfox 330627517@qq.com
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	protected static Object[] concat(Object[] a, Object[] b) {
		if (b.length <= 0) {
			return a;
		}
		if (a.length <= 0) {
			return b;
		}
		Object[] c = new Object[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
}
