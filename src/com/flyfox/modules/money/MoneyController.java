package com.flyfox.modules.money;

import com.flyfox.base.controller.BaseController;
import com.flyfox.modules.project.TbProject;
import com.flyfox.util.DateUtils;
import com.flyfox.util.StrUtils;
import com.flyfox.util.db.SQLUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * 金钱管理
 * 
 * @author flyfox 2014-2-11
 */
public class MoneyController extends BaseController {

	private static final String path = "/pages/money/";

	public void list() {
		TbMoney model = getModel(TbMoney.class, "attr");

		SQLUtils sql = new SQLUtils(" from tb_money t,tb_project p where t.project_id = p.id ");
		sql.setAlias("t");
		sql.whereLike("name", model.getStr("name"));
		sql.whereEquals("type", model.getStr("type"));
		sql.whereEquals("project_id", model.getInt("project_id"));

		String pay_time_start = getPara("pay_time_start");
		String pay_time_end = getPara("pay_time_end");
		model.put("pay_time_start", pay_time_start);
		model.put("pay_time_end", pay_time_end);
		if (StrUtils.isNotEmpty(pay_time_start))
			sql.append(" and pay_time >= '" + pay_time_start + "'");
		if (StrUtils.isNotEmpty(pay_time_end))
			sql.append(" and pay_time <= '" + pay_time_end + "'");

		sql.append("order by t.project_id,t.type,t.id desc");
		
		Page<TbMoney> page = TbMoney.dao.paginate(getPaginator(), "select t.*,p.name as project_name ", //
				sql.toString().toString());

		// 下拉框
		setAttr("optionList", selectProject(model.getInt("project_id")));
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.jsp");
	}

	public String selectProject(Integer selected) {
		return TbProject.selectProject(selected);
	}

	public void add() {
		setAttr("optionList", selectProject(null));
		render(path + "add.jsp");
	}

	public void view() {
		TbMoney model = TbMoney.dao.findById(getParaToInt());
		setAttr("optionList", selectProject(model.getInt("project_id")));
		setAttr("model", model);
		render(path + "view.jsp");
	}

	public void delete() {
		TbMoney.dao.deleteById(getParaToInt());
		list();
	}

	public void edit() {
		TbMoney model = TbMoney.dao.findById(getParaToInt());
		setAttr("optionList", selectProject(model.getInt("project_id")));
		setAttr("model", model);
		render(path + "edit.jsp");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbMoney model = getModel(TbMoney.class);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", getSessionUser().getUserid());
			model.put("create_time", DateUtils.getCreateTime());
			model.save();
		}
		renderMessage("保存成功");
	}
}
