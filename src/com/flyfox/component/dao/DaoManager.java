/**
 * author:lcy
 * description:arrangement
 * tomcat DataSource
 */
package com.flyfox.component.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.flyfox.util.Constants;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import conf.Config;

public class DaoManager {

	private static final Logger logger = Logger.getLogger(DaoManager.class);

	/**
	 * C3P0数据源
	 */
	private static ComboPooledDataSource c3p0DataSource = null;

	private static String dialect;

	private static String contype;
	private static String jdbcUrl;
	private static String user;
	private static String password;
	private static String driverClass;
	private static int maxPoolSize;
	private static int minPoolSize;
	private static int initialPoolSize;
	private static int maxIdleTime;
	private static int acquireIncrement;
	// private int numHelperThreads=10;
	// private int maxStatements=100;

	static {
		init();
	}

	private static void init() {
		dialect = Config.getDbParam("db.dialect");
		contype = Config.getDbParam("db.contype");

		driverClass = Config.getDbParam(dialect + ".drivers");
		user = Config.getDbParam(dialect + ".user");
		password = Config.getDbParam(dialect + ".password");
		jdbcUrl = Config.getDbParam(dialect + ".url");
		jdbcUrl = jdbcUrl == null ? "com.mysql.jdbc.Driver" : jdbcUrl;
		maxPoolSize = toInt(Config.getDbParam("db.maxconns"), 20);
		minPoolSize = toInt(Config.getDbParam("db.minconns"), 1);
		initialPoolSize = toInt(Config.getDbParam("db.initconns"), 2);
		maxIdleTime = toInt(Config.getDbParam("db.maxIdleTime"), 20);
		acquireIncrement = toInt(Config.getDbParam("db.acquireIncrement"), 5);
		// 启动C3P0
		startC3p0();
	}

	private DaoManager() {
	}

	public static Connection getConnection(String dialect) {
		try {
			String driverClass = Config.getDbParam(dialect + ".drivers");
			String user = Config.getDbParam(dialect + ".user");
			String password = Config.getDbParam(dialect + ".password");
			String jdbcUrl = Config.getDbParam(dialect + ".url");
			jdbcUrl = jdbcUrl == null ? "com.mysql.jdbc.Driver" : jdbcUrl;

			Class.forName(driverClass);
			return DriverManager.getConnection(jdbcUrl, user, password);
		} catch (Exception ex) {
			logger.error(" getConnection failed!!!" + ex.getMessage());
			return null;
		}
	}

	/**
	 * @author lcy
	 * @return Connection
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			if ("jdbc".equals(contype)) {
				// 现在引用jdbc直连
				if (conn == null || conn.isClosed()) {
					conn = DaoManager.getConnectionByJdbc();
					if (conn != null && !conn.isClosed()) {
						if (Constants.DEBUG)
							logger.info("##OPEN:现在引用jdbc直连");
						return conn;
					}
				}
			} else if ("c3p0".equals(contype)) {
				// 现在C3P0连接池
				DataSource ds = c3p0DataSource;
				if (ds != null) {
					conn = ds.getConnection();
					if (conn != null && !conn.isClosed()) {
						if (Constants.DEBUG)
							logger.info("##OPEN:现在C3P0连接池");
						return conn;
					}
				}
			}

			// 没有连接上
			if (conn == null || conn.isClosed()) {
				logger.error(" ##ERROR:数据源获取失败！！！ ");
				return null;
			}

		} catch (Exception e) {
			System.out.println("driverClass:" + driverClass);
			System.out.println("jdbcUrl:" + jdbcUrl);
			System.out.println("user:" + user);
			System.out.println("password:" + password);
			logger.error(" ##ERROR:数据源获取异常！！！ ", e);
		}
		return null;
	}

	/**
	 * 获取JDBC数据源
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-8-22 下午05:13:15
	 */
	public static Connection getConnectionByJdbc() {
		try {
			Class.forName(driverClass);
			return DriverManager.getConnection(jdbcUrl, user, password);
		} catch (Exception ex) {
			logger.error(" getConnectionByJdbc failed!!!" + ex.getMessage());
			return null;
		}
	}

	public static Connection getConnectionByC3p0() {
		try {
			if (c3p0DataSource == null) {
				startC3p0();
				if (c3p0DataSource == null) {
					return null;
				}
			}
			return c3p0DataSource.getConnection();
		} catch (Exception ex) {
			logger.error(" getConnectionByC3p0 failed!!!" + ex.getMessage());
			return null;
		}
	}

	/**
	 * c3p0生成数据源
	 * 
	 * @return
	 * @author zb 330627517@qq.com
	 * @create 2013-8-22 下午05:13:26
	 */
	static boolean startC3p0() {
		c3p0DataSource = new ComboPooledDataSource();
		c3p0DataSource.setJdbcUrl(jdbcUrl);
		c3p0DataSource.setUser(user);
		c3p0DataSource.setPassword(password);
		try {
			c3p0DataSource.setDriverClass(driverClass);
		} catch (PropertyVetoException e) {
			c3p0DataSource = null;
			System.err.println("C3p0Pool start error");
			throw new RuntimeException(e);
		}
		c3p0DataSource.setMaxPoolSize(maxPoolSize);
		c3p0DataSource.setMinPoolSize(minPoolSize);
		c3p0DataSource.setInitialPoolSize(initialPoolSize);
		c3p0DataSource.setMaxIdleTime(maxIdleTime);
		c3p0DataSource.setAcquireIncrement(acquireIncrement);
		// 上面设置的
		// dataSource.setNumHelperThreads(numHelperThreads);
		// dataSource.setMaxStatements(maxStatements);
		return true;
	}

	/**
	 * 项目关闭时、关闭数据源
	 * 
	 * @return
	 */
	public static boolean close() {
		if (c3p0DataSource != null)
			c3p0DataSource.close();
		return true;
	}

	static Integer toInt(String str, int value) {
		return Integer.parseInt(str) > 0 ? Integer.parseInt(str) : value;
	}

	public static String getDialect() {
		return dialect;
	}

}
