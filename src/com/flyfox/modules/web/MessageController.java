package com.flyfox.modules.web;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.comment.TbComment;
import com.flyfox.modules.web.service.CommentService;
import com.flyfox.modules.web.service.HomeService;
import com.flyfox.system.user.SysUser;
import com.jfinal.plugin.activerecord.Page;

/**
 * 我的消息
 * 
 * 2015年3月10日 下午5:38:24 flyfox 330627517@qq.com
 */
@ControllerBind(controllerKey = "/web_message")
public class MessageController extends BaseController {

	/**
	 * 我的消息
	 */
	public void index() {
		// 目录列表
		new HomeService().showDirectory(this, "message");

		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		String sql = " from tb_comment t " //
				+ " left join tb_article art on art.id = t.article_id " //
				+ " where t.create_id = ? or t.reply_userid = ? order by t.create_time desc ";
		Page<TbComment> pages = TbComment.dao.paginate(getPaginator(), //
				"select t.*,art.title,art.create_id as article_create_id " //
				, sql, user.getUserid(), user.getUserid());
		// 更新状态为已读
		new CommentService().updateCommentStatusRead(user.getUserid());

		setAttr("page", pages);
		renderAuto(Home.path + "show_message.html");
	}
}
