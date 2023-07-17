<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>/views/users/login_form.jsp</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
	        text-anchor: middle;
	        -webkit-user-select: none;
	        -moz-user-select: none;
	        -ms-user-select: none;
	        user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
            font-size: 3.5rem;
            }
        }
    </style>
    <link href="signin.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <%-- <h1>로그인</h1>
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
        </form> --%>
	<form class="form-signin" action="${pageContext.request.contextPath}/users/login" method="post">
	    <div class="text-center mb-4">
		    <h1 class="h3 mb-3 font-weight-normal">로그인</h1>
	    </div>
	    
	    <div>
	        <c:choose>
                <c:when test="${ empty param.url }">
                    <input type="hidden" name="url" value="${pageContext.request.contextPath}/"/>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="url" value="${param.url }"/>
                </c:otherwise>
            </c:choose>
	    </div>
	    
	    <div class="form-label-group">
	    <input type="text" id="id" name="id" class="form-control" placeholder="아이디" required autofocus>
	    <label for="id">아이디</label>
	    </div>
	
	    <div class="form-label-group">
	        <input type="password" id="pwd" name="pwd" class="form-control" placeholder="비밀번호" required>
	        <label for="pwd">비밀번호</label>
	    </div>
	
	    <div class="checkbox mb-3">
		    <label>
		        <input type="checkbox" value="remember-me"> Remember me
		    </label>
	    </div>
	    <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
	    <p class="mt-5 mb-3 text-muted text-center">&copy; 2023</p>
    </form>
    </div>
</body>
</html>