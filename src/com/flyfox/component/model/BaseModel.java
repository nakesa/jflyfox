package com.flyfox.component.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.TableInfoMapping;

@SuppressWarnings("rawtypes")
public class BaseModel<M extends Model> extends Model<M> {

	private static final long serialVersionUID = 1L;

	public List<M> find() {
		String tableName = TableInfoMapping.me().getTableInfo(getClass()).getTableName();
		String sql = " SELECT * FROM " + tableName;
		return find(sql);
	}

}
