package com.flyfox.component.util;

import org.apache.log4j.Logger;

import com.flyfox.system.dict.SysDictDetail;
import com.flyfox.util.cache.Cache;
import com.flyfox.util.cache.CacheManager;

public class JFlyFoxCache {

	private final static Logger log = Logger.getLogger(JFlyFoxCache.class);
	private final static String cacheName = "JFlyFoxCache";
	private static Cache cache = CacheManager.get(cacheName);

	/**
	 * 更新缓存
	 * 
	 * 2015年4月29日 下午4:37:40 flyfox 330627517@qq.com
	 */
	public void updateCache() {
		init();
	}

	public static void init() {
		log.info("####JFlyFoxCache初始化......");
		update();
	}

	/**
	 * 更新友情链接缓存
	 * 
	 * 2015年4月24日 下午3:11:40 flyfox 330627517@qq.com
	 */
	public static void update() {
		cache.clear();
		
		// 获取web title  页面展示名称
		String webTitle = null;
		SysDictDetail dict = SysDictDetail.dao.findFirst("select detail_name from sys_dict_detail " //
				+ "where  dict_type = 'systemParam' and detail_code = 1");
		if (dict != null) {
			webTitle = dict.getStr("detail_name");
		} else {
			webTitle = "FLY的狐狸~！~";
		}
		cache.add("webTitle", webTitle);
		
		// 获取head title - html title
		String headTitle = null;
		SysDictDetail headTitleDict = SysDictDetail.dao.findFirst("select detail_name from sys_dict_detail " //
				+ "where  dict_type = 'systemParam' and detail_code = 2");
		if (headTitleDict != null) {
			headTitle = headTitleDict.getStr("detail_name");
		} else {
			headTitle = "Jflyfox博客";
		}
		cache.add("headTitle", headTitle);

	}

	public static String getHeadTitle() {
		return cache.get("headTitle");
	}
	
	public static String getWebTitle() {
		return cache.get("webTitle");
	}

}
