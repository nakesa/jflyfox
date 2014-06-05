package com.flyfox.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.flyfox.base.model.Paginator;
import com.flyfox.modules.user.SysUser;
import com.flyfox.util.HandlerUtils;
import com.flyfox.util.StrUtils;
import com.jfinal.core.Controller;

/**
 * Controller 不能初始化
 * 
 * @author flyfox
 * @date 2013-10-20
 */
public abstract class BaseController extends Controller {

	protected static final String page_message = "/pages/common/message.jsp";
	private static final Logger log = Logger.getLogger(BaseController.class);
	public static final String SESSION_NAME = "userContent";
	public static final String MODEL_NAME = "model";

	protected void renderMessage(String message) {
		renderMessage(message, "closeIframe();");
	}

	protected void renderMessageByFailed(String message) {
		renderMessage(message, "history.back();");
	}

	protected void renderMessage(String message, String obj) {
		String script = "";
		if (StrUtils.isEmpty(obj)) { // 关闭页面
			script = "closeIframe();";
		} else if (script.endsWith(".jsp")) { // 页面跳转
			script = "window.location.href = \"" + obj + "\"";
		} else { // 直接执行JS
			script = obj;
		}
		setAttr("msg", message);
		setAttr("script", script);
		render(page_message);
	}

	protected void render500(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/pages/error/500.jsp").forward(request, response);
		} catch (Exception e) {
			log.error("500 page -->", e);
		}
	}

	protected <T> T handler(Class<T> modelClass) {
		Object o = null;
		try {
			o = modelClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return handler(o);
	}

	@SuppressWarnings("unchecked")
	protected <T> T handler(Object o) {
		if (o == null) {
			return null;
		}
		return (T) HandlerUtils.handler(getRequest(), o);
	}

	public SysUser getSessionUser() {
		return getSessionAttr(SESSION_NAME);
	}

	public SysUser setSessionUser(SysUser user) {
		setSessionAttr(SESSION_NAME, user);
		return user;
	}

	public void removeSessionUser() {
		removeSessionAttr(SESSION_NAME);
	}

	public Paginator getPaginator() {
		Paginator paginator = new Paginator();
		Integer pageNo = getParaToInt("pageNo");
		if (pageNo != null && pageNo > 0) {
			paginator.setPageNo(pageNo);
		}
		Integer pageSize = getParaToInt("recordsperpage");
		if (pageSize != null && pageSize > 0) {
			paginator.setPageSize(pageSize);
		}
		return paginator;
	}

	public Object[] toArray(List<Object> list) {
		return list.toArray(new Object[list.size()]);
	}

	@Override
	public <T> T getModel(Class<T> modelClass) {
		return super.getModel(modelClass, MODEL_NAME);
	}

}
