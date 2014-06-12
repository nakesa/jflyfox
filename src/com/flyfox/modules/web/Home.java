package com.flyfox.modules.web;

import java.util.List;

import com.flyfox.base.controller.BaseController;
import com.flyfox.component.route.ControllerBind;
import com.flyfox.modules.column.ColumnSvc;
import com.flyfox.modules.column.TbColumn;

@ControllerBind(controllerKey = "/web")
public class Home extends BaseController {

	private static final String path = "/pages/web/";

	public void index() {
		List<TbColumn> columns = ColumnSvc.getListByParentId(1);
		setAttr("list", columns);
		render(path + "home.jsp");
	}

}
