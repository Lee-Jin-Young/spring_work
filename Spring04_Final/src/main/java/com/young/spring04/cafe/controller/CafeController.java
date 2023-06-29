package com.young.spring04.cafe.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring04.cafe.dto.CafeDto;
import com.young.spring04.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService service;
	
	@RequestMapping("/cafe/list")
	public String list(HttpServletRequest request) {
		service.getList(request);
		
		return "cafe/list";
	}
	
	@RequestMapping("/cafe/insertform")
	public String insertform() {
		return "/cafe/insertform";
	}
	
	@RequestMapping("/cafe/insert")
	public String insert(CafeDto dto, HttpSession session) {
		String writer = (String)session.getAttribute("id");
		dto.setWriter(writer);
		service.saveContent(dto);
		
		return "cafe/insert";
	}
	
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(HttpServletRequest request, ModelAndView mView) {
		service.getDetail(request);
		
		mView.setViewName("cafe/detail");
		return mView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cafe/updateform")
	public String updateform(HttpServletRequest request) {
		service.getData(request);
		
		return "/cafe/updateform";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cafe/update")
	public String update(CafeDto dto) {
		service.updateContent(dto);
		
		return "cafe/update";
	}
	
	@RequestMapping("/cafe/delete")
	public String delete(int num, HttpServletRequest request) {
		service.deleteContent(num, request);
		
		return "redirect:/cafe/list";
	}
}
