package com.example.aop.util;

import org.springframework.stereotype.Component;

@Component
public class WritingUtil {
	public WritingUtil() {
		System.out.println("WritingUtil 생성자");
	}
	
	public void writeLetter() {

		System.out.println("편지 작성");
		
	}
	
	public void writeReporte() {
		
		System.out.println("보고서 작성");
		
	}
	
	public void writeDiary() {
		
		System.out.println("일기 작성");
		
	}
}
