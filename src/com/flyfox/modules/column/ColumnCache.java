package com.flyfox.modules.column;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 栏目缓存
 * 
 * @author flyfox
 * 2014-2-11
 */
public class ColumnCache {

	private final static Logger log = Logger.getLogger(ColumnCache.class);
	static final Map<Integer, Map<Integer, TbColumn>> colChildMap = new HashMap<Integer, Map<Integer, TbColumn>>();
	static final Map<Integer, TbColumn> colMap = new HashMap<Integer, TbColumn>();

	/**
	 * 初始化栏目表
	 * 
	 * @author flyfox 2013-11-19
	 */
	public static void init() {
		log.info("####栏目Cache初始化......");
		colMap.clear();
		List<TbColumn> listDetail = new ArrayList<TbColumn>();
		listDetail = TbColumn.dao.findByWhere(" order by sort");
		Map<Integer, TbColumn> childs;
		for (TbColumn column : listDetail) {
			colMap.put(column.getInt("id"), column);

			childs = colChildMap.get(column.getInt("parent_id"));
			if (childs == null)
				childs = new LinkedHashMap<Integer, TbColumn>();
			childs.put(column.getInt("id"), column);
			colChildMap.put(column.getInt("parent_id"), childs);
		}
	}

	/**
	 * 重建一个数据
	 * 
	 * @param key
	 * @return
	 * @author flyfox 2014-2-7
	 */
	public static boolean rebuild(Integer key) {
		if (key == null)
			return false;
		TbColumn column = TbColumn.dao.findFirstByWhere(" where id = ? order by sort ", key);
		return rebuild(key, column);
	}

	/**
	 * 重建一个数据
	 * 
	 * @param key
	 * @return
	 * @author flyfox 2014-2-7
	 */
	public static boolean rebuild(Integer key, TbColumn column) {
		if (key == null)
			return false;
		colMap.put(column.getInt("id"), column);

		Map<Integer, TbColumn> childs = colChildMap.get(column.getInt("parent_id"));
		if (childs == null)
			childs = new LinkedHashMap<Integer, TbColumn>();
		childs.put(column.getInt("id"), column);
		colChildMap.put(column.getInt("parent_id"), childs);

		return true;
	}

}
