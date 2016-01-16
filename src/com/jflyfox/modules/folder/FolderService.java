package com.jflyfox.modules.folder;

import java.util.List;

import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

/**
 * 目录管理
 * 
 * @author flyfox 2014-2-11
 */
public class FolderService extends BaseService {

	private final static String cacheName = "HomeService";
	/**
	 * 目录缓存
	 */
	private static Cache cache = CacheManager.get(cacheName);

	/**
	 * 获取目录信息
	 * 
	 * 2015年4月29日 下午4:37:55
	 * flyfox 330627517@qq.com
	 * @return
	 */
	public List<TbFolder> getFolderList() {
		List<TbFolder> folders = cache.get("folderList");
		// 目录列表
		if (folders == null) {
			updateCache();
			folders = cache.get("folderList");
		}
		return folders;
	}

	/**
	 * 更新缓存
	 * 
	 * 2015年4月29日 下午4:37:40
	 * flyfox 330627517@qq.com
	 */
	public void updateCache() {
		List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
		cache.add("folderList", folders);
	}
}
