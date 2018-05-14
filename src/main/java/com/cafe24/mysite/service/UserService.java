package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo vo) {
		userDao.insert(vo);
	}
	public UserVo getUser(UserVo vo) {
		return 	userDao.get(vo);
	}
	public UserVo getUser(Long no) {
		return 	userDao.get(no);
	}
	
	@Transactional
	public boolean modify(UserVo vo,UserVo authUser,String newPassword) {
		UserVo tempAuthUser = userDao.get(vo); //이메일과 비밀번호일치하는 유저가 있는지 확인
		if(tempAuthUser!=null) {
			vo.setPassword(newPassword);
			userDao.update(vo);
			authUser.setName(tempAuthUser.getName());
			authUser.setPassword(getUser(tempAuthUser.getNo()).getPassword());
			authUser.setName(tempAuthUser.getName());
			return true;
		}
		return 	false;
	}
}
