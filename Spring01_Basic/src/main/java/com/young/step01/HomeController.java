package com.young.step01;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//프로젝트의 최상위 경로 요청이 오면 /WEB-INF/views/home.jsp페이지로 forward 응답
	//"home"이라는 문자열을 리턴하면 앞에 "/WEB-INF/views/" 뒤에 ".jsp"를 더한
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		List<String> noticeList = new ArrayList<String>();
		noticeList.add("hello");
		return "home";
	}
	
}
