package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
	public UserVo get(Long no) {
		return sqlSession.selectOne("user.getByNo",no);
	}
	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail",email);
	}
	public UserVo get(UserVo userVo) throws UserDaoException {
		UserVo result = sqlSession.selectOne("user.getByEamilAndPassword",userVo);
		return result;
	}
	public List<UserVo> getList(){
		
		return sqlSession.selectList("user.getList");
	}
//	private Connection getConnection() throws SQLException, ClassNotFoundException {
//		Connection conn = null;
//
//			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/webdb";
//			conn = DriverManager.getConnection(url,"webdb","webdb");
//
//		return conn;
//	}
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert",vo);
		return count ==1;
	}
	
	
	public boolean update(UserVo vo) {

		int count = sqlSession.update("user.update",vo);
		return count ==1;
	}
}