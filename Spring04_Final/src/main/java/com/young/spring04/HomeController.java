package com.young.spring04;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		List<String> noticeList=new ArrayList<String>();
		noticeList.add("가나다");
		noticeList.add("ABC");
		noticeList.add("123");

		request.setAttribute("noticeList", noticeList);

		return "home";
	}
	
	@RequestMapping("/play")
	public String play() {
		// /WEB-INF/views/play.jsp
		return "play";
	}
}