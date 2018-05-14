package com.cafe24.security;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

//	@Autowired
//	private UserService userService;
	
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response, 
			Object handler)
			throws Exception {
//		return super.preHandle(request, response, handler);

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password); //아직 암호화 안된 패스워드
		
		ApplicationContext ac = 
		WebApplicationContextUtils.
		getWebApplicationContext(
				request.getServletContext()
				);  // 웹어플리케이션컨텍스트 객체 가져오기
		UserService userService = ac.getBean(UserService.class);
//		UserService userService = 
		UserVo authUser = userService.getUser(vo); //
		if(authUser == null) {
			request.setAttribute("result", "fail");
//			response.sendRedirect(request.getContextPath()+"/user/login");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
			rd.forward(request, response);
			return false;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath()+"/main");
		return false;
	}

}
