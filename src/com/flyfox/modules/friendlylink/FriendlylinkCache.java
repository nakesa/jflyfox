package com.flyfox.modules.friendlylink;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flyfox.jfinal.base.BaseService;

/**
 * 友情链接管理
 * 
 * @author flyfox 2014-4-24
 */
public class FriendlylinkCache extends BaseService {

	private final static Logger log = Logger.getLogger(FriendlylinkCache.class);
	private static final List<TbFriendlylink> cacheList = new ArrayList<TbFriendlylink>();

	public static void init() {
		log.info("####FriendlylinkCache初始化......");
		update();
	}

	/**
	 * 更新友情链接缓存
	 * 
	 * 2015年4月24日 下午3:11:40 flyfox 330627517@qq.com
	 */
	public static void update() {
		List<TbFriendlylink> list = TbFriendlylink.dao.findByWhere(" order by sort ");
		cacheList.clear();
		for (TbFriendlylink model : list) {
			cacheList.add(model);
		}
	}

	public static List<TbFriendlylink> getList() {
		return cacheList;
	}

}
