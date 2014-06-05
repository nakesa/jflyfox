package com.flyfox.modules.dict;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * 数据字典缓存
 * 
 * 2014年1月21日 下午11:23:23 flyfox 330627517@qq.com
 */
public class DictCache {

	private final static Logger log = Logger.getLogger(DictCache.class);
	static final Map<Integer, SysDictDetail> dictMap = new LinkedHashMap<Integer, SysDictDetail>();

	/**
	 * 初始化Map
	 * 
	 * @author flyfox 2013-11-15
	 */
	public static void init() {
		log.info("####数据字典Cache初始化......");
		DictCache.initDict();
	}

	/**
	 * 初始化数据字典明细表
	 * 
	 * @author flyfox 2013-11-19
	 */
	public static void initDict() {
		dictMap.clear();
		List<SysDictDetail> listDetail = new ArrayList<SysDictDetail>();
		// detailSort
		listDetail = SysDictDetail.dao.findByWhere(" order by detail_sort,detail_id");
		for (SysDictDetail detail : listDetail) {
			dictMap.put(detail.getInt("detail_id"), detail);
		}
	}

	public static Map<Integer, SysDictDetail> getDictdetailMap() {
		return DictCache.dictMap;
	}

	//////////////////////////jstl 标签/////////////////////////////
	
	/**
	 * 获取下拉菜单
	 * 
	 * 2014年1月22日 下午10:08:38 flyfox 330627517@qq.com
	 * 
	 * @param type
	 * @param selected_value
	 * @return
	 */
	public static String getSelect(String type, Integer selected_value) {
		Map<Integer, SysDictDetail> map = DictCache.getDictdetailMap();
		if (map == null || map.size() <= 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (Integer key : map.keySet()) {
			SysDictDetail dict = map.get(key);
			if (dict.getStr("dict_type").equals(type)) {
				sb.append("<option value=\"");
				sb.append(dict.getInt("detail_id"));
				sb.append("\" ");
				sb.append(key.equals(selected_value) ? "selected" : "");
				sb.append(">");
				sb.append(dict.getStr("detail_name"));
				sb.append("</option>");
			}
		}
		return sb.toString();
	}

	/**
	 * 获取Value值
	 * 
	 * 2014年1月22日 下午10:10:26 flyfox 330627517@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(Integer key) {
		if (key == null) {
			return null;
		}
		SysDictDetail dict = dictMap.get(key);
		return dict == null ? null : dict.getStr("detail_name");
	}

	/**
	 * 获取Code值
	 * 
	 * 2014年1月22日 下午10:10:26 flyfox 330627517@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public static String getCode(Integer key) {
		if (key == null) {
			return null;
		}
		SysDictDetail dict = dictMap.get(key);
		return dict == null ? null : dict.getStr("detail_code");
	}

}
