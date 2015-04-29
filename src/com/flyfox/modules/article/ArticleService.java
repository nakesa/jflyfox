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
			articles = TbArticle.dao.paginate(paginator, "select * " //
					, " from tb_article  where status = 1 and type in (11,12) " //
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
