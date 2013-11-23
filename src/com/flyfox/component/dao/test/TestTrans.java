package com.flyfox.component.dao.test;

import org.h2.tools.DeleteDbFiles;

import com.flyfox.component.dao.Trans;

public class TestTrans {

	public static void main(String[] args) throws Exception {
		executeSql();
	}

	private static void executeSql() {
		// delete the database named 'test' in the user home directory
		DeleteDbFiles.execute("D:/", "test", true);
		// Config.init();

		Trans trans = new Trans();
		trans.open();
		trans.execute("create table T_DICT_MAIN(dict_type varchar(255) primary key,"
				+ " dict_name varchar(255), is_delete int)");
		trans.execute("insert into T_DICT_MAIN values('test', 'Hello1',0)");
		System.out.println("#####"
				+ trans.getValue("select dict_name from T_DICT_MAIN where dict_type = 'test'"));
		trans.commit();
	}

}
