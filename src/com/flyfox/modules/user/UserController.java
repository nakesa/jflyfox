package com.flyfox.modules.user;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.flyfox.util.DateUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * 用户管理
 * 
 * @author flyfox
 * 2014-2-11
 */
public class UserController extends BaseController {

	private static final String path = "/pages/common/user/";

	public void list() {
		SysUser model = getModel(SysUser.class, "attr");
		
		SQLUtils sql = new SQLUtils(" from sys_user t where 1 = 1 order by userid");
		sql.whereLike("username", model.getStr("username"));
		sql.whereLike("realname", model.getStr("realname"));
		Page<SysUser> page = SysUser.dao.paginate(getPaginator(), "select t.* ",
				sql.toString().toString());
		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.jsp");
	}

	public void add() {
		render(path + "add.jsp");
	}

	public void view() {
		SysUser model = SysUser.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.jsp");
	}

	public void delete() {
		SysUser.dao.deleteById(getParaToInt());
		UserCache.init();
		list();
	}

	public void edit() {
		SysUser model = SysUser.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.jsp");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysUser model = getModel(SysUser.class);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("userid");
			model.put("password", "123456");
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", DateUtils.getNow());
			model.save();
		}
		UserCache.init();
		renderMessage("保存成功");
	}
}
