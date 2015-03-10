package com.flyfox.modules.web;

import java.util.List;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.base.BaseService;
import com.flyfox.modules.folder.TbFolder;
import com.jfinal.plugin.activerecord.Db;

public class HomeService extends BaseService {

	/**
	 * 展示菜单
	 * 
	 * 2015年3月10日 下午4:44:54 flyfox 330627517@qq.com
	 * 
	 * @param folders_selected
	 *            选择项
	 */
	protected void showDirectory(BaseController controller, Object folders_selected) {
		// 目录列表
		List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
		controller.setAttr("folders", folders);
		controller.setAttr("folders_selected", folders_selected);
	}

	/**
	 * 更新评论数据
	 * 
	 * 2015年3月10日 下午3:25:46 flyfox 330627517@qq.com
	 * 
	 * @param article_id
	 */
	public void updateCommentCount(int article_id) {
		String sql = "update tb_article set count_comment = " //
				+ "(select count(*) from tb_comment where article_id = ? ) where id = ? ";
		Db.update(sql, article_id, article_id);
	}

	/**
	 * 更新状态
	 * 
	 * 2015年3月10日 下午4:10:00 flyfox 330627517@qq.com
	 * 
	 * @param userid
	 * @param status
	 */
	public void updateCommentStatus(Integer userid, int status) {
		String sql = "update tb_comment set status = ? " //
				+ " where create_id = ? or reply_userid = ? ";
		Db.update(sql, status, userid, userid);
	}
}
