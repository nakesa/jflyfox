package com.flyfox.component.dao.auto;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flyfox.component.dao.DBUtil;

/**
 * @param TableName
 * @filename TableNamePO.java
 * @classname TableNamePO
 * @param2 packagename
 */
public class ModelGenerateOracle extends ModelGenerate {

	/**
	 * 生成一个指定表的po类的java文件,适应oracle
	 * 
	 * @param tablename
	 * @param packagename
	 * @param con
	 * @return
	 * @throws IOException
	 */
	public boolean createModel(String tablename, String typePack, String enc, Connection con) throws Exception {
		ResultSet rs = null;
		String primaryKey = null;
		String primaryKeyType = null;
		String[] columnname = null;
		String[] columntype = null;
		int[] data_scale = null;
		String[] columncomm = null;
		int colcount = 0;
		try {
			String sql = "SELECT COUNT(1) COLCOUNT FROM USER_TAB_COLUMNS WHERE TABLE_NAME='" + tablename.toUpperCase()
					+ "' ";
			rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				colcount = rs.getInt(1);
			}
			sql = "SELECT CU.COLUMN_NAME,Tu.DATA_TYPE "
					+ " FROM USER_CONS_COLUMNS CU, USER_CONSTRAINTS AU,USER_TAB_COLUMNS TU "
					+ " WHERE CU.CONSTRAINT_NAME = AU.CONSTRAINT_NAME "
					+ " AND AU.TABLE_NAME = TU.TABLE_NAME AND CU.column_name = tu.COLUMN_NAME "
					+ "AND AU.CONSTRAINT_TYPE = 'P' AND AU.TABLE_NAME = '" + tablename.toUpperCase() + "' ";
			rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				primaryKey = rs.getString(1);
				primaryKeyType = rs.getString(2);
			}

			columnname = new String[colcount];
			columntype = new String[colcount];
			data_scale = new int[colcount];
			columncomm = new String[colcount];
			sql = "SELECT A.COLUMN_NAME,A.DATA_TYPE,NVL(A.DATA_SCALE,-1),B.COMMENTS "
					+ " FROM USER_TAB_COLUMNS A, USER_COL_COMMENTS B WHERE A.TABLE_NAME='" + tablename.toUpperCase()
					+ "' AND A.TABLE_NAME=B.TABLE_NAME AND A.COLUMN_NAME=B.COLUMN_NAME ORDER BY A.COLUMN_ID";
			rs = con.createStatement().executeQuery(sql);
			int colnum = 0;
			while (rs.next()) {
				columnname[colnum] = rs.getString(1).toLowerCase();
				columntype[colnum] = rs.getString(2).toLowerCase();
				data_scale[colnum] = rs.getInt(3);
				columncomm[colnum] = rs.getString(4);
				colnum++;

			}
			String holePackPre = typePack.toLowerCase();
			String path = ("src." + holePackPre).replaceAll("\\.", "\\\\");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			File f = new File(path + "\\" + tablename + "Model.java");
			FileOutputStream fos = new FileOutputStream(f);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			StringBuffer sb = new StringBuffer();
			sb.append("package " + holePackPre + ";\n\n");
			sb.append("import com.flyfox.model.Model;\n");
			sb.append("import java.util.HashMap;\n");
			sb.append("import java.util.Map;\n\n");
			sb.append("public class " + tablename + "Model extends Model {\n\n");

			// 构造函数
			sb.append("\tpublic " + tablename + "Model() {\n");
			sb.append("\t}\n\n");

			// 构造函数 主键参数
			if (primaryKeyType == null) {
			} else if ("NUMBER".equals(primaryKeyType)) {
				sb.append("\tpublic " + tablename + "Model(int param) {\n");
			} else {
				sb.append("\tpublic " + tablename + "Model(String param) {\n");
			}
			sb.append("\t\t set(\"" + toLowerCase(primaryKey) + "\",param);\n");
			sb.append("\t}\n\n");

			// serialVersionUID
			sb.append("\tprivate static final long serialVersionUID = 1L;\n\n");
			sb.append("\t/**\n");
			sb.append("\t * 表信息\n");
			sb.append("\t */\n");
			sb.append("\tprotected static final Map<String, String> tableInfo = new HashMap<String, String>();\n\n");
			sb.append("\t/**\n");
			sb.append("\t * 属性\n");
			sb.append("\t */\n");
			sb.append("\tprotected static final Map<String, Class<?>> attrs = new HashMap<String, Class<?>>();\n\n");

			sb.append("\tpublic Map<String, String> getTableInfo() {\n");
			sb.append("\t\treturn tableInfo;\n");
			sb.append("\t}\n\n");

