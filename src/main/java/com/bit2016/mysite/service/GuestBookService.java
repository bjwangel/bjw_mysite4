package com.bit2016.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.GuestBookDao;
import com.bit2016.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDao guestbookDao;
	
	public List<GuestBookVo> getMessageList(){
		return guestbookDao.getList();
	}
	
	public List<GuestBookVo> getMessageList(int page){
		return guestbookDao.getList(page);
	}
	
	public void deleteMessage(GuestBookVo vo){
		guestbookDao.delete(vo);
	}
	public void writeMessage(GuestBookVo vo){
		Long no=guestbookDao.insert(vo);
		System.out.println(no);
	}
	
	public GuestBookVo writeMessage( GuestBookVo vo, boolean fetch ) {
		GuestBookVo guestbookVo = null;
		
		Long no = guestbookDao.insert(vo);
		if( fetch ) {
			guestbookVo = guestbookDao.get( no );
		}
		
		return guestbookVo;
	}
	public GuestBookVo writeMessage2(GuestBookVo vo){
		Long no=guestbookDao.insert(vo);
		System.out.println(no);
		return null;
	}
}
