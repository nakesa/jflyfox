package com.flyfox.component.dao.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flyfox.component.dao.Trans;

public class TransModel extends Trans {

	public int add(Model model) {
		return execute(model.getSQLByInsert(), model.getSQLByInsertPara());
	}

	public int update(Model model, Object... para) {
		return execute(model.getSQLByUpdate(), concat(model.getSQLByUpdatePara(), para));
	}

	public int delete(Model model, Object... para) {
		return execute(model.getSQLByDelete(), para);
	}

	@SuppressWarnings("unchecked")
	public <T extends Model> T getModel(String columns, Model model, Object... para) {
		return (T) model.clone().setAttrsValue(
				getMapByOneRow(model.getSQLByQuery(columns), "lower", para));
	}

	public <T extends Model> T getModel(Model model, Object... para) {
		return getModel(model.getColumnNames(), model, para);
	}

	public List<Model> find(Model model, Object... para) {
		return findByPages(0, 0, model.getColumnNames(), "lower", model, para);
	}

	public List<Model> find(String columns, Model model, Object... para) {
		return findByPages(0, 0, columns, "lower", model, para);
	}

	public List<Model> findByPages(int pageno, int pageSize, Model model, Object... para) {
		return findByPages(pageno, pageSize, model.getColumnNames(), "lower", model, para);
	}

	public List<Model> findByPages(int pageno, int pageSize, String columns, Model model,
			Object... para) {
		return findByPages(pageno, pageSize, columns, "lower", model, para);
	}

	protected List<Model> findByPages(int pageno, int pageSize, String columns, String type,
			Model model, Object... para) {
		String sql = model.getSQLByQuery(columns);
		List<Model> list = new ArrayList<Model>();

		try {
			ResultSet rs = getRsByPages(sql, pageno, pageSize, para);

			if (rs.next()) {
				ResultSetMetaData rm = rs.getMetaData();
				int count = rm.getColumnCount();
				do {
					Model tmpModel = model.clone();
					Map<String, Object> map = new HashMap<String, Object>();
					for (int i = 0; i < count; i++) {
						String tmp = rm.getColumnName(i + 1);
						String key = getValueByType(type, tmp);
						map.put(key, rs.getString(tmp));
					}
					tmpModel.setAttrsValue(map);
					list.add(tmpModel);
				} while (rs.next());
			}
		} catch (Exception e) {
			printError(e, sql);
		} finally {
			closeAll();
		}
		return list;
	}

}
