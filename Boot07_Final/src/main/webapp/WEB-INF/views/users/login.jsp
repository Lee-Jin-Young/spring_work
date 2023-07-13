<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/login.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${not empty sessionScope.id }">
                <div class="alert alert-success" role="alert">
	                <p><strong>${sessionScope.id }</strong>님 로그인 되었습니다.</p>
	                <a href="${requestScope.url}" class="btn btn-success">확인</a>
                 </div>
	        </c:when>
	        <c:otherwise>
	            <div class="alert alert-danger" role="alert">
	                <p>아이디 혹은 비밀 번호가 틀립니다.</p>
	                <a href="login_form?url=${requestScope.encodedUrl}" class="btn btn-danger">다시 시도</a>
	            </div>
            </c:otherwise>
        </c:choose>
    </div>    
</body>
</html>