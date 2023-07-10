package com.example.hello2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hello2.pc.Computer;
import com.example.hello2.pc.Cpu;
import com.example.hello2.util.Remocon;
import com.example.hello2.util.RemoconImpl;
import com.example.hello2.util.TvRemocon;

/*
 * 어떤 객체를 Spring이 생성, 관리할지 설정
 * xml 문서로 설정하던 bean설정을 class기반으로 함
 */
@Configuration
public class JavaConfig {
	/*
	 * 이 메소드에서 리턴되는 객체를 spring이 관리하는 bean이 되도록 함
	 * 아래의 메소드는 xml문서에서 <bean id = "myCar" class="com.example.hello.Car">과 같다
	 */
	@Bean
	public Car myCar() {
		System.out.println("myCar()메소드 호출됨");
		Car c1 = new Car();
		return c1;
	}
	
	@Bean
	public Remocon myRemocon() {
		Remocon r1 = new RemoconImpl();
		return r1;
	}
	
	@Bean
	public Remocon tvRemocon() {
		Remocon r1 = new TvRemocon();
		return r1;
	}
	
	@Bean
	public Cpu getCpu() {
		return new Cpu();
	}
	
	@Bean Computer myComputer() {
		Computer c1 = new Computer(getCpu());
		return c1;
	}
}
