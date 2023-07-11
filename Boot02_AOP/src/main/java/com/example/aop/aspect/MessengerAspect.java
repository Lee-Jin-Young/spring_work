package com.example.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MessengerAspect {

	@Around("execution(void send*(..))")
	public void checkGreeting(ProceedingJoinPoint joinPoint) throws Throwable {		
		Object[] args = joinPoint.getArgs();
		
		for(Object tmp : args) {
			if(tmp instanceof String) {
				String msg = (String)tmp;
				System.out.println("aspect에서 읽어낸 내용 : "+msg);
				if(msg.contains("둘")) {
					System.out.println("둘은 금지된 단어입니다.");
					return;
				}
			}
		}
		
		// aspect가 적용된 메소드가 호출 되기 직전에 할 작업위치

		// proceed를 호출하여 aspect가 적용된 메소드 실행
		joinPoint.proceed();
		
		// aspect가 적용된 메소드가 호출 된 직후에 할 작업위치
		
		System.out.println("aspect가 적용된 메소드가 리턴");
	}
	
	/*
	 * return type은 String이고 get으로 시작되는 메소드이며, 메소드에 전달되는 인자는 없다.
	 * java.lang패키지에 있는 type은 패키지명 생략 가능
	 */
	@Around("execution(String com.example.aop.util.*.get*())")
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable {
		Object obj = joinPoint.proceed();
		
		String a = (String)obj;
		
		return "checkReturn() 리턴값";
	}
}
