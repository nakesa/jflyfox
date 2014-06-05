package com.flyfox.modules.column;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.flyfox.base.service.BaseService;

/**
 * 栏目service
 * 
 * @author flyfox
 * 2014-2-11
 */
public class ColumnSvc extends BaseService {

	public static List<TbColumn> getListByLevel(int level) {
		List<TbColumn> list = TbColumn.dao.findByWhere(" level = ? order by sort ", level);
		return list;
	}

	public static List<TbColumn> getListByParentId(int parentId) {
		Map<Integer, TbColumn> map = ColumnCache.colChildMap.get(parentId);
		List<TbColumn> list = new ArrayList<TbColumn>();
		if (map == null)
			return list;

		for (Integer key : map.keySet()) {
			TbColumn column = map.get(key);
			list.add(column);
		}
		return list;
	}

	public static TbColumn getColumn(int id) {
		return ColumnCache.colMap.get(id);
	}

	public boolean save(TbColumn column) {
		boolean flag = column.save();
		ColumnCache.rebuild(column.getInt("id"));
		return flag;
	}

	public boolean update(TbColumn column) {
		boolean flag = column.update();
		ColumnCache.rebuild(column.getInt("id"));
		return flag;
	}
}
