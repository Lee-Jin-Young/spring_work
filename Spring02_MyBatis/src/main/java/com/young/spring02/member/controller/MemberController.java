package com.young.spring02.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring02.member.dao.MemberDao;
import com.young.spring02.member.dto.MemberDto;

@Controller
public class MemberController {
	@Autowired
	private MemberDao dao;
	
	/* 
	 * 매개변수의 이름이 받는 파라미터 이름과 다를경우
	 * @RequestParam(value = "num")를 이용해야함
	 * 같을 경우 생략 가능
	 * 
	 * @RequestMapping에 Method속성의 값을 명시하지 않을 경우
	 * 경로가 맞으면 모두 처리한다.
	 * 
	 * method 속성의 값을 명시하면 요청 방식이 다를 경우 처리하지 않는다.
	 * POST 방식일 경우 요청방식을 명시하는것을 지향한다.
	*/

	// 회원 추가 요청 처리
	//public void insert(MemberDto dto);
	@RequestMapping("/member/insert")
	public String insert(MemberDto dto) {
		dao.insert(dto);
		return "member/insert";
	}

	// 회원 추가 폼 요청 처리
	@RequestMapping("/member/insertform")
	public String insertform() {
		return "member/insertform";
	}
	
	// 회원 정보 수정 요청 처리
	//public void update(MemberDto dto);
	@RequestMapping(method = RequestMethod.POST, value = "/member/update")
	public String update(MemberDto dto) {
		dao.update(dto);
		return "member/update";
	}
	
	// 회원 수정 폼 요청 처리
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(ModelAndView mView, int num) {
		MemberDto dto = dao.getData(num);
		mView.addObject("dto", dto);
		mView.setViewName("member/updateform");
		return mView;
	}
	
	// 회원 정보 삭제 요청 처리
	// public void delete(int num);
	@RequestMapping("/member/delete")
	public String delete(int num) {
		dao.delete(num);
		return "/redirect:/member/list";
	}
	 
	// 회원 정보 보기 요청 처리
	//public MemberDto getData(int num);
	@RequestMapping("/member/data")
	public String data(int num) {
		MemberDto dto = dao.getData(num);
		return "dto";
	}
	
	//public List<MemberDto> getList();
	// 회원 목록 보기 요청 처리
	@RequestMapping("/member/list")
	public String list(HttpServletRequest request) {
		List<MemberDto> list = dao.getList();
		request.setAttribute("list", list);
		// /WEBf-INF/views/member/list.jsp 페이지로 forward 이동해서 응답
		return "member/list";
	}
}