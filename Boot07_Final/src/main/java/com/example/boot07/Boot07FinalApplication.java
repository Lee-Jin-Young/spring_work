package com.example.boot07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/*
 *  @PropertySource(value=" 커스텀 properties 파일의 위치" )
 *  classpath: 는 resources 폴더를 가리킨다.
 */
@SpringBootApplication
@PropertySource(value = "classpath:custom.properties")
public class Boot07FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot07FinalApplication.class, args);
	}

}
