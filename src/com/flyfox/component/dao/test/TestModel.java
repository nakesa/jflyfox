package com.flyfox.component.dao.test;

import java.util.List;

import org.h2.tools.DeleteDbFiles;

import com.flyfox.component.dao.Trans;
import com.flyfox.component.dao.model.Model;
import com.flyfox.component.dao.model.TransModel;

public class TestModel {

	public static void main(String[] args) throws Exception {
		/*
		 * List<Object> list = new ArrayList<Object>(); for (int i = 0; i <
		 * 100000; i++) { T_DICT_MainModel o = new T_DICT_MainModel();
		 * list.add(o); } list.toString();
		 */
		executeSql();
		executeModel();
	}

	private static void executeModel() {
		T_DICT_MainModel dictMain = new T_DICT_MainModel();
		dictMain.setDict_name("222");
		dictMain.setDict_type("test1");
		dictMain.setIs_delete(0);
		dictMain.setSqlWhere(" dict_type = ? ");

		T_DICT_MainModel queryModel = new T_DICT_MainModel();
		queryModel.setSqlWhere(" dict_type = ? ");

		T_DICT_MainModel listModel = new T_DICT_MainModel();
		listModel.setSqlWhere(" 1 = ? ");

		System.out.println("##~!~##" + listModel.getSQLByInsert());
		TransModel trans = new TransModel();
		trans.open();
		// del
		trans.delete(dictMain, "test1");
		// add
		trans.add(dictMain);
		// add
		dictMain.setDict_type("test2");
		trans.add(dictMain);
		// queryList
		List<Model> listOne = trans.find(listModel, 1);
		for (Model model : listOne) {
			T_DICT_MainModel tmpModel = (T_DICT_MainModel) model;
			printModel(tmpModel);
		}
		// queryListPages
		List<Model> listTwo = trans.findByPages(1, 2, listModel, 1);
		for (Model model : listTwo) {
			T_DICT_MainModel tmpModel = (T_DICT_MainModel) model;
			printModel(tmpModel);
		}
		// queryListPages
		List<Model> listThress = trans.findByPages(2, 2, listModel, 1);
		for (Model model : listThress) {
			T_DICT_MainModel tmpModel = (T_DICT_MainModel) model;
			printModel(tmpModel);
		}
		// query
		T_DICT_MainModel queryModel2 = trans.getModel(queryModel, "test2");
		printModel(queryModel2);
		// update
		dictMain.setDict_name("sda");
		trans.update(dictMain, "test2");
		// query
		queryModel = trans.getModel(queryModel, "test2");
		printModel(queryModel);
		// del
		trans.delete(dictMain, "test2");
		// query
		queryModel = trans.getModel(queryModel, "test2");
		printModel(queryModel);
		trans.commit();
	}

	private static void printModel(T_DICT_MainModel model) {
		System.out.println("####\tdict_type:" + model.getDict_type() + "\tdict_name:"
				+ model.getDict_name() + "\tis_delete:" + model.getIs_delete());
	}

	private static void executeSql() {
		// delete the database named 'test' in the user home directory
		DeleteDbFiles.execute("D:/", "test", true);
		// Config.init();

		Trans trans = new Trans();
		trans.open();
		trans.execute("drop table T_DICT_MAIN");
		trans.execute("create table T_DICT_MAIN(dict_type varchar(255) primary key,"
				+ " dict_name varchar(255), is_delete int)");
		trans.execute("insert into T_DICT_MAIN values('test', 'Hello1',0)");
		System.out.println("#####"
				+ trans.getValue("select dict_name from T_DICT_MAIN where dict_type = 'test'"));
		trans.commit();
		System.out.println(2);
	}

}
