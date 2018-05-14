package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardUsersJoinVo;
@Repository
public class BoardUsersDto {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardUsersJoinVo> getList(Long start,Long numberPerPage,String kwd){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("kwd", "%"+kwd+"%");
		map.put("start", start*numberPerPage);
		map.put("numberPerPage", numberPerPage);
		return sqlSession.selectList("boarddto.getList",map);
	}

	
}