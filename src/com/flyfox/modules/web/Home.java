package com.flyfox.modules.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.flyfox.component.util.ImageCode;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.article.ArticleService;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.folder.TbFolder;
import com.flyfox.modules.web.service.HomeService;
import com.jfinal.plugin.activerecord.Page;

@ControllerBind(controllerKey = "/web")
public class Home extends BaseController {

	public static final String path = "/pages/web/";

	public void index() {
		Integer folder_id = getParaToInt();
		if (folder_id == null || folder_id <= 0) {
			folder_id = TbFolder.ROOT;
		}
		// 题目
		setAttr("web_title", JFlyFoxUtils.getWebTitle());
		// 目录列表，缓存
		new HomeService().showDirectory(this, folder_id);

		// 文章数据列表，缓存
		Page<TbArticle> articles = new ArticleService().getArticlePage(getPaginator(),folder_id);
		setAttr("page", articles);

		renderAuto(path + "home.html");

	}

	

	/**
	 * 登陆
	 */
	public void login() {
		setAttr("pre_page", getPrePage());
		render(CommonController.loginPage);
	}

	/**
	 * 登出
	 */
	public void logout() {
		removeSessionUser();
		redirect(CommonController.firstPage);
	}

	/**
	 * 生成注册码
	 * 
	 * 2015年2月28日 下午1:50:25 flyfox 330627517@qq.com
	 */
	public void image_code() {
		try {
			new ImageCode().doGet(getRequest(), getResponse());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderNull();
	}

}
