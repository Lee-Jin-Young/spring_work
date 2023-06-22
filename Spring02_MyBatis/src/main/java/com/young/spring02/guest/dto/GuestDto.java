package com.young.spring02.guest.dto;

public class GuestDto {
	private int num;
	private String content;
	private String writer;
	private String pwd;
	private String regdate;
	
	public GuestDto() {
		
	}
	
	public GuestDto(int num, String content, String writer, String pwd, String regdate) {
		super();
		this.num = num;
		this.content = content;
		this.writer = writer;
		this.pwd = pwd;
		this.regdate = regdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
