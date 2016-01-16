package com.jflyfox.modules.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.jflyfox.component.util.JFlyFoxCache;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.modules.article.TbArticle;
import com.jflyfox.modules.folder.TbFolder;
import com.jflyfox.modules.tags.TbTags;
import com.jflyfox.modules.web.service.WebService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.jfinal.base.BaseController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;

@ControllerBind(controllerKey = "/web_tags")
public class TagsController extends BaseController {

	public void index() {
		String tagName = getPara();
		try {
			tagName = URLDecoder.decode(tagName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		setAttr("WEB_TITLE", "标签：" + tagName);
		// 目录列表
		new WebService().showDirectory(this, TbFolder.ROOT);

		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), "select t.* ", //
				" from tb_article t " //
						+ " left join tb_tags tag on tag.article_id = t.id " //
						+ " where tag.tagname = ? and t.status = 1 and t.type in (11,12)  " //
						+ " order by t.sort,t.create_time desc", tagName);
		setAttr("page", articles);

		// seo：title优化
		setAttr(JFlyFoxUtils.TITLE_ATTR, tagName + " - " + JFlyFoxCache.getHeadTitle());

		renderAuto(Home.path + "home.html");
	}
	
	public void all() {
		// 目录列表
		int tagsAllId = Db.findFirst(" SELECT id FROM `tb_folder` where jump_url = 'web_tags/all'").getInt("id");
		new WebService().showDirectory(this, tagsAllId);

		List<TbTags> taglist = TbTags.dao.find(" select tagname from tb_tags group by tagname order by id ");
		setAttr("taglist", taglist);

		// seo：title优化
		setAttr(JFlyFoxUtils.TITLE_ATTR, "标签 - " + JFlyFoxCache.getHeadTitle());

		renderAuto(Home.path + "show_tags.html");
	}

}
