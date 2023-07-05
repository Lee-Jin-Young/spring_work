package com.young.step01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FortuneController {
    @RequestMapping("/fortune")
    public String fortune(HttpServletRequest request) {
        String fortuneToday = "아쉬울 것 없는 날!";
        request.setAttribute("fortuneToday", fortuneToday);
        
        return "fortune";
    }
    
    @RequestMapping("/fortune2")
    public ModelAndView fortune2() {
        String fortuneToday = "아쉬울 것 없는 날!";
        
        //HttpServletRequest객체 대신 ModelAndView객체를 생성함
        ModelAndView mView = new ModelAndView();
        mView.addObject("fortuneToday",fortuneToday);
        mView.setViewName("fortune");
        
        return mView;
    }
    
    @RequestMapping("/fortune3")
    public ModelAndView fortune3(ModelAndView mView) {
        String fortuneToday = "아쉬울 것 없는 날!";
        
        mView.addObject("fortuneToday",fortuneToday);
        mView.setViewName("fortune");
        
        return mView;
    }
}
