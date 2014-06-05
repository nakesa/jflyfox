package com.flyfox.modules.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 用户信息缓存
 * 
 * @author flyfox
 * 2014-2-11
 */
public class UserCache {

	private final static Logger log = Logger.getLogger(UserCache.class);
	private static final Map<Integer, SysUser> cacheMap = new HashMap<Integer, SysUser>();
	private static final List<SysUser> cacheList = new ArrayList<SysUser>();

	private UserCache() {
	}

	public static void init() {
		log.info("####用户Cache初始化......");
		cacheMap.clear();
		cacheList.clear();
		List<SysUser> userList = SysUser.dao.findByWhere(" order by userid ");
		for (SysUser user : userList) {
			cacheMap.put(user.getInt("pid"), user);
			cacheList.add(user);
		}
	}

	public static SysUser getUser(Integer pid) {
		return cacheMap.get(pid);
	}

	public static Map<Integer, SysUser> getUserMap() {
		return cacheMap;
	}

	public static List<SysUser> getUserList() {
		return cacheList;
	}

}
