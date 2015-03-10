package com.flyfox.modules.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.flyfox.component.util.ArticleCountCache;
import com.flyfox.component.util.ImageCode;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.comment.TbComment;
import com.flyfox.modules.folder.TbFolder;

@ControllerBind(controllerKey = "/web")
public class Home extends BaseController {

	public static final String path = "/pages/web/";

	public void index() {
		Integer folder_id = getParaToInt();
		if (folder_id == null || folder_id <= 0) {
			folder_id = TbFolder.ROOT;
		}
		// TODO 应该静态化或者取用户信息根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));
		// 目录列表
		new HomeService().showDirectory(this, folder_id);
		
		// 数据列表,只查询展示的和类型为11,12的
		List<TbArticle> articles = TbArticle.dao.findByWhere(" where status = 1 and type in (11,12) " //
				+ "and folder_id = ? " //
				+ "order by sort,create_time desc", folder_id);
		setAttr("list", articles);

		renderAuto(path + "home.html");

	}

	/**
	 * 查看文章
	 * 
	 * 2015年2月26日 下午1:46:14 flyfox 330627517@qq.com
	 */
	public void article() {

		// 数据列表
		int articleId = getParaToInt();
		TbArticle article = TbArticle.dao.findById(articleId);
		if (article != null) {
			// 更新浏览量
			String key = getSessionAttr(JFlyFoxUtils.USER_KEY);
			if (key != null) {
				ArticleCountCache.addCountView(article, key);
			}

			setAttr("item", article);

			List<TbComment> listComment = TbComment.dao.findByWhere( //
					" where article_id = ? order by create_time desc ", articleId);
			setAttr("listComment", listComment);
		}
		
		// TODO 应该静态化或者取用户信息根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));
		// 目录列表
		new HomeService().showDirectory(this, article.getInt("folder_id"));
		
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
	 * 登出
	 */
	public void logout() {
		removeSessionUser();
		redirect(getPrePage());
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
