package com.young.spring04.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/*
 *  Spring MVC 가 동작중에 특정 type 의 예외가 발생하면  해당 예외를 여기서 처리 할수 있다.
 */
@ControllerAdvice
public class ExceptionController {
	// NotDeleteException type 의 예외가 발생하면 호출되는 메소드 
	@ExceptionHandler(NotDeleteException.class)
	public ModelAndView notDelete(NotDeleteException nde) {
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception", nde);
		mView.setViewName("error/info");
		return mView;
	}
	
}
