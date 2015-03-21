package com.flyfox.modules.web;

import com.alibaba.fastjson.JSONObject;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.comment.CommentContants;
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

		// 评论
		int status;
		if (comment.getInt("reply_userid") == 0) {
			// 评论自己文章 标记为已读
			status = (user.getUserID() == comment.getInt("create_id") ? CommentContants.STATUS_READ
					: CommentContants.STATUS_NO_READ);
			// 设置 回复人为文章创建者
			comment.put("reply_userid", comment.getInt("create_id"));
		} else { // 回复
			status = CommentContants.STATUS_REPLY_NO_READ ;
		}
		comment.put("status", status);
		
		comment.put("fatherId", 0);
		comment.put("create_id", user.getUserID());
		String now = DateUtils.getNow("yyyy-MM-dd HH:mm:ss");
		comment.put("create_time", now);
		comment.save();
		// 更新评论数
		new HomeService().updateCommentCount(comment.getInt("article_id"));

		json.put("comment_id", comment.getInt("id"));
		json.put("title_url", user.getStr("title_url"));
		json.put("reply_userid", comment.getInt("reply_userid"));
		json.put("reply_username", UserCache.getUser(comment.getInt("reply_userid")).getUserName());
		json.put("create_id", user.getUserID());
		json.put("create_name", user.getUserName());
		json.put("create_time", now);
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

	/**
	 * 获取最新评论数
	 * 
	 * 2015年3月16日 下午5:33:55 flyfox 330627517@qq.com
	 */
	public void count() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		if (user == null) {
			json.put("msg", "没有登陆，无法获取评论数！");
			renderJson(json.toJSONString());
			return;
		}

		String sql = "select count(*) AS cnt from tb_comment t " //
				+ " where t.reply_userid = ? " //
				+ " and status in (" + CommentContants.STATUS_NO_READ //
				+ "," + CommentContants.STATUS_REPLY_NO_READ + ") ";
		TbComment obj = TbComment.dao.findFirst(sql, user.getUserid());
		// 更新状态为已读
		Object cnt = obj.get("cnt");
		if (cnt == null) {
			json.put("msg", "没有登陆，评论数获取失败！");
			renderJson(json.toJSONString());
			return;
		}

		json.put("count", cnt.toString());
		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}
}
