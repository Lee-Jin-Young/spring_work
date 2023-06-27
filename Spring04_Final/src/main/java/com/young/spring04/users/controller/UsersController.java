// UsersController.java
package com.young.spring04.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
    @RequestMapping(method = RequestMethod.POST, value = "/users/signup")
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
    @RequestMapping(method = RequestMethod.POST, value = "/users/login")
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

    // 개인정보 보기 요청 처리
    @RequestMapping("/users/info")
    public ModelAndView info(HttpSession session, ModelAndView mView) {
    	service.getInfo(session, mView);
    	mView.setViewName("users/info");
        return mView;
    }
    
    // 비밀번호 수정 폼 요청 처리
    @RequestMapping("/users/pwd_update_form")
    public String pwdUpdateForm() {
    	return "users/pwd_update_form";
    }
    
    // 비밀번호 수정 요청 처리
    @RequestMapping("/users/pwd_update")
    public ModelAndView pwdUpdate(UsersDto dto, ModelAndView mView, HttpSession session) {
    	service.updateUserPwd(session, dto, mView);
    	mView.setViewName("users/pwd_update");
    	
    	return mView;
    }
    
    // 회원정보 수정 폼 요청 처리
    @RequestMapping("/users/update_form")
	public ModelAndView updateform(HttpSession session, ModelAndView mView) {
		service.getInfo(session, mView);
		mView.setViewName("users/update_form");
		return mView;
	}
    
  //개인정보 수정 요청 처리
  	@RequestMapping(method=RequestMethod.POST, value = "/users/update")
  	public ModelAndView update(UsersDto dto, HttpSession session, ModelAndView mView, HttpServletRequest request) {
  		service.updateUser(dto, session);
  		
  		mView.setViewName("redirect:/users/info");
  		return mView;
  	}
    
    // ajax 프로필 사진 업로드 요청 처리
    @RequestMapping(method = RequestMethod.POST, value = "/users/profile_upload")
    @ResponseBody
    public Map<String, Object> profileUpload(HttpServletRequest request, MultipartFile image) {
    	return service.saveProfileImage(request, image);
    }
    
    // 회원 탈퇴 요청 처리
    @RequestMapping("/users/delete")
    public ModelAndView delete(HttpSession session, ModelAndView mView) {
    	service.deleteUser(session, mView);
    	
    	session.removeAttribute("id");
    	
    	mView.setViewName("home");
    	return mView;
    }
}