			sb.append("\tpublic Map<String, Class<?>> getAttrs() {\n");
			sb.append("\t\treturn attrs;\n");
			sb.append("\t}\n\n");

			sb.append("\tstatic {\n");
			// 主键
			sb.append("\t\ttableInfo.put(PRIMARY_KEY, \"" + toLowerCase(primaryKey) + "\");");
			sb.append(" /* 主键 */\n");
			// 表名
			sb.append("\t\ttableInfo.put(TABLE_NAME, \"" + tablename.toUpperCase() + "\");");
			sb.append(" /* 表名 */\n");
			// 序列
			// sb.append("\t\ttableInfo.put(SEQUENCE, \"SEQ_FOR_" +
			// tablename.toUpperCase() + "\");");
			// sb.append(" /* 序列  */\n");
			// 字段和类型
			for (int i = 0; i < colcount; i++) {
				if (columntype[i].equalsIgnoreCase("number")) {
					if (data_scale[i] == 0 || data_scale[i] == -1)
						sb.append("\t\tattrs.put(\"" + columnname[i] + "\", Integer.class);");
					else
						sb.append("\t\tattrs.put(\"" + columnname[i] + "\", java.math.BigDecimal.class);");
				} else if (columntype[i].equalsIgnoreCase("date")) {
					sb.append("\t\tattrs.put(\"" + columnname[i] + "\", java.sql.Timestamp.class);");
				} else {
					sb.append("\t\tattrs.put(\"" + columnname[i] + "\", String.class);");
				}
				if (columncomm[i] != null && columncomm[i].length() > 0) {
					sb.append(" /* " + clear(columncomm[i], "\\s") + " */");
				}
				sb.append("\n");
			}
			sb.append("\t}\n\n");

			// 取得自身对象
			sb.append("\tpublic " + tablename + "Model getMe() {\n");
			sb.append("\t\treturn new " + tablename + "Model() ;\n");
			sb.append("\t}\n\n");

			// 属性字段的存取方法 NUMBER,DATE,CHAR,VARCHAR2
			for (int i = 0; i < colcount; i++) {
				// get
				if (columntype[i].equalsIgnoreCase("number")) {
					if (data_scale[i] == 0 || data_scale[i] == -1) {
						sb.append("\tpublic int get" + DBUtil.firstToUpperCase(columnname[i]) + "() {\n");
						sb.append("\t\treturn getInteger(\"" + columnname[i] + "\");\n");
					} else {
						sb.append("\tpublic java.math.BigDecimal get" + DBUtil.firstToUpperCase(columnname[i])
								+ "() {\n");
						sb.append("\t\treturn getBigDecimal(\"" + columnname[i] + "\");\n");
					}
				} else if (columntype[i].equalsIgnoreCase("date")) {
					sb.append("\tpublic java.sql.Timestamp get" + DBUtil.firstToUpperCase(columnname[i]) + "() {\n");
					sb.append("\t\treturn getTimestamp(\"" + columnname[i] + "\");\n");
				} else {
					sb.append("\tpublic java.lang.String get" + DBUtil.firstToUpperCase(columnname[i]) + "() {\n");
					sb.append("\t\treturn getString(\"" + columnname[i] + "\");\n");
				}
				sb.append("\t}\n\n");

				// set
				if (columntype[i].equalsIgnoreCase("number")) {
					if (data_scale[i] == 0 || data_scale[i] == -1)
						sb.append("\tpublic void set" + DBUtil.firstToUpperCase(columnname[i]) + "(int "
								+ columnname[i] + ") {\n");
					else
						sb.append("\tpublic void set" + DBUtil.firstToUpperCase(columnname[i])
								+ "(java.math.BigDecimal " + columnname[i] + ") {\n");
				} else if (columntype[i].equalsIgnoreCase("date")) {
					sb.append("\tpublic void set" + DBUtil.firstToUpperCase(columnname[i]) + "(java.sql.Timestamp "
							+ columnname[i] + ") {\n");
				} else {
					sb.append("\tpublic void set" + DBUtil.firstToUpperCase(columnname[i]) + "(java.lang.String "
							+ columnname[i] + ") {\n");
				}
				sb.append("\t\tset(\"" + columnname[i] + "\"," + columnname[i] + ");\n");
				sb.append("\t}\n\n");
			}

			sb.append("}");
			bos.write(sb.toString().getBytes(enc));
			bos.flush();
			bos.close();
			bos = null;
			fos = null;
			f = null;
		} catch (SQLException e) {
			System.out.println("sql exception!");
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e1) {
				System.out.println("rs or con be closed failed!");
			}
		}
		return true;
	}

}