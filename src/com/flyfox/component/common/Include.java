package com.flyfox.component.common;

import org.apache.log4j.Logger;

import com.flyfox.component.IComponent;
import com.jfinal.core.JFinal;

public class Include implements IComponent {

	private static final Logger logger = Logger.getLogger(Include.class);
	public static String basePath = null;

	public boolean start() {
		if (basePath == null) {
			basePath = JFinal.me().getContextPath();
			logger.info("##Component Include init......\r\n  basepath set (" + basePath
					+ ") init........");
		}
		return basePath != null;
	}

	/**
	 * 所有引用
	 * 
	 * @autor flyfox
	 * @since Jul 14, 2013
	 * @return
	 */
	public static String index() {
		String path = ymprompt() + jquery() + quick() + main();
		return path;
	}

	/**
	 * main
	 * 
	 * @autor flyfox
	 * @since Jul 14, 2013
	 * @return
	 */
	public static String main() {
		String path = getStyle("/resources/style/main.css");
		return path;
	}

	/**
	 * 填出框组件
	 * 
	 * @autor flyfox
	 * @since Jul 14, 2013
	 * @return
	 */
	public static String ymprompt() {
		String path = getStyle("/component/extend/ymPrompt/skin/qq/ymPrompt.css") //
				+ getScript("/component/extend/ymPrompt/ymPrompt.js") //
				+ getScript("/component/extend/ymPrompt/common.js");
		return path;
	}

	/**
	 * Jquery
	 * 
	 * @autor flyfox
	 * @since Jul 14, 2013
	 * @return
	 */
	public static String quick() {
		return getStyle("/component/extend/quick/quick.css");
	}

	/**
	 * Jquery
	 * 
	 * @autor flyfox
	 * @since Jul 14, 2013
	 * @return
	 */
	public static String jquery() {
		return getScript("/component/extend/jquery/jquery-1.8.2.min.js");
	}

	private static String getScript(String path) {
		return "    <script type=\"text/javascript\" src=\"" + basePath + path + "\"></script>\n";
	}

	private static String getStyle(String path) {
		return "	<link rel=\"stylesheet\" id='skin' type=\"text/css\" href=\"" + basePath + path
				+ "\" />\n";
	}

	public boolean stop() {
		basePath = null;
		return true;
	}
}
