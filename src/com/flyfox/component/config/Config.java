package com.flyfox.component.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.flyfox.component.IComponent;
import com.flyfox.component.util.NumberUtils;
import com.jfinal.kit.PathKit;

public class Config implements IComponent {

	private static final Logger logger = Logger.getLogger(Config.class);
	private static final String CONFIG_FILE = "config.properties";
	private static Properties props = null;

	public Config() {
	}

	public boolean start() {
		if (props == null) {
			String configFile = PathKit.getWebRootPath() + File.separator + "WEB-INF" + File.separator + CONFIG_FILE;
			logger.info("##Component Config init......\r\n （" + configFile + "） Properties Object init........");
			props = parsePropertyFile(configFile);
		}
		return props != null;
	}

	public static Integer getToInt(String paramName) {
		return NumberUtils.parseInt(get(paramName));
	}

	public static String get(String paramName) {
		return props.getProperty(paramName);
	}

	private Properties parsePropertyFile(String file) {
		Properties props = new Properties();
		try {
			// InputStream propFile = getClass().getResourceAsStream(file); // 相对路径
			InputStream propFile = new FileInputStream(new File(file)); // 绝对路径
			props.load(propFile);
			return props;
		} catch (IOException e) {
			logger.error("file read fail:" + file);
			e.printStackTrace();
		}
		return props;
	}

	public boolean stop() {
		return false;
	}

}
