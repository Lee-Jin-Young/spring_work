package com.example.boot07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.boot07.interceptor.LoginInterceptor;

/*
 * [Spring MVC 관련 설정]
 * WebMvcConfigurer 인터페이스를 구현 후 설정에 필요한 메소드만 오버라이딩
 * Resource Handler, Interceptor, view page등의 설정을 한다.
 * 설정에 관련된 클레스에는 @Configuration을 붙인다.
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired LoginInterceptor loginInterceptor;
	
	//Interceptor를 추가할 때 오버라이드 하는 메소드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
			.addPathPatterns("/users/*", "/cafe/*", "/file/*", "/gallery/*")
			/*
			 * .excludePathPatterns()의 매개변수는 String... type ...은 가변인자임을 뜻한다.
			 */
			.excludePathPatterns("/users/login_form", "/users/login", "/users/signup_form", "/users/signup", "/cafe/list", "/file/list", "/gallery/list");
	}
	
	//webapp/resources 폴더를 정적파일 폴더로 사용
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
	}
}
