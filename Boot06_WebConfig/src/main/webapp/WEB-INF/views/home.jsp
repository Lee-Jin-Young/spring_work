<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${empty sessionScope.id}">
                <a href="${pageContext.request.contextPath}/users/login_form">로그인 하러 가기</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/users/info">${id }</a>
                님 로그인중
                <a href="${pageContext.request.contextPath}/users/logout">로그아웃</a>
            </c:otherwise>
        </c:choose>
        
        <h1>인덱스 페이지 입니다.</h1>
        <%-- 
            static 폴더(정적) 자원 사용 
            ${pageContext.request.contextPath}경로는 컨트롤러 -> static or webapp의 순서로 탐색
        --%>
        <img src="${pageContext.request.contextPath}/images/0.png"/>
        <img src="${pageContext.request.contextPath}/images/1.png"/>
        
        <%-- 컨트롤러에 이미지 요청 --%>
        <img src="${pageContext.request.contextPath}/aaa/2.png"/>
        <img src="${pageContext.request.contextPath}/aaa/3.png"/>
        <ul>
            <li><a href="${pageContext.request.contextPath}/html/hello.html">hello.html으로</a></li>
            <%-- .jsp는 동적자원이므로 static 폴더에 위치 시킬 수 없다. --%>
            <li><a href="${pageContext.request.contextPath}/jsp/hello.jsp">hello.jsp로</a></li>
            <%-- ${pageContext.request.contextPath}를 /webapp 으로 인식 --%>
            <li><a href="${pageContext.request.contextPath}/test.jsp">test.jsp로</a></li>
            <li><a href="cafe/insert_form">글쓰기</a></li>
        </ul>
        <h3>공지사항</h3>
        <ul>
            <c:forEach var="tmp" items="${noticeList}">
                <li>${tmp }</li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>