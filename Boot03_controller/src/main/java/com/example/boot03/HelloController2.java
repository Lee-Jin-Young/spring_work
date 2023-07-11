package com.example.boot03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * 컨트롤러 메소드에서 리턴하는 내용을 바로 클라이언트에게 응답하는 컨트롤러
 * 일반 문자열, xml, json 형식의 문자열을 응답할 때 주로 사용한다.
 * 모든 컨트롤러 메소드에 @ResponseBody 어노테이션이 붙어있는것과 같다.
 */
@RestController
public class HelloController2 {
	
	@RequestMapping(method = RequestMethod.GET, value = "/hello2")
	public String hello2() {
		return "hello";
	}
	
	@GetMapping("/hello3")
	public String hello3() {
		return "bye";
	}
}
