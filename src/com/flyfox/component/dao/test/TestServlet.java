package com.flyfox.component.dao.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.flyfox.util.DateUtils;

public class TestServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TestServlet.class);
	final Logger log = Logger.getLogger(getClass());
	private static final boolean DEBUG = true;
	protected static final int kb = 1024 * 1024;

	public TestServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		perform(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		perform(request, response);
	}

	public void perform(HttpServletRequest request, HttpServletResponse response) {
		try {
			long start = 0L, startMem = 0L;
			String now = "";
			if (DEBUG) {
				start = System.currentTimeMillis();
				startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				now = DateUtils.format(DateUtils.getCurrentTimestamp(), "yyyy-MM-dd HH:mm:ss");
				logger.info("[" + now + " start]\t剩余内存值：" + Runtime.getRuntime().freeMemory() / kb + "MB");
			}

			execute(request, response);
			

			if (DEBUG) {
				long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) - startMem;
				long time = System.currentTimeMillis() - start;

				String path = request.getServletPath() + "?" + request.getQueryString();
				long freeInt = Runtime.getRuntime().freeMemory() / kb;
				String log = "[" + now + " end]\t剩余内存值：" + freeInt + "MB\t访问路径:" + path + "\t运行时间：" + time / 1000L + "s\t运行占用内存:" + memory / kb
						+ "MB\t系统最大内存值：" + Runtime.getRuntime().maxMemory() / kb + "MB";
				logger.info(log);
			}
			
			forward(request, response, "/index.jsp");
		} catch (Throwable t) {
			logger.error("Not processed exception", t);
		}
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String path = request.getServletPath() + "?" + request.getQueryString();

		System.out.println("##########" + path);
	}

	/*
	 * public abstract void execute(HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Throwable;
	 */

	protected void forward(HttpServletRequest request, HttpServletResponse response, String page) {
		try {
			RequestDispatcher requestdispatcher = getServletContext().getRequestDispatcher(page);
			requestdispatcher.forward(request, response);
		} catch (Exception ex) {
			logger.error("Forward error:" + page, ex);
			throw new RuntimeException(ex);
		}
	}

	protected void forwardDirect(HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch (Exception ex) {
			logger.error("Forward error:", ex);
		}
	}

	/** *************重构获取方法 2012-05-15******************* */

	/**
	 * 从request中获取一个String型的参数，如果为空返回""
	 * 
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	protected String getPara(HttpServletRequest request, String name) {
		return getPara(request, name, "");
	}

	/**
	 * 从request中获取一个String型的参数，如果为空返回defaultValue
	 * 
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	protected String getPara(HttpServletRequest request, String name, String defaultValue) {
		String objValue = request.getParameter(name);
		String res = null;
		if (objValue == null) {
			res = defaultValue;
		} else {
			res = objValue;
		}
		return res;
	}

	/**
	 * 从request中获取一个Integer型的参数，如果为空返回0
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	protected Integer getParaInt(HttpServletRequest request, String name) {
		return getPara(request, name, 0);
	}

	/**
	 * 从request中获取一个Integer型的参数，如果为空返回defaultValue
	 * 
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	protected Integer getPara(HttpServletRequest request, String name, int defaultValue) {
		String objValue = request.getParameter(name);
		int res = 0;
		try {
			res = Integer.parseInt(objValue);
		} catch (Exception e) {
			res = defaultValue;
		}
		return res;
	}

}