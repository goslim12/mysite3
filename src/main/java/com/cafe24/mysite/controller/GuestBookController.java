package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.GuestBookService;
import com.cafe24.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

	@Autowired
	private GuestBookService guestbookService;
	
	@RequestMapping()
	public String list(Model model) {
		List<GuestBookVo> list = guestbookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String list(@ModelAttribute GuestBookVo vo) {
		guestbookService.insert(vo);
		return "redirect:/guestbook";
	}
	@RequestMapping(value="/delete" ,method = RequestMethod.POST)
	public String delete( @ModelAttribute GuestBookVo vo) {
		guestbookService.delete(vo);
		return "redirect:/guestbook";
	}
	@RequestMapping("/ajax")
	public String ajax() {
		return "guestbook/index-ajax";
	}
	@RequestMapping(value="/delete" ,method = RequestMethod.GET)
	public String delete(Model model,Long no) {
		return "guestbook/delete";
	}
}
