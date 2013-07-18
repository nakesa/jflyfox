package com.flyfox.component.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.flyfox.component.IComponent;
import com.flyfox.component.util.NumberUtils;

public class Config implements IComponent {

	private static final Logger logger = Logger.getLogger(Config.class);
	private static final String CONFIG_FILE = "config.properties";
	private static Properties props = null;

	public Config() {
	}

	public boolean start() {
		if (props == null) {
			logger.info("##Component Config init......\r\n " + CONFIG_FILE + " Properties Object init........");
			props = parsePropertyFile(CONFIG_FILE);
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
			java.io.InputStream propFile = getClass().getResourceAsStream(file);
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
