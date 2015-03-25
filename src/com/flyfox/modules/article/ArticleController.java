package com.flyfox.modules.article;

import java.io.File;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.flyfox.modules.folder.TbFolder;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

/**
 * 联系人管理
 * 
 * @author flyfox 2014-2-11
 */
public class ArticleController extends BaseController {

	private static final String path = "/pages/article/";
	/**
	 * 图片目录
	 */
	private static final String upload_path = PathKit.getWebRootPath() + File.separator //
			+ "download" + File.separator + "image_url";

	public void index() {
		list();
	}

	@Override
	public void render(String view) {
		// 查询下拉框
		setAttr("folder_json", selectFolderJson());
		super.render(view);
	}

	/**
	 * 目录复选框
	 * 
	 * 2015年1月28日 下午5:28:40 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public String selectFolderJson() {
		return getJsonData("select id,name from tb_folder", "id", "name");
	}

	public void list() {
		TbArticle model = getModelByAttr(TbArticle.class);

		SQLUtils sql = new SQLUtils(" from tb_article t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("title", model.getStr("title"));
			sql.whereEquals("folder_id", model.getInt("folder_id"));
			sql.whereEquals("status", model.getInt("status"));
		}
		sql.append(" order by folder_id,sort ");

		Page<TbArticle> page = TbArticle.dao.paginate(getPaginator(), "select t.* ", //
				sql.toString().toString());

		// 查询下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		TbArticle.dao.deleteById(getParaToInt());
		list();
	}

	public void edit() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		UploadFile uploadFile = getFile("model.image_url", upload_path, 10 * 1024 * 1024);

		Integer pid = getParaToInt();
		TbArticle model = getModel(TbArticle.class);

		if (uploadFile != null) {
			model.set("image_url", uploadFile.getFileName());
		}

		model.put("update_time", getNow());
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", getNow());
			if (model.get("sort") == null)
				model.put("sort", 1);
			model.save();
		}
		renderMessage("保存成功");
	}

	public void edit_content() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);
		super.render(path + "edit_article.html");
	}

	public void view_content() {
		// 根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));
		// 数据列表
		TbArticle article = TbArticle.dao.findById(getParaToInt());
		if (article != null) {
			setAttr("item", article);
		}

		renderAuto(path + "show_article.html");

	}

	public void save_content() {
		TbArticle model = getModel(TbArticle.class);
		model.update();
		renderMessage("保存成功");
	}
}
