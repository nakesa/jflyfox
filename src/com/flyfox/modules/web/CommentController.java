package com.flyfox.modules.web;

import com.alibaba.fastjson.JSONObject;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.comment.TbComment;
import com.flyfox.modules.user.SysUser;
import com.flyfox.modules.user.UserCache;
import com.flyfox.util.DateUtils;
import com.flyfox.util.StrUtils;

@ControllerBind(controllerKey = "/web_comment")
public class CommentController extends BaseController {

	/**
	 * 删除评论
	 */
	public void del() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getSessionAttr(Attr.SESSION_NAME);

		if (user == null) {
			json.put("msg", "没有登陆，无法进行删除！");
			renderJson(json.toJSONString());
			return;
		}

		TbComment comment = getModel(TbComment.class);
		int id = comment.getInt("id");
		if (id <= 0) {
			json.put("msg", "提交数据错误，无法删除！");
			renderJson(json.toJSONString());
			return;
		}
		// 确认数据创建人和当前用户一致
		TbComment test = TbComment.dao.findById(id);
		if (test.getInt("create_id") != user.getUserid()) {
			json.put("msg", "不能删除他人提交数据！");
			renderJson(json.toJSONString());
			return;
		}

		comment.deleteById(id);
		// 更新评论数
		new HomeService().updateCommentCount(comment.getInt("article_id"));

		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

	/**
	 * 保存评论
	 */
	public void save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getSessionAttr(Attr.SESSION_NAME);

		if (user == null) {
			json.put("msg", "没有登陆，无法进行评论！");
			renderJson(json.toJSONString());
			return;
		}

		TbComment comment = getModel(TbComment.class);
		if (StrUtils.isEmpty(comment.getStr("content"))) {
			json.put("msg", "发布内容不能为空！");
			renderJson(json.toJSONString());
			return;
		}

		String now = DateUtils.getNow("yyyy-MM-dd HH:mm:ss");
		comment.put("fatherId", 0);
		comment.put("create_id", user.getUserID());
		comment.put("create_time", now);
		comment.save();
		// 更新评论数
		new HomeService().updateCommentCount(comment.getInt("article_id"));

		json.put("comment_id", comment.getInt("id"));
		json.put("title_url", user.getStr("title_url"));
		if (comment.getInt("reply_userid") > 0) {
			json.put("reply_username", UserCache.getUser(comment.getInt("reply_userid")).getUserName());
		}
		json.put("create_name", user.getUserName());
		json.put("create_time", now);
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}
}
