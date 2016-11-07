package com.bit2016.mysite.vo;

public class GuestBookVo {
	private Long no;
	private String name;
	private String content;	
	private String date;
	private String passWord;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Override
	public String toString() {
		return "Guestbook [no=" + no + ", name=" + name + ", content=" + content + ", date=" + date + ", passWord="
				+ passWord + "]";
	}
	
}
