<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
</head>
<body>
   <div class="container">
      <h1>인덱스 페이지 입니다</h1>
      <ul>
         <li><a href="${pageContext.request.contextPath}/member/insertform">파라미터 추출 테스트</a></li>
         <li><a href="${pageContext.request.contextPath}/fortune">오늘의 운세보기1</a></li>
         <li><a href="${pageContext.request.contextPath}/fortune2">오늘의 운세보기2</a></li>
         <li><a href="${pageContext.request.contextPath}/fortune3">오늘의 운세보기3</a></li>
         <li><a href="${pageContext.request.contextPath}/member/delete?num=1">작업 후 리다이렉트 응답</a></li>
         <li><a href="${pageContext.request.contextPath}/member/delete?num=2">작업 후 리다이렉트 응답</a></li>
         <li><a href="${pageContext.request.contextPath}/test/json1">JSON 응답1</a></li>
         <li><a href="${pageContext.request.contextPath}/test/json2">JSON 응답2</a></li>
         <li><a href="${pageContext.request.contextPath}/test/json3">JSON 응답3</a></li>
         <li><a href="${pageContext.request.contextPath}/test/json4">JSON 응답4</a></li>
         <li><a href="${pageContext.request.contextPath}/test/json5">JSON 응답5</a></li>
         <li><a href="${pageContext.request.contextPath}/test/json6">JSON 응답6</a></li>
      </ul>
      
      <h2>공지사항</h2>
      <ul>
         <c:forEach var="tmp" items = "${requestScope.noticeList }">
            <li>${tmp }</li>
         </c:forEach>
      </ul>
   </div>
</body>
</html>