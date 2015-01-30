package com.flyfox.modules.folder;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.flyfox.util.DateUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * 联系人管理
 * 
 * @author flyfox 2014-2-11
 */
public class FolderController extends BaseController {

	private static final String path = "/pages/folder/";

	public void index() {
		list();
	}

	public void list() {
		TbFolder model = getModel(TbFolder.class, "attr");

		SQLUtils sql = new SQLUtils(" from tb_folder t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("name", model.getStr("name"));
			sql.whereEquals("status", model.getInt("status"));
		}

		Page<TbFolder> page = TbFolder.dao.paginate(getPaginator(), "select t.* ", //
				sql.toString().toString());

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		TbFolder model = TbFolder.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		// 不处理首页数据
		if (getParaToInt() == 1) {
			list();
			return;
		}
		TbFolder.dao.deleteById(getParaToInt());
		list();
	}

	public void edit() {
		TbFolder model = TbFolder.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		// 不处理首页数据
		if (getParaToInt() == 1) {
			renderMessage("数据不能修改。");
			return;
		}
		
		Integer pid = getParaToInt();
		TbFolder model = getModel(TbFolder.class);
		model.put("update_time", DateUtils.getNow("yyyy-MM-dd HH:mm:ss"));
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", DateUtils.getNow());
			model.save();
		}
		renderMessage("保存成功");
	}
}
