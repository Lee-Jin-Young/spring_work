<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/list.jsp</title>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/member/insertform">회원추가</a>
        <h1>회원목록</h1>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>주소</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tmp" items="${list}">
                    <tr>
                        <td>${tmp.num}</td>
                        <td>${tmp.name}</td>
                        <td>${tmp.addr}</td>
                        <td><a href="${pageContext.request.contextPath}/member/update">수정</a></td>
                        <td><a href="${pageContext.request.contextPath}/member/delete">삭제</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>