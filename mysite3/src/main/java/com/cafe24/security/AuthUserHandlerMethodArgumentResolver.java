package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer, 
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
		if(supportsParameter(parameter)==false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		//@AuthUser가 붙어 있고 파라미터 타입이 UserVo
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
//		dsafasdf = request.getParameter("newPassword");
		
		if(session == null) {
			return null;
		}
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		//1.파라미터에 @AuthUser가 붙어 있는지 확인
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		
		//2. @AuthUser가 안붙어 있는경우
		if(authUser == null)
			return false;
		
		//3. Type이 UserVo가 아님
		if(parameter.getParameterType().equals(UserVo.class)==false) {
			return false;
		}
	
		return true;
	}	

}
