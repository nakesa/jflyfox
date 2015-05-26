package com.flyfox.modules.article;

import com.flyfox.jfinal.base.BaseService;
import com.flyfox.jfinal.base.Paginator;
import com.flyfox.util.cache.Cache;
import com.flyfox.util.cache.CacheManager;
import com.jfinal.plugin.activerecord.Page;

/**
 * 文章管理
 * 
 * @author flyfox 2014-2-11
 */
public class ArticleService extends BaseService {

	private final static String cacheName = "ArticleService";
	/**
	 * 目录缓存
	 */
	private static Cache cache = CacheManager.get(cacheName);

	/**
	 * 加入文章访问量和评论数缓存
	 * 
	 * 2015年5月26日 上午8:53:27 flyfox 330627517@qq.com
	 * 
	 * @param article
	 * @return
	 */
	public TbArticle addArticleCount(TbArticle article) {
		String key = ("articleCount_" + article.getId());
		TbArticle articleCount = new TbArticle().setId(article.getId()) //
				.setCountView(article.getCountView()) //
				.setCountComment(article.getCountComment());
		cache.add(key, articleCount);
		return articleCount;
	}

	/**
	 * 获取缓存量
	 * 
	 * 2015年5月26日 上午8:56:26 flyfox 330627517@qq.com
	 * 
	 * @param articleId
	 * @return
	 */
	public TbArticle getArticleCount(int articleId) {
		String key = ("articleCount_" + articleId);
		TbArticle articleCount = cache.get(key);
		// 推荐文章列表
		if (articleCount == null) {
			articleCount = TbArticle.dao.findFirst("select id,count_view,count_comment " //
					+ "from tb_article where id = ?", articleId);
			cache.add(key, articleCount);
		}
		return articleCount;
	}
	
	/**
	 * 查询文章，展示的和类型为11,12的
	 * 
	 * 2015年4月29日 下午4:48:24 flyfox 330627517@qq.com
	 * 
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbArticle> getArticlePage(Paginator paginator, Integer folder_id) {
		String key = (folder_id + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbArticle> articles = cache.get(key);
		// 目录列表
		if (articles == null) {
			articles = TbArticle.dao.paginate(paginator, "select t.*,tag.tagsname " //
					, " from tb_article t "
							// 拼接标签
							+ "left join (SELECT article_id,group_concat(tagname) tagsname "
							+ " FROM tb_tags GROUP BY article_id order by id ) tag on t.id = tag.article_id"
							// 拼接标签
							+ " where status = 1 and type in (11,12) " //

							+ "and folder_id = ? " //
							+ "order by sort,create_time desc", folder_id);
			cache.add(key, articles);
		}
		return articles;
	}

	/**
	 * 更新缓存,清空
	 * 
	 * 2015年4月29日 下午4:37:40 flyfox 330627517@qq.com
	 */
	public void updateCache() {
		cache.clear();
	}
}
