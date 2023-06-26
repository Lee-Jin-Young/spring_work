// UsersController.java
package com.young.spring04.users.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring04.users.dto.UsersDto;
import com.young.spring04.users.service.UsersService;

@Controller
public class UsersController {
    @Autowired
    private UsersService service;
    
    // 회원가입 폼 요청 처리
    @RequestMapping(method = RequestMethod.GET, value = "/users/signup_form")
    public String signupForm() {
        return "users/signup_form";
    }
    
    // 회원가입 요청 처리
    @RequestMapping("/users/signup")
    public ModelAndView signup(ModelAndView mView, UsersDto dto) {
        service.addUser(dto);
        mView.setViewName("users/signup");
        return mView;
    }
    
    // 로그인 폼 요청 처리
    @RequestMapping(method = RequestMethod.GET, value = "/users/login_form")
    public String loginForm() {
        return "users/login_form";
    }

    // 로그인 요청 처리
    @RequestMapping("/users/login")
    public ModelAndView login(ModelAndView mView, UsersDto dto, String url, HttpSession session) {
        /*
         * 서비스에서 비즈니스 로직을 처리할 때 필요로 하는 객체를 컨트롤러에서 직접 전달을 해 주어야 한다.
         * HttpServletRequest, HtpServletResponse, HttpSession, ModelAndView등의 객체
         */
        service.loginProcess(dto, session);
        
        String encodedUrl = URLEncoder.encode(url);
        mView.addObject("url", url);
        mView.addObject("encodedUrl", encodedUrl);
        
        mView.setViewName("users/login");
        return mView;
    }

    // 로그아웃 요청 처리
    @RequestMapping("/users/logout")
    public String logout(HttpSession session) {
        // session scope에 저장된 모든 내용을 clear해서 로그아웃 처리를 한다.
        session.invalidate();
        return "users/logout";
    }

    // 개인정보 보기 요청처리
    @RequestMapping("/users/info")
    public String info() {
        return "users/info";
    }
}
