package com.example.boot07.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        String id=(String)session.getAttribute("id");
        
        //만일 로그인을 하지 않았다면 로그인 페이지로
        if(id == null) {
            String url=request.getRequestURI();
            String query=request.getQueryString();
            String encodedUrl=null;
            
            if(query==null) {
                encodedUrl=URLEncoder.encode(url);
            }else {
                encodedUrl=URLEncoder.encode(url+"?"+query);
            }
            
            String cPath=request.getContextPath();
            response.sendRedirect(cPath+"/users/login_form?url="+encodedUrl);
            return false;
        }
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}