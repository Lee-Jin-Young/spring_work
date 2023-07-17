package com.example.boot07.users.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot07.users.dto.UsersDto;
import com.example.boot07.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired 
	private UsersService service;
	
	@Value("${file.location}")
	private String fileLocation;
	
	// 회원가입 폼 요청 처리
	@GetMapping("/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	// 회원가입 요청 처리
	@PostMapping("/users/signup")
	public String signup (UsersDto dto) {
		service.addUser(dto);
		return "users/signup";
	}
	
	// 로그인 폼 요청 처리
	@GetMapping("/users/login_form")
	public String loginForm() {
		return "users/login_form";
	}
	
	// 로그인 요청 처리
	@PostMapping("/users/login")
	public String login(Model model, UsersDto dto, String url, HttpSession session) {
		service.loginProcess(dto, session);
		
		String encodedUrl=URLEncoder.encode(url);
		model.addAttribute("url", url);
		model.addAttribute("encodedUrl", encodedUrl);
		
		return "users/login";
	}
	
	// 로그아웃 요청 처리
	@GetMapping("/users/logout")
	public String logoutForm(HttpSession session) {
		session.invalidate();
		return "users/logout";
	}
	
	// 개인정보 보기 요청 처리
	@GetMapping("/users/info")
	public String info(UsersDto dto, HttpSession session, Model model) {
		service.getInfo(session, dto, model);
		return "users/info";
	}
	
	// 비밀번호 수정 폼 요청 처리
	@GetMapping("/users/pwd_update_form")
	public String pwdUpdateForm() {
		
		return "users/pwd_update_form";
	}
	
	// 비밀번호 수정 요청 처리
	@PostMapping("/users/pwd_update")
	public String pwdUpdate(UsersDto dto, Model model, HttpSession session) {
		service.updateUserPwd(session, dto, model);
		return "users/pwd_update";
	}
	
	// 개인정보 수정 폼 요청 처리
	@GetMapping("/users/update_form")
	public String updateForm(UsersDto dto, HttpSession session, Model model) {
		service.getInfo(session, dto, model);
		return "users/update_form";
	}
	
	// 개인정보 수정 요청 처리
	@PostMapping("/users/update")
	public String update(UsersDto dto, HttpSession session) {
		service.updateUser(dto, session);
		return "redirect:/users/info";
	}
	
	// ajax 프로필 사진 업로드 요청 처리
	@ResponseBody
	@PostMapping("/users/profile_upload")
	public Map<String, Object> profileUpload(HttpServletRequest request, MultipartFile image) {
		return service.saveProfileImage(request, image);
	}
	
	@GetMapping(value = "/users/images/{imageName}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE })
	@ResponseBody
	public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
		// imageName 에는 응답해줄 이미지의 이름이 들어 있다.

		// 읽어들일 파일의 경로
		// C:/folder/imageName.png 형식의 경로
		String absolutePath = fileLocation + File.separator + imageName;
		// 파일에서 읽어들일 InputStream
		InputStream is = new FileInputStream(absolutePath);
		// fileLocation 필드에는 파일이 저장되어 있는 서버의 파일 시스템상에서의 위치가 들어 있다.
		return IOUtils.toByteArray(is);
	}
	
	// 탈퇴 요청 처리
	@GetMapping("/users/delete")
	public String delete(HttpSession session, Model model) {
		service.deleteUser(session, model);
		session.invalidate();
		return "users/delete";
	}
}
