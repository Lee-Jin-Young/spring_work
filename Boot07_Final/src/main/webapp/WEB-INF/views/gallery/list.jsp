<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
    /* 화면의 폭이 575px 이하 일때 적용할 css */
   @media(max-width: 575px){
           .img-wrapper{
               height: 400px;
           }
   }
   
    .img-wrapper{
        height: 250px;
        /* transform 을 적용할 때 0.3s 동안 순차적으로 적용하기 */
        transition: transform 0.3s ease-out;
    }
    
    .img-wrapper:hover{
        /* 원본 크기의 1.1 배로 확대 */
        transform: scale(1.1);
    }
    
    .card .card-text{
        /* 한줄 넘는 길이에 대해서는 줄임표 */
        display:block;
        white-space : nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
    }
        .img-wrapper img{
            width: 100%;
            height: 100%;
            /* fill | contain | cover | scale-down | none(default) */
            /*    
                cover - 부모의 크기에 맞게 키운 후 크롭, 비율은 일정하게 증가
                contain - 크롭하지 않는 대신 빈 공간이 남을 수 있음
                fill - 부모의 크기에 딱 맞게 비율 관계 없이 맞춘다.(이미지가 일그러질 수 있음)
                scale-down - 가로, 세로 중에 큰 것을 부모의 크기에 맞춘 상태까지만 커지거나 작아지고, 비율은 일정
            */
        object-fit: cover;    
        }
</style>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/gallery/upload_form">사진 업로드 하러 가기</a>
        <br />
        <a href="${pageContext.request.contextPath}/gallery/upload_form_drag">사진 업로드 하러 가기2</a>
        <br />
        <a href="${pageContext.request.contextPath}/gallery/upload_form_ajax">사진 업로드 하러 가기3</a>
        <h1>갤러리 목록 입니다.</h1>
            
        <div class="row">
            <c:forEach var="tmp" items="${list }">
                <div class="col-sm-6 col-md-4 col-lg-3">
                    <div class="card mb-3">
                        <a href="${pageContext.request.contextPath}/gallery/detail?num=${tmp.num}">
	                        <div class="img-wrapper">
	                            <img class="card-img-top" src="${pageContext.request.contextPath}${tmp.imagePath}" />
	                        </div>
                        </a>
                        <div class="card-body">
                            <p class="card-text">${tmp.caption}</p>
                            <p class="card-text">by <strong>${tmp.writer}</strong></p>
                            <p><small>${tmp.regdate}</small></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        
        
        <nav>
            <ul class="pagination justify-content-center">
                <c:choose>
                    <c:when test="${startPageNum ne 1 }">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/gallery/list?pageNum=${startPageNum - 1}">Prev</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="javascript:">Prev</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                
                <c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
                    <c:choose>
                        <c:when test="${i eq pageNum }">
                            <li class="page-item active">
                                <a class="page-link" href="${pageContext.request.contextPath}/gallery/list?pageNum=${i}">${i }</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/gallery/list?pageNum=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                
                <c:choose>
                    <c:when test="${endPageNum lt totalPageCount }">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/gallery/list?pageNum=${endPageNum + 1}">Next</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="javascript:">Next</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>    
    </div>
    
    <%--
    <script>
        // card 이미지의 부모 요소를 선택해서 imgLiquid  동작(jquery plugin 동작) 하기 
        $(".img-wrapper").imgLiquid();
    </script>
    --%>
</body>
</html>