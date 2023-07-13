<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/pwd_update.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${isSuccess }">
            <div class="alert alert-success" role="alert">
	            <p><strong>${id }</strong> 님 비밀번호를 수정하고 로그아웃되었습니다.</p>
	            <a href="${pageContext.request.contextPath}/users/login_form" class="btn btn-success">다시 로그인 하러 가기</a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger" role="danger">
	            <p>이전 비밀번호가 일치하지 않습니다.</p>
                <a href="${pageContext.request.contextPath}/users/pwd_update_form" class="btn btn-danger">재시도</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
