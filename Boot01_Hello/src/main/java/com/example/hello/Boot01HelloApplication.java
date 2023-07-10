package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Boot01HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot01HelloApplication.class, args);
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		Car car1 = ctx.getBean(Car.class);
		car1.drive();
	}
}
