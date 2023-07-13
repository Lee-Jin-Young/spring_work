<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/update_form.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<style>
    #imageForm{
        display: none;
    }
    #profileImage{
        width: 100px;
        height: 100px;
        border: 1px solid #cecece;
        border-radius: 50%;
    }
</style>
</head>
<body>
    <div class="container">
        <h3>회원 정보 수정</h3>
        <a id="profileLink" href="javascript:">
            <c:choose>
                <c:when test="${ empty dto.profile }">
                    <i class="bi bi-person-circle" style="font-size: 100px"></i>
                </c:when>
                <c:otherwise>
                    <img id="profileImage" src="${pageContext.request.contextPath }${ dto.profile}">
                </c:otherwise>
            </c:choose>
        </a>
        <form action="${pageContext.request.contextPath}/users/update" method="post">        
            <input type="hidden" name="profile" 
                value="${ empty dto.profile ? 'empty' : dto.profile }"/>        
            <div class="row g-3 align-items-center">
                <div class="col-auto"><label for="id" class="form-label">아이디</label></div>
                <div class="col-auto"><input type="text" readonly class="form-control-plaintext" id="id" value="${dto.id }"/></div>
            </div>
            <div class="row g-3 align-items-center mb-2">
                <div class="col-auto"><label for="email" class="form-label">이메일</label></div>
                <div class="col-auto"><input type="text" class="form-control" id="email" name="email" value="${dto.email }"/></div>
            </div>
            <button type="submit" class="btn btn-primary">수정확인</button>
            <button type="reset" class="btn btn-secondary">취소</button>
        </form>    
        
        <form id="imageForm" action="${pageContext.request.contextPath}/users/profile_upload" method="post" enctype="multipart/form-data">
            프로필 사진
            <input type="file" id="image" name="image" accept=".jpg, .png, .gif"/>
            <button type="submit">업로드</button>
        </form>
                    
    </div>
    <!-- my_util.js 로딩 -->
    <script src="${pageContext.request.contextPath }/resources/js/my_util.js"></script>
    <script>

        document.querySelector("#profileLink").addEventListener("click", function(){
            document.querySelector("#image").click();
        });    
        
        document.querySelector("#image").addEventListener("change", function(){
            const form=document.querySelector("#imageForm");
            ajaxFormPromise(form)
            .then(function(response){
                return response.json();
            })
            .then(function(data){
                console.log(data);
                document.querySelector("input[name=profile]").value=data.imagePath;
                
                let img=`<img id="profileImage" 
                    src="${pageContext.request.contextPath }\${data.imagePath}">`;
                document.querySelector("#profileLink").innerHTML=img;
            });
        });        
        
    </script>
</body>
</html>
