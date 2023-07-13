<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/signup.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="alert alert-success" role="alert">
            <p><strong>${param.id }</strong> 님 가입 되었습니다.</p>
            <a href="${pageContext.request.contextPath}/users/login_form" class="btn btn-success">로그인 하러가기</a>
		</div>
    </div>    
</body>
</html>