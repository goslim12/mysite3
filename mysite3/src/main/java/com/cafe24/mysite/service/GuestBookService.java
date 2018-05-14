package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestBookDao;
import com.cafe24.mysite.vo.GuestBookVo;
import com.cafe24.mysite.vo.UserVo;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao guestbookDao;
	
	
	public List<GuestBookVo> getList() {
		return guestbookDao.getList();
	}
	public List<GuestBookVo> getMessageList(Long no) {
		return guestbookDao.getList(no);
	}
	public void insert(GuestBookVo vo) {
		guestbookDao.insert(vo);
	}
	
	
	public GuestBookVo insertMessage2(GuestBookVo guestBookVo) {
		GuestBookVo vo = null;
		boolean count = guestbookDao.insert(guestBookVo);
		if(count == true) {
			vo = guestbookDao.get(guestBookVo.getNo());
		}
		return vo;
	}
	
	public boolean delete(GuestBookVo vo) {
		return guestbookDao.delete(vo);
	}
//	public UserVo getUser(GuestBookVo vo) {
//		return 	userDao.get(vo.getEmail(), vo.getPassword());
//	}
//	public boolean modify(UserVo vo) {
//		UserVo tempAuthUser = userDao.get(vo.getEmail(),vo.getPassword());
//		if(tempAuthUser!=null) {
//			userDao.update(vo);
//			return true;
//		}
//		return 	false;
//	}
}
