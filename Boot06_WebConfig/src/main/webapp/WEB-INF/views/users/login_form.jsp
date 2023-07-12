<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageContext.request.contextPath}</title>
</head>
<body>
    <div class="container">
        <form action="login" method="post">
            <input type="text" name="id" id="id"/>
            <button type="submit">전송</button>
        </form>
    </div>
</body>
</html>