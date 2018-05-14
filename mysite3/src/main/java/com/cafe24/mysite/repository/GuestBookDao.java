package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestBookVo;
@Repository
public class GuestBookDao {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}
	
	
	public List<GuestBookVo> getList(Long no){
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getList2",no);
		return list;
	}
	public boolean insert(GuestBookVo vo) {

		int count = sqlSession.insert("guestbook.insert", vo);
		return count==1;
	}
	public GuestBookVo get(Long no){
		return sqlSession.selectOne("guestbook.getByNo",no); 
	}
	public boolean delete(GuestBookVo vo) {
//		Map<Long,Object> map = new HashMap<Long,Object>();
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("no", vo.getNo());
//		map.put("password", vo.getPassword());
		int count = sqlSession.delete("guestbook.delete",vo);
		
		return count == 1;
	}
}