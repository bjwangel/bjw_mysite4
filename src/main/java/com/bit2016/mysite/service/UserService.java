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
	
	public UserVo login(UserVo vo) {
		System.out.println("전달 받음");
		
		UserVo userVo= userDao.get(vo);
		System.out.println("다오 로그인");
		return userVo;
	}
	
	public UserVo getUser( Long no ) {
		return userDao.get(no);
	}
	
	public void updateUser( UserVo vo ) {
		userDao.update(vo);
	}
	public boolean emailExist(String email){
		return (userDao.get(email)) != null ;
		
	}
}