package com.flyfox.base.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.TableInfo;
import com.jfinal.plugin.activerecord.TableInfoMapping;

/**
 * Model 优化修改
 * 
 * @author flyfox
 * 2014-2-11
 * @param <M>
 */
public class BaseModel<M extends Model<M>> extends Model<M> {

	private static final long serialVersionUID = 1L;

	public TableInfo getTableInfo() {
		return TableInfoMapping.me().getTableInfo(getClass());
	}

	/**
	 * Paginate.
	 * 
	 * @param paginator
	 *            the page
	 * @param select
	 *            the select part of the sql statement
	 * @param sqlExceptSelect
	 *            the sql statement excluded select part
	 * @param paras
	 *            the parameters of sql
	 * @return Page
	 */
	public Page<M> paginate(Paginator paginator, String select, String sqlExceptSelect, Object... paras) {
		return paginate(paginator.getPageNo(), paginator.getPageSize(), select, sqlExceptSelect, paras);
	}

	/**
	 * Find model.
	 * 
	 * @param where
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @param paras
	 *            the parameters of sql
	 * @return the list of Model
	 */
	public List<M> findByWhere(String where, Object... paras) {
		return findByWhereAndColumns(where, "*", paras);
	}

	/**
	 * Find model.
	 * 
	 * @param where
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @param columns
	 * @param paras
	 *            the parameters of sql
	 * @return the list of Model
	 */
	public List<M> findByWhereAndColumns(String where, String columns, Object... paras) {
		String sql = " select " + columns + " from " + getTableInfo().getTableName() + " " + where;
		return find(sql, paras);
	}

	/**
	 * Find first model. I recommend add "limit 1" in your sql.
	 * 
	 * @param where
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @param paras
	 *            the parameters of sql
	 * @return Model
	 */
	public M findFirstByWhere(String where, Object... paras) {
		return findFirstByWhereAndColumns(where, "*", paras);
	}

	/**
	 * Find first model. I recommend add "limit 1" in your sql.
	 * 
	 * @param where
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @param columns
	 * @param paras
	 *            the parameters of sql
	 * @return Model
	 */
	public M findFirstByWhereAndColumns(String where, String columns, Object... paras) {
		String sql = " select " + columns + " from " + getTableInfo().getTableName() + " " + where;
		return findFirst(sql, paras);
	}

}
