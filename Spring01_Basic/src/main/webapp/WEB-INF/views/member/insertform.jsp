<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/insertform.jsp</title>
</head>
<body>
    <div class="container">
        <h1>회원추가</h1> 
        <form action="${pageContext.request.contextPath}/member/insert" method="post">
            <p>번호 <input type="text" name="num" /></p>
            <p>이름 <input type="text" name="name"/></p>
            <p>주소 <input type="text" name="addr"/></p>
            
            <button type="submit">전송</button>
        </form>
        
        <h1>회원추가2</h1>
        <form action="${pageContext.request.contextPath}/member/insert2" method="post">
            <p>번호 <input type="text" name="num" /></p>
            <p>이름 <input type="text" name="name"/></p>
            <p>주소 <input type="text" name="addr"/></p>
            
            <button type="submit">전송</button>
        </form>
        
        <h1>회원추가3</h1>
        <form action="${pageContext.request.contextPath}/member/insert3" method="post">
            <p>번호 <input type="text" name="num" /></p>
            <p>이름 <input type="text" name="name"/></p>
            <p>주소 <input type="text" name="addr"/></p>
            
            <button type="submit">전송</button>
        </form>
    </div>
</body>
</html>