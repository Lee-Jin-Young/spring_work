package com.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
/*
 * @SpringBootApplication 어노테이션이 붙어있는 main 메소드가 존재하는 패키지를 포함하여
 * 하위의 모든 패키지에 component scan이 자동 실행된다.
 */

import com.example.aop.util.MemberDto;
import com.example.aop.util.MemberService;
import com.example.aop.util.Messenger;
import com.example.aop.util.WritingUtil;

@SpringBootApplication
public class Boot02AopApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Boot02AopApplication.class, args);
		
		WritingUtil util = ctx.getBean(WritingUtil.class);
		util.writeLetter();
		util.writeReporte();
		util.writeDiary();
		
		System.out.println();
		Messenger m1 = ctx.getBean(Messenger.class);
		String msg = m1.getMessage();
		System.out.println("msg : "+msg);
		
		System.out.println();
		m1.sendGreeting("하나");
		
		System.out.println();
		m1.sendGreeting("둘");
		
		System.out.println();
		m1.sendGreeting("셋");
		
		System.out.println();
		MemberService service = ctx.getBean(MemberService.class);
		MemberDto dto = service.getMember(1);
		System.out.println(dto.getNum() +"|"+ dto.getName() +"|"+ dto.getAddr());
	}

}
