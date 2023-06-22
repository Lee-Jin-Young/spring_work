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
	<div>
		<label for="num">번호</label>
		<input type="text" name="num" value="${dto.num} readonly"/>
	</div>
	<div>
		<label for="name">이름</label>
		<input type="text" name="name" id="name" value="${dto.name}"/>
	</div>
	<div>
		<label for="addr">주소</label>
		<input type="text" name="addr" id="addr" value="${dto.addr}"/>
	</div>
	
	<button type="submit">전송</button>
	<buttond type="">지우기</buttond>
	</body>
</html>