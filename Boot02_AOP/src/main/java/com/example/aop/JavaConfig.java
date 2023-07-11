package com.example.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.aop.aspect.MessengerAspect;
import com.example.aop.util.MemberService;
import com.example.aop.util.Messenger;

@Configuration
@EnableAspectJAutoProxy
public class JavaConfig {
	@Bean
	public Messenger myMessenger() {
		return new Messenger();
	}
	
	@Bean
	public MessengerAspect msa() {
		return new MessengerAspect();
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService();
	}
}
