package com.flyfox.modules.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSONObject;
import com.flyfox.component.util.ArticleCountCache;
import com.flyfox.component.util.ImageCode;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.comment.TbComment;
import com.flyfox.modules.folder.TbFolder;
import com.flyfox.modules.user.SysUser;
import com.flyfox.modules.user.UserCache;
import com.flyfox.util.DateUtils;
import com.flyfox.util.StrUtils;
import com.jfinal.plugin.activerecord.Db;

@ControllerBind(controllerKey = "/web")
public class Home extends BaseController {

	private static final String path = "/pages/web/";

	public void index() {
		Integer folder_id = getParaToInt();
		if (folder_id == null || folder_id <= 0) {
			folder_id = TbFolder.ROOT;
		}
		// 根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));

		// 目录列表
		List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
		setAttr("folders", folders);
		setAttr("folders_selected", folder_id);
		// 数据列表,只查询展示的和类型为11,12的
		List<TbArticle> articles = TbArticle.dao.findByWhere(" where status = 1 and type in (11,12) " //
				+ "and folder_id = ? " //
				+ "order by sort,create_time desc", folder_id);
		setAttr("list", articles);

		renderAuto(path + "home.html");

	}

	/**
	 * 查看文章
	 * 
	 * 2015年2月26日 下午1:46:14 flyfox 330627517@qq.com
	 */
	public void showArticle() {
		// 根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));
		// 数据列表
		int articleId = getParaToInt();
		TbArticle article = TbArticle.dao.findById(articleId);
		if (article != null) {
			// 更新浏览量
			String key = getSessionAttr(JFlyFoxUtils.USER_KEY);
			if (key != null) {
				ArticleCountCache.addCountView(article, key);
			}

			setAttr("item", article);

			List<TbComment> listComment = TbComment.dao.findByWhere( //
					" where article_id = ? order by create_time desc ", articleId);
			setAttr("listComment", listComment);
		}

		renderAuto(path + "show_article.html");

	}

	/**
	 * 保存评论
	 */
	public void comment_del() {
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
		comment.deleteById(id);
		// 更新评论数
		updateCommentCount(comment.getInt("article_id"));

		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

	/**
	 * 保存评论
	 */
	public void comment_save() {
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
		updateCommentCount(comment.getInt("article_id"));

		json.put("comment_id", comment.getInt("id"));
		json.put("title_url", user.getStr("title_url"));
		json.put("create_name", user.getUserName());
		json.put("create_time", now);
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

	private void updateCommentCount(int article_id) {
		String sql = "update tb_article set count_comment = " //
				+ "(select count(*) from tb_comment where article_id = ? ) where id = ? ";
		Db.update(sql, article_id, article_id);
	}

	/**
	 * 登陆
	 */
	public void login() {
		setAttr("pre_page", getPrePage());
		render(CommonController.loginPage);
	}

	/**
	 * 登出
	 */
	public void logout() {
		removeSessionUser();
		redirect(getPrePage());
	}

	public String getPrePage() {
		return getRequest().getHeader("Referer");
	}

	/**
	 * 我的消息
	 */
	public void message() {
		index();
	}

	/**
	 * 个人信息
	 */
	public void person() {
		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		if (user == null) {
			index();
		} else {
			setAttr("model", user);

			// 目录列表
			List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
			setAttr("folders", folders);
			setAttr("folders_selected", "person");

			renderAuto(path + "show_person.html");
		}
	}

	/**
	 * 个人信息保存
	 */
	public void person_save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		int userid = user.getInt("userid");
		SysUser model = getModel(SysUser.class);

		if (userid != model.getInt("userid")) {
			json.put("msg", "提交数据错误！");
			renderJson(json.toJSONString());
			return;
		}

		// 第三方用户不需要密码
		if (!user.getStr("usertype").equals("4")) {
			String oldPassword = getPara("old_password");
			String newPassword = getPara("new_password");
			String newPassword2 = getPara("new_password2");
			if (!user.getStr("password").equals(JFlyFoxUtils.passwordEncrypt(oldPassword))) {
				json.put("msg", "密码错误！");
				renderJson(json.toJSONString());
				return;
			}
			if (StrUtils.isNotEmpty(newPassword) && !newPassword.equals(newPassword2)) {
				json.put("msg", "两次新密码不一致！");
				renderJson(json.toJSONString());
				return;
			} else if (StrUtils.isNotEmpty(newPassword)) { // 输入密码并且一直
				model.set("password", JFlyFoxUtils.passwordEncrypt(newPassword));
			}
		}

		if (StrUtils.isNotEmpty(model.getStr("email")) && model.getStr("email").indexOf("@") < 0) {
			json.put("msg", "email格式错误！");
			renderJson(json.toJSONString());
			return;
		}

		model.update();
		UserCache.init(); // 设置缓存
		SysUser newUser = SysUser.dao.findById(userid);
		setSessionUser(newUser); // 设置session
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

	/**
	 * 生成注册码
	 * 
	 * 2015年2月28日 下午1:50:25
	 * flyfox 330627517@qq.com
	 */
	public void image_code(){
		try {
			new ImageCode().doGet(getRequest(), getResponse());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderNull();
	}
	
	/**
	 * 注册
	 */
	public void regist() {
		// 目录列表
		List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
		setAttr("folders", folders);
		setAttr("folders_selected", "regist");

		String prePage = getPara("pre_page");
		if (StrUtils.isEmpty(prePage)) {
			prePage = getPrePage();
		}
		setAttr("pre_page", prePage);

		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		// 如果已经登陆了~您就别注册啦
		if (user != null) {
			redirect(prePage);
		} else {
			renderAuto(path + "show_regist.html");
		}
	}

	/**
	 * 注册信息保存
	 */
	public void regist_save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getModel(SysUser.class);
		String password = getPara("password");
		String password2 = getPara("password2");
		String key = user.getStr("email");
		
		// 获取验证码
		String imageCode = getSessionAttr(ImageCode.class.getName());
		String checkCode = this.getPara("imageCode");
		
		if (StrUtils.isEmpty(imageCode) || !imageCode.equalsIgnoreCase(checkCode)) {
			json.put("msg", "验证码错误！");
			renderJson(json.toJSONString());
			return;
		}
		
		if (StrUtils.isEmpty(key) || key.indexOf("@") < 0) {
			json.put("msg", "email格式错误！");
			renderJson(json.toJSONString());
			return;
		}

		if (user.getInt("userid") != null || StrUtils.isEmpty(user.getStr("realname")) //
				|| StrUtils.isEmpty(password) || StrUtils.isEmpty(password2) //
				|| password.length() < 6 || !password.equals(password2)) {
			json.put("msg", "提交数据错误！");
			renderJson(json.toJSONString());
			return;
		}

		SysUser newUser = SysUser.dao.findFirstByWhere("where username = ? ",key);
		if (newUser!=null) {
			json.put("msg", "邮箱已存在，请重新输入");
			renderJson(json.toJSONString());
			return;
		}
		
		user.set("username", key);
		user.set("password", JFlyFoxUtils.passwordEncrypt(password));
		user.set("usertype", "3");
		user.put("create_id", 1);
		user.put("create_time", DateUtils.getNow());
		user.save();
		UserCache.init(); // 设置缓存
		setSessionUser(user); // 设置session
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}
}
