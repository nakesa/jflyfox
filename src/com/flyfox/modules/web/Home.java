package com.flyfox.modules.web;

import java.util.List;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.folder.TbFolder;

@ControllerBind(controllerKey = "/web")
public class Home extends BaseController {

	private static final String path = "/pages/web/";

	public void index() {
		Integer folder_id = getParaToInt();
		if (folder_id == null || folder_id <= 0) {
			folder_id = TbFolder.ROOT;
		}
		// 根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));

		// 目录列表
		List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
		setAttr("folders", folders);
		setAttr("folders_selected", folder_id);
		// 数据列表,只查询展示的和类型为11,12的
		List<TbArticle> articles = TbArticle.dao.findByWhere(" where status = 1 and type in (11,12) " //
				+ "and folder_id = ? " //
				+ "order by sort,create_time desc", folder_id);
		setAttr("list", articles);

		renderAuto(path + "home.html");

	}

	public void showArticle() {
		// 根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));
		// 数据列表
		TbArticle article = TbArticle.dao.findById(getParaToInt());
		if (article != null) {
			setAttr("item", article);
		}

		renderAuto(path + "show_article.html");

	}

	/**
	 * 登陆
	 */
	public void login() {
		setAttr("pre_page", getPrePage());
		render(CommonController.loginPage);
	}

	/**
	 * 注册
	 */
	public void regist() {
		setAttr("pre_page", getPrePage());
		render(CommonController.registPage);
	}

	/**
	 * 登出
	 */
	public void logout() {
		removeSessionUser();
		redirect(getPrePage());
	}

	public String getPrePage() {
		return getRequest().getHeader("Referer");
	}
	
	/**
	 * 我的消息
	 */
	public void message() {
		index();
	}
	
	/**
	 * 个人信息
	 */
	public void person() {
		int id = getParaToInt();
		if (id <= 0 ) {
			index();
		} else {
			index();
		}
	}

}
