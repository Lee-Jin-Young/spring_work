package com.example.aop.util;

public class Messenger {
	public void sendGreeting(String msg) {
		System.out.println("msg : " + msg);
	}
	
	public String getMessage() {
		System.out.println("getMessage() 메소드가 수행");
		return "return message";
	}
}
