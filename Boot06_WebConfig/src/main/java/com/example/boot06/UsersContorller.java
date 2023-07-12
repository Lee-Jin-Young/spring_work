package com.example.boot06;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersContorller {
	@GetMapping("/users/login_form")
	public String loginForm() {
		return "users/login_form";
	}
	
	@PostMapping("/users/login")
	public String login(HttpSession session, String id) {
		session.setAttribute("id", id);
		return "redirect:/";
	}
	
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/users/info")
	public String info(Model model) {
		String address = "강남";
		model.addAttribute("address",address);
		/*
		 * 컨트롤러의 매개변수로 전달된 Model 객체의 addAttribure() 메소드를 이용하여
		 * view page에 전달할 내용을 담으면 알아서 HttpServletRequest객체에 담긴다.
		 * 또한 컨트롤러의 return type으로 리턴하지 않아도 동작한다.
		 */
		
		return "users/info";
	}
}
