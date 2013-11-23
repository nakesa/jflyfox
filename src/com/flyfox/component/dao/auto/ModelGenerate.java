package com.flyfox.component.dao.auto;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

import com.flyfox.component.dao.DBUtil;
import com.flyfox.component.dao.DaoManager;

/**
 * @param TableName
 * @filename TableNamePO.java
 * @classname TableNamePO
 * @param2 packagename
 */
public abstract class ModelGenerate {

	/**
	 * 创建Model
	 * 
	 * 2013年9月23日 下午3:16:19
	 * flyfox 330627517@qq.com
	 * @param tablename
	 * @param typePack
	 * @param enc
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public abstract boolean createModel(String tablename, String typePack, String enc, Connection con) throws Exception ;
	
	/**
	 * 生成PO，按照db.properties中的配置 database 为配置参数
	 */
	public static void generateAllPO(String[] tables, String enc, String typePack) {
		Connection con = null;
		try {
			// 取得连接
			con = DaoManager.getConnection();
			ModelGenerate modelGenerate = null;
			if (DBUtil.DIALECT_ORACLE.equals(DaoManager.getDialect())) {
				modelGenerate = new ModelGenerateOracle();
			} else if (DBUtil.DIALECT_MSSQL.equals(DaoManager.getDialect())) {
				modelGenerate = new ModelGenerateSqlServer();
			}
			for (int i = 0; i < tables.length; i++) {
				if (modelGenerate.createModel(tables[i], typePack, enc, con)) {
					System.out.println(tables[i] + " is success!");
				} else {
					System.out.println(tables[i] + " is failed!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("con be closed exception!");
			}
		}
	}


	protected String toLowerCase(String primaryKey) {
		return primaryKey == null ? null : primaryKey.toLowerCase();
	}

	protected String clear(String str, String regex) throws UnsupportedEncodingException {
		if (str == null || str.trim().equals(""))
			return str;
		if (regex != null) {
			String reg = "(" + regex + ")+";
			Pattern p = Pattern.compile(reg);
			while (p.matcher(str).find()) {
				str = str.replaceAll(reg, "");
			}
		}
		return str;
	}

	protected String firstToUpperCase(String s) {
		String ret = "";
		if (s != null && s.length() > 1) {
			ret = s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return ret;
	}
}