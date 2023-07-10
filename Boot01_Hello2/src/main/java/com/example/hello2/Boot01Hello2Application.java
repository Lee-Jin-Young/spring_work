package com.example.hello2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.hello2.pc.Computer;
import com.example.hello2.util.Remocon;

@SpringBootApplication
public class Boot01Hello2Application {

	public static void main(String[] args) {
		// run()메소드가 리턴하는 ApplicationContext객체의 참조값을 변수에 담는다.
		ApplicationContext ctx = SpringApplication.run(Boot01Hello2Application.class, args);
		
		Car car1 = ctx.getBean(Car.class);
		car1.drive();
		
		Car car2 = ctx.getBean(Car.class);
		car2.drive();
		
		if(car1 == car2) {
			System.out.println("car1 == car2");
		}
		
		// 스프링이 관리하는 객체 중 myRemocon type의 참조값 가져오기
		//Remocon r1 = ctx.getBean(Remocon.class);
		
		// 스프링이 관리하는 객체 중 myRemocon이라는 이름의 객체를 가져오기
		Remocon r1 = (Remocon)ctx.getBean("myRemocon");
		r1.up();
		r1.down();
		
		Remocon r2 = (Remocon)ctx.getBean("tvRemocon");
		r2.up();
		r2.down();
		
		Computer com1 = ctx.getBean(Computer.class);
		com1.action();
	}

}
