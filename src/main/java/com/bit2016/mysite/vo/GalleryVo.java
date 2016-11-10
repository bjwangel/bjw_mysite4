package com.bit2016.mysite.vo;

public class GalleryVo {
	private Long no;
	private String org_file_name;
	private String save_file_name;
	private String comments;
	private String file_ext_name;
	private String reg_date;
	private Long user_no;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrg_file_name() {
		return org_file_name;
	}
	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFile_ext_name() {
		return file_ext_name;
	}
	public void setFile_ext_name(String file_ext_name) {
		this.file_ext_name = file_ext_name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", org_file_name=" + org_file_name + ", sava_file_name=" + save_file_name
				+ ", comments=" + comments + ", file_ext_name=" + file_ext_name + ", reg_date=" + reg_date
				+ ", user_no=" + user_no + "]";
	}
	
	
}
