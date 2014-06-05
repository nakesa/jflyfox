package com.flyfox.modules.project;

import com.flyfox.base.controller.BaseController;
import com.flyfox.util.DateUtils;
import com.flyfox.util.db.SQLUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * 项目
 * 
 * @author flyfox 2014-2-11
 */
public class ProjectController extends BaseController {

	private static final String path = "/pages/project/";

	public void list() {
		TbProject model = getModel(TbProject.class, "attr");

		SQLUtils sql = new SQLUtils(" from tb_project t where 1 = 1 ");
		sql.whereLike("name", model.getStr("name"));
		Page<TbProject> page = TbProject.dao.paginate(getPaginator(), "select t.* ", sql.toString().toString());
		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.jsp");
	}

	public void add() {
		render(path + "add.jsp");
	}

	public void view() {
		TbProject model = TbProject.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.jsp");
	}

	public void delete() {
		TbProject.dao.deleteById(getParaToInt());
		list();
	}

	public void edit() {
		TbProject model = TbProject.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.jsp");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbProject model = getModel(TbProject.class);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("type", "1");
			model.put("create_id", getSessionUser().getUserid());
			model.put("create_time", DateUtils.getCreateTime());
			model.save();
		}
		renderMessage("保存成功");
	}
}
