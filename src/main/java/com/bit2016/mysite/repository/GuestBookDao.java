package com.bit2016.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.mysite.vo.GuestBookVo;
	

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void delete(GuestBookVo vo){
		sqlSession.delete("guestbook.delete",vo);
	}
	
	public Long insert(GuestBookVo vo){
		sqlSession.insert("guestbook.insert", vo);
		return vo.getNo();
	}
	
	public List<GuestBookVo> getList(){
		return sqlSession.selectList("guestbook.getList");
	}
	
	public List<GuestBookVo> getList(int page){
		return sqlSession.selectList("guestbook.getListByPage", page);
	}
	
	public GuestBookVo get( Long no ) {
		return sqlSession.selectOne( "guestbook.getByNo", no ); 
	}
}
	
		