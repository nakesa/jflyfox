package com.flyfox.modules.web;

import java.util.List;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.comment.TbComment;
import com.flyfox.modules.user.SysUser;

/**
 * 我的消息
 * 
 * 2015年3月10日 下午5:38:24
 * flyfox 330627517@qq.com
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
		String sqlWhere = "where create_id = ? or reply_userid = ? order by create_time desc ";
		List<TbComment> list = TbComment.dao.findByWhere(sqlWhere, user.getUserid(), user.getUserid());
		// 更新状态为已读
		new HomeService().updateCommentStatus(user.getUserid(), 2);

		setAttr("listComment", list);
		renderAuto(Home.path + "show_message.html");
	}
}
