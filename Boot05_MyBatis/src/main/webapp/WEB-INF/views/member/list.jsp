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
        <h3>회원목록</h3>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>주소</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tmp" items="${requestScope.list }">
                    <tr>
                        <td>${tmp.num }</td>
                        <td>${tmp.name }</td>
                        <td>${tmp.addr }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>