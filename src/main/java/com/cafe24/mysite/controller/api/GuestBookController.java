package com.cafe24.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.GuestBookService;
import com.cafe24.mysite.vo.GuestBookVo;


@Controller("guestbookAPIController")
@RequestMapping("/api/guestbook")
public class GuestBookController {

	@Autowired
	private GuestBookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list(@RequestParam(value="no",required=true, defaultValue="0") Long no) {
//		List<GuestBookVo> list = guestbookDao.getList();
//		return JSONResult.success(list);
		List<GuestBookVo> list = guestbookService.getMessageList(no);
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public JSONResult insert(@RequestBody GuestBookVo vo) {
		GuestBookVo guestBookVo =  guestbookService.insertMessage2(vo);
		return JSONResult.success(guestBookVo);
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(@ModelAttribute GuestBookVo vo) {
		boolean result = guestbookService.delete(vo);
		return JSONResult.success(result ?vo.getNo() :-1);
//		guestbookService.delete(vo);
//		return JSONResult.success(vo.getNo());
	}
}
