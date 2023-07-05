<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
</head>
<body>
    <script>
        alert(${param.num}번 회원의 정보를 수정하였습니다.);
        location.href="${pageContext.request.contextPath}/member/list"
    </script>
</body>
</html>