package com.flyfox.modules.web;

import java.util.List;

import com.flyfox.component.beelt.BeeltFunctions;
import com.flyfox.component.util.ArticleCountCache;
import com.flyfox.component.util.JFlyFoxCache;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.comment.TbComment;
import com.flyfox.modules.folder.TbFolder;
import com.flyfox.modules.tags.TbTags;
import com.flyfox.modules.web.service.WebService;
import com.jfinal.plugin.activerecord.Page;

/**
 * 联系人管理
 * 
 * @author flyfox 2014-2-11
 */
@ControllerBind(controllerKey = "/web_article")
public class ArticleController extends BaseController {

	/**
	 * 查看文章
	 * 
	 * 2015年2月26日 下午1:46:14 flyfox 330627517@qq.com
	 */
	public void index() {

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

			// seo：title优化 
			setAttr(JFlyFoxUtils.TITLE_ATTR, article.getStr("title") + " - " + JFlyFoxCache.getHeadTitle());
			
			// 标签
			List<TbTags> taglist = TbTags.dao.findByWhere(" where article_id = ? order by id", article.getInt("id"));
			setAttr("taglist", taglist);
			
			// 评论
			Page<TbComment> comments = TbComment.dao.paginate(getPaginator(), "select * ", //
					" from tb_comment where article_id = ? order by create_time desc ", articleId);
			setAttr("page", comments);
		}

		// 目录列表
		new WebService().showDirectory(this, article.getInt("folder_id"));

		renderAuto(Home.path + "show_article.html");

	}

	/**
	 * 查看文章某用户发布文章
	 * 
	 * 2015年2月26日 下午1:46:14 flyfox 330627517@qq.com
	 */
	public void user() {

		Integer userid = getParaToInt();

		setAttr("WEB_TITLE", BeeltFunctions.getUserName(userid));
		// 目录列表
		new WebService().showDirectory(this, TbFolder.ROOT);

		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), "select * ", //
				" from tb_article where status = 1 and type in (11,12) " //
						+ "and create_id = ? " //
						+ "order by sort,create_time desc", userid);
		setAttr("page", articles);

		renderAuto(Home.path + "home.html");

	}
}
