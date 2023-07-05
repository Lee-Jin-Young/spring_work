<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/updateform.jsp</title>
</head>
<body>
    <h1>회원 정보 수정 폼</h1>
    
    <form action="${pageContext.request.contextPath}/guest/update" method="post">
        <div>
            <label for="num">번호</label>
            <input type="text" name="num" value="${dto.num} readonly" />
        </div>
        <div>
            <label for="writer">작성자</label>
            <input type="text" name="writer" value="${dto.writer} readonly" />
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="text" name="pwd" id="pwd" value="${dto.pwd}" />
        </div>
        <div>
            <label for="regdate">작성일</label>
            <input type="text" name="regdate" id="regdate" value="${dto.regdate}" />
        </div>
        <div>
            <textarea name="content" id="content" cols="30" rows="10">${dto.content}</textarea>
        </div>
        
        <button type="submit">전송</button>
        <buttond type="">지우기</buttond>
    </form>
</body>

</html>