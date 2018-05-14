package com.cafe24.mysite.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller

@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo,BindingResult result) {
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error : list) {
//				System.out.println("Object Error:" + error);
//			}
			return "user/join";
		}
		// userDao.insert(vo);
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
	
		return "user/login";
	}

//	@RequestMapping(value = "/auth", method = RequestMethod.POST)
//	public String login(HttpSession session, @ModelAttribute UserVo vo, Model model) {
//
//		// UserVo authUser = userDao.get(vo.getEmail(), vo.getPassword());
//		UserVo authUser = userService.getUser(vo);
//		if (authUser == null) {
//			model.addAttribute("result", "fail");
//			return "user/login";
//		}
//
//		session.setAttribute("authUser", authUser);
//		return "redirect:/main";
//	}

//	@RequestMapping(value = "/logout")
//	public String logout(HttpSession session, @ModelAttribute UserVo vo, Model model) {
//
//		session.removeAttribute("authUser");
//		session.invalidate();
//
//		
//
//		return "redirect:/main";
//	}
	@Auth()
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo vo) {

		// 접근제어
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		if (authUser == null)
		
		if(vo==null)
			return "redirect:/main";

		return "user/modify";
	}

//	@Auth는 인증,권한부여 를 위해 썼고, 
//@AuthUser는 콘트롤러에서 HttpSession을 사용하지 않고 아규먼트리졸버를 통해서 authUser 정보 가지고옴
	@Auth() //
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute UserVo tmpUser , @AuthUser UserVo authUser, String newPassword) {
		// 접근제어
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		if (authUser == null)
//			return "redirect:/main";

		if (userService.modify(tmpUser, authUser,newPassword)) { // 기존 비밀번호와 일치한 경우
			return "redirect:/user/modifysuccess";
		}
		return "user/modify"; // 기존 비밀 번호 틀린경우
	}

	@RequestMapping(value = "/modifysuccess", method = RequestMethod.GET)
	public String modifysuccess() {
		return "user/modifysuccess";
	}

	
	/*
	 * @ExceptionHandler( UserDaoException.class ) public String
	 * handleUserDaoException() { 로그남기기 return "error/error"; }
	 */
}
