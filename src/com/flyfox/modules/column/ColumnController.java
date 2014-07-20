package com.flyfox.modules.column;

import java.io.File;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.flyfox.util.DateUtils;
import com.flyfox.util.StrUtils;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.upload.UploadFile;

/**
 * 栏目
 * 
 * @author flyfox 2013-11-11
 */
public class ColumnController extends BaseController {

	private static final String path = "/pages/column/";
	private static final String upload_path = PathKit.getWebRootPath() + File.separator + "download" + File.separator
			+ "image_url";
	ColumnSvc svc = new ColumnSvc();

	public void index() {
		getTree();
		render(path + "index.jsp");
	}

	public void list() {
		TbColumn model = getModel(TbColumn.class, "attr");
		StringBuffer sql = new StringBuffer();
		Page<TbColumn> page = null ;
		if (DbKit.getConfig().getDialect() instanceof MysqlDialect) {
			// mysql
			sql.append(" from ( SELECT * FROM tb_column R where R.parent_id = ? or R.id = ? ");
			SQLUtils sqlUtils = new SQLUtils(sql.toString(), "R");
			sqlUtils.whereLike("title", model.getStr("title"));
			sqlUtils.whereEquals("type", model.getInt("type"));
			sqlUtils.append(" order by level,sort");
			sqlUtils.append(" ) t");
			page = TbColumn.dao.paginate(getPaginator(), "select t.* ",
					sqlUtils.toString(), model.getInt("id"), model.getInt("id"));
		} else {
			// postgresql
			sql.append(" from ( WITH RECURSIVE R AS ");
			sql.append(" (SELECT * FROM TB_COLUMN WHERE ID = ? ");
			sql.append(" UNION ALL SELECT COL.* FROM TB_COLUMN COL, R WHERE COL.PARENT_ID = R.ID) ");
			sql.append("  SELECT * FROM R WHERE 1 = 1 ");

			SQLUtils sqlUtils = new SQLUtils(sql.toString(), "R");
			sqlUtils.whereLike("title", model.getStr("title"));
			sqlUtils.whereEquals("type", model.getInt("type"));
			sqlUtils.append(" order by level,sort");
			sqlUtils.append(" ) t");
			page = TbColumn.dao.paginate(getPaginator(), "select t.* ",
					sqlUtils.toString(), model.getInt("id"));
		}
		
		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.jsp");
	}

	public void add() {
		getTree();
		Integer parent_id = getParaToInt();// 父ID
		// 父节点
		if (parent_id != null && parent_id > 0) {
			TbColumn model = new TbColumn();
			model.put("parent_id", parent_id);
			TbColumn parent_model = ColumnSvc.getColumn(parent_id);
			model.put("parent_val", parent_model.getStr("title"));
			model.put("level", (parent_model.getInt("level") + 1));
			setAttr("model", model);
		}

		render(path + "add.jsp");
	}

	public void view() {
		TbColumn model = ColumnSvc.getColumn(getParaToInt());
		setAttr("model", model);
		render(path + "view.jsp");
	}

	public void delete() {
		TbColumn model = ColumnSvc.getColumn(getParaToInt());
		if (StrUtils.isNotEmpty(model.getStr("image_url"))) {
			new File(upload_path + File.separator + model.getStr("image_url")).delete();
		}
		TbColumn.dao.deleteById(getParaToInt());
		list();
	}

	public void edit() {
		getTree();
		TbColumn model = ColumnSvc.getColumn(getParaToInt());
		// 父节点
		if (model.getInt("parent_id") != null && model.getInt("parent_id") > 0) {
			TbColumn parent_model = ColumnSvc.getColumn(model.getInt("parent_id"));
			model.put("parent_val", parent_model.getStr("title"));
		} else {
			model.put("parent_id", 0);
			model.put("parent_val", "根节点");
		}
		setAttr("model", model);
		render(path + "edit.jsp");
	}

	public void edit_content() {
		TbColumn model = ColumnSvc.getColumn(getParaToInt());
		setAttr("model", model);
		render(path + "edit_content.jsp");
	}

	public void view_content() {
		TbColumn model = ColumnSvc.getColumn(getParaToInt());
		String content = model.getStr("content");
		content = StrUtils.isEmpty(content) ? "" : content;
		renderHtml(content);
	}

	public void save() {
		UploadFile uploadFile = getFile("tbColumn.image_url", upload_path, 10 * 1024 * 1024);
		Integer pid = getParaToInt();
		TbColumn model = getModel(TbColumn.class);

		if (uploadFile != null) {
			model.set("image_url", uploadFile.getFileName());
		}

		if (pid != null && pid > 0) { // 更新
			svc.update(model);
		} else { // 新增
			model.remove("id");
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", DateUtils.getNow());
			svc.save(model);
		}
		renderMessage("保存成功");
	}

	public void save_content() {
		TbColumn model = getModel(TbColumn.class);
		svc.update(model);
		renderMessage("保存成功");
	}

	/**
	 * 获取树信息
	 * 
	 * @author flyfox 2014-1-27
	 */
	private void getTree() {
		List<TbColumn> list = TbColumn.dao.findByWhere(" order by level,sort ");
		JSONArray jsonArr = new JSONArray();
		for (TbColumn tbColumn : list) {
			JSONObject json = new JSONObject();
			if (tbColumn.getInt("parent_id").equals(0)) {
				json.put("open", true);
			}
			json.put("id", tbColumn.getInt("id"));
			json.put("pId", tbColumn.getInt("parent_id"));
			json.put("name", tbColumn.getStr("title"));
			jsonArr.add(json);
		}
		setAttr("nodes", jsonArr.toString());
	}
}
