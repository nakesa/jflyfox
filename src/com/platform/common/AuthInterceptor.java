package com.platform.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class AuthInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		System.out.println("AuthInterceptor....");
		ai.invoke();
	}

}
