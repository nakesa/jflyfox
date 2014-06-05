package com.flyfox.modules.column;

import com.flyfox.base.controller.BaseController;

/**
 * 首页文章展示
 * 
 * @author flyfox
 * 2014-2-11
 */
public class ArticleController extends BaseController {

	public void show() {
		Integer id = getParaToInt();
		TbColumn column = ColumnSvc.getColumn(id);
		renderHtml(column.getStr("content"));
	}
}
