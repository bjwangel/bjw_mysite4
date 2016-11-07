package com.bit2016.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.UserDao;
import com.bit2016.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void join( UserVo vo ) {
		userDao.insert(vo);
	}
	
	public UserVo login( String email, String password ) {
		System.out.println("전달 받음");
		UserVo userVo = null;
		userVo = userDao.get(email, password);
		System.out.println("다오 로그인");
		return userVo;
	}
	
	public UserVo getUser( Long no ) {
		return userDao.get(no);
	}
	
	public void updateUser( UserVo vo ) {
		userDao.update(vo);
	}
}