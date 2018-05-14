package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestBookVo;
import com.cafe24.mysite.vo.BoardVo;
@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getList(){
		List<BoardVo> list = sqlSession.selectList("board.getList");
		return list;
	}

	public boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert",vo);

		return count==1;
	}
	
	public boolean delete(BoardVo vo) {
		int count = sqlSession.delete("board.delete",vo);
		return count==1;
	}
	public Long getMaxGroupNo(){
		return sqlSession.selectOne("board.getVoMaxGroupNo");
	}
//	public BoardVo getVoMaxOrderNo(Long groupNo){
//		return sqlSession.selectOne("boardvo.getVoMaxOrderNo");
//	}
	public BoardVo getSameNoVo(Long num){
		return sqlSession.selectOne("board.getSameNoVo",num);
	}

	public boolean upHit(BoardVo vo) {
		int count = sqlSession.update("board.update",vo);
		return count ==1;
	}

	public boolean modify(BoardVo vo) {
		int count = sqlSession.update("board.modify",vo);
		return count==1;
	}

	public boolean upOrderNo(BoardVo vo) {
		int count = sqlSession.update("board.upOrderNo",vo);
		return count==1;
	}



}