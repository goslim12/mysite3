package com.cafe24.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor2 extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler
			)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyInterceptor2:preHandle");
		System.out.println(handler);
//		return super.preHandle(request, response, handler);
		return true;
	}

}
