package com.young.spring03.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor { 
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      HttpSession session=request.getSession();
      String id=(String)session.getAttribute("id");
      
      if(id == null) {
         String url=request.getRequestURI();
         String query=request.getQueryString();
         String encodedUrl=null;
         if(query==null) {
            encodedUrl=URLEncoder.encode(url);
         }else {
            // 원래 목적지가 /test/xxx.jsp 라고 가정하면 아래와 같은 형식의 문자열을 만든다.
            // "/test/xxx.jsp?a=xxx&b=xxx ..."
            encodedUrl=URLEncoder.encode(url+"?"+query);
         }
         
         //로그인 안되어 있을 때
         String cPath=request.getContextPath();
         response.sendRedirect(cPath+"/users/loginform?url="+encodedUrl);
         return false;
      }
      
      return true;
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
         ModelAndView modelAndView) throws Exception {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
         throws Exception {
      // TODO Auto-generated method stub
      
   }
}