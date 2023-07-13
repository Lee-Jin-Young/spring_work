<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/pwd_update_form.jsp</title>
</head>
<body>
    <div class="container">
        <h1>비밀 번호 수정</h1>
        <form action="${pageContext.request.contextPath}/users/pwd_update" method="post" id="myForm">
            <div class="row g-3 align-items-center mb-2">
                <div class="col-auto"><label for="pwd">기존 비밀 번호</label></div>
                <div class="col-auto"><input type="password" name="pwd" id="pwd"/></div>
            </div>
            <div class="row g-3 align-items-center mb-2">
                <div class="col-auto"><label for="newPwd">새 비밀번호</label></div>
                <div class="col-auto"><input type="password" name="newPwd" id="newPwd"/></div>
            </div>
            <div class="row g-3 align-items-center mb-2">
                <div class="col-auto"><label for="newPwd2">새 비밀번호 확인</label></div>
                <div class="col-auto"><input type="password" id="newPwd2"/></div>
            </div>
            <button type="submit" class="btn btn-primary">수정하기</button>
            <button type="reset" class="btn btn-secondary">리셋</button>
        </form>
    </div>
    <script>
        document.querySelector("#myForm").addEventListener("submit", function(e){
            let pwd1=document.querySelector("#newPwd").value;
            let pwd2=document.querySelector("#newPwd2").value;
            if(pwd1 != pwd2){
                alert("비밀번호를 확인 하세요.");
                e.preventDefault();
            }
        });
    </script>
</body>
</html>


