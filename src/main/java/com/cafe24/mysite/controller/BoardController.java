package com.cafe24.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping()
	public String list(String p,String kwd,Model model) {
		Map<String, Object> map = boardService.getList(p,kwd);
		model.addAttribute("map", map);
		return "board/list";
	}
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write( ) {
		return "board/write";
	}
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardVo vo ) {
		if(!"".equals(vo.getTitle())) // 제목이 없는경우는 제외
			boardService.write(vo);

		return "redirect:/board";
	}
	@Auth
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@ModelAttribute BoardVo vo) {
		boardService.delete(vo);
		return "redirect:/board";
	}
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(@ModelAttribute BoardVo vo,Model model) {
		model.addAttribute("vo", vo);
		vo = boardService.view(vo);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	@Auth
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modifyForm(@AuthUser UserVo authUser, @ModelAttribute BoardVo vo,Model model) {
		model.addAttribute("vo",boardService.getSameNoVo(vo));
		return "board/modify";
	}
	
	@Auth()
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo,Model model) {
		vo = boardService.modify(vo);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	@Auth
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String replyform(@ModelAttribute BoardVo vo,Model model) {
		model.addAttribute("vo",boardService.getSameNoVo(vo));
		return "board/reply";
	}
	
	@Auth
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(@ModelAttribute BoardVo vo,Model model) {
		boardService.reply(vo);
		return "redirect:/board/";
	}
}
