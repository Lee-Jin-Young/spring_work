<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/login_form.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>로그인</h1>
        <form action="${pageContext.request.contextPath}/users/login" method="post">
            <c:choose>
                <c:when test="${ empty param.url }">
                    <input type="hidden" name="url" value="${pageContext.request.contextPath}/"/>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="url" value="${param.url }"/>
                </c:otherwise>
            </c:choose>
            <div class="row g-3 align-items-center mb-2">
                <div class="col-auto"><label for="id" class="form-label">아이디</label></div>
                <div class="col-auto"><input type="text" class="form-control" name="id" id="id"/></div>
            </div>
            <div class="row g-3 align-items-center mb-2">
                <div class="col-auto"><label for="pwd" class="form-label">비밀번호</label></div>
                <div class="col-auto"><input type="password" class="form-control" name="pwd" id="pwd"/></div>
            </div>
            <button type="submit" class="btn btn-primary">로그인</button>
        </form>
    </div>
</body>
</html>