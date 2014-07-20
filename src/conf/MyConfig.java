package conf;

import com.flyfox.jfinal.config.JflyfoxConfig;
import com.flyfox.modules.column.ColumnCache;
import com.flyfox.modules.dict.DictCache;
import com.flyfox.modules.user.UserCache;
import com.flyfox.modules.user.UserInterceptor;
import com.jfinal.config.Interceptors;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;

/**
 * API引导式配置
 */
public class MyConfig extends JflyfoxConfig {



	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		// 用户认证
		me.add(new UserInterceptor());
		// session model转换
		me.add(new SessionInViewInterceptor());
	}


	/**
	 * 初始化
	 */
	@Override
	public void afterJFinalStart() {
		reset();
	}

	public static void reset() {
		DictCache.init();
		UserCache.init();
		ColumnCache.init();
	}

	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebContent", 80, "/", 5);
	}
}
