package com.flyfox.modules.web;

import java.util.List;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.column.ColumnSvc;
import com.flyfox.modules.column.TbColumn;

@ControllerBind(controllerKey = "/web")
public class Home extends BaseController {

	private static final String path = "/pages/web/";

	public void index() {
		// 根目录
		setAttr("model", ColumnSvc.getColumn(TbColumn.ROOT));
		// 数据列表
		List<TbColumn> columns = ColumnSvc.getListByParentId(TbColumn.ROOT);
		setAttr("list", columns);

		render(path + "home.jsp");
	}

}
