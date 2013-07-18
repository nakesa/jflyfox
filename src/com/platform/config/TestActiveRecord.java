package com.platform.config;

import java.util.List;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.platform.model.Dpet;

public class TestActiveRecord {
	static String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	static String user = "bjepn_whsh";
	static String password = "bjepn_whsh";
	static String driverClass = "oracle.jdbc.driver.OracleDriver";

	public static void main(String[] args) {
		startActiveRecord();

		List<Dpet> list = Dpet.dao.find("select * from SY_D_DEPT");
		for (Dpet dpet : list) {
			System.out.println(dpet.getStr("ORGNAME"));
		}
	}

	/**
	 * 启动ActiveRecord
	 */
	private static void startActiveRecord() {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(url, user, password, driverClass);
		c3p0Plugin.start();
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setDialect(new OracleDialect()); // 使用Oracle
		arp.addMapping("SY_D_DEPT", Dpet.class); // 映射blog 表到 Blog模型
		arp.start();
	}
}
