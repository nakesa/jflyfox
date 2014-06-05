package com.flyfox.component.route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.flyfox.util.annotation.ClassSearcher;
import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.kit.StringKit;
import com.jfinal.log.Logger;

/**
 * 自动绑定（来自jfinal ext)
 * 1.如果没用加入注解，必须以Controller结尾,自动截取前半部分为key
 * 2.加入ControllerBind的 获取 key
 * 
 * @author zb
 * 2014-3-20
 */
public class AutoBindRoutes extends Routes {

	protected final Logger logger = Logger.getLogger(getClass());

	private List<Class<? extends Controller>> excludeClasses =  new ArrayList<Class<? extends Controller>>();

	private List<String> includeJars = new ArrayList<String>();

	private boolean autoScan = true;

	private String suffix = "Controller";

	public AutoBindRoutes() {
	}

	public AutoBindRoutes(boolean autoScan) {
		this.autoScan = autoScan;
	}

	public AutoBindRoutes addJar(String jarName) {
		if (StringKit.notBlank(jarName)) {
			includeJars.add(jarName);
		}
		return this;
	}

	public AutoBindRoutes addJars(String jarNames) {
		if (StringKit.notBlank(jarNames)) {
			addJars(jarNames.split(","));
		}
		return this;
	}

	public AutoBindRoutes addJars(String[] jarsName) {
		includeJars.addAll(Arrays.asList(jarsName));
		return this;
	}

	public AutoBindRoutes addJars(List<String> jarsName) {
		includeJars.addAll(jarsName);
		return this;
	}

	public AutoBindRoutes addExcludeClass(Class<? extends Controller> clazz) {
		if (clazz != null) {
			excludeClasses.add(clazz);
		}
		return this;
	}

	public AutoBindRoutes addExcludeClasses(Class<? extends Controller>[] clazzes) {
		excludeClasses.addAll(Arrays.asList(clazzes));
		return this;
	}

	public AutoBindRoutes addExcludeClasses(List<Class<? extends Controller>> clazzes) {
		excludeClasses.addAll(clazzes);
		return this;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void config() {
		List<Class<? extends Controller>> controllerClasses = ClassSearcher.findInClasspathAndJars(Controller.class,
				includeJars);
		ControllerBind controllerBind = null;
		for (Class controller : controllerClasses) {
			if (excludeClasses.contains(controller)) {
				continue;
			}
			controllerBind = (ControllerBind) controller.getAnnotation(ControllerBind.class);
			if (controllerBind == null) {
				if (!autoScan) {
					continue;
				}
				this.add(controllerKey(controller), controller);
				logger.debug("routes.add(" + controllerKey(controller) + ", " + controller.getName() + ")");
			} else if (StringKit.isBlank(controllerBind.viewPath())) {
				this.add(controllerBind.controllerKey(), controller);
				logger.debug("routes.add(" + controllerBind.controllerKey() + ", " + controller.getName() + ")");
			} else {
				this.add(controllerBind.controllerKey(), controller, controllerBind.viewPath());
				logger.debug("routes.add(" + controllerBind.controllerKey() + ", " + controller + ","
						+ controllerBind.viewPath() + ")");
			}
		}
	}

	private String controllerKey(Class<Controller> clazz) {
		if (!clazz.getSimpleName().endsWith(suffix)) {
			throw new RuntimeException(" does not has a ControllerBind annotation and it,s name is not end with "
					+ suffix);
		}
		String controllerKey = "/" + StringKit.firstCharToLowerCase(clazz.getSimpleName());
		controllerKey = controllerKey.substring(0, controllerKey.indexOf(suffix));
		return controllerKey;
	}

	public List<Class<? extends Controller>> getExcludeClasses() {
		return excludeClasses;
	}

	public void setExcludeClasses(List<Class<? extends Controller>> excludeClasses) {
		this.excludeClasses = excludeClasses;
	}

	public List<String> getIncludeJars() {
		return includeJars;
	}

	public void setIncludeJars(List<String> includeJars) {
		this.includeJars = includeJars;
	}

	public void setAutoScan(boolean autoScan) {
		this.autoScan = autoScan;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
