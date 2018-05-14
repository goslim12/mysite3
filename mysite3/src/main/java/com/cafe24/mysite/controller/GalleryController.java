package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.vo.UserVo;

@Controller()
@RequestMapping("/gallery")
public class GalleryController {
	
	@RequestMapping()
	public String main() {
		return "gallery/index";
	}

}
