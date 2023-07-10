<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/insertform.jsp</title>
<style>
    #profileForm {
        display : none;
    }
    #profileLink img {
        width : 200px;
        height : 200px;
        border : 1px solid red;
        border-radius : 50%;
    }
</style>
</head>
<body>
    <div class="container">
        <h3>파일 업로드 폼1</h3>
        <form action="${pageContext.request.contextPath}/file/upload"
            method="post" enctype="multipart/form-data">
            제목 <input type="text" name="title" /><br /> 첨부파일 <input type="file"
                name="myFile" /><br />
            <button type="submit">업로드</button>
        </form>

        <h3>파일 업로드 폼2</h3>
        <form action="${pageContext.request.contextPath}/file/upload2"
            method="post" enctype="multipart/form-data">
            제목 <input type="text" name="title" /><br /> 첨부파일 <input type="file"
                name="myFile" /><br />
            <button type="submit">업로드</button>
        </form>

        <h3>이미지 업로드 폼</h3>
        <form id="uploadForm" action="${pageContext.request.contextPath}/image/upload"
            method="post" enctype="multipart/form-data">
            이미지 <input type="file" name="image" accept=".jpg, .jpeg, .JPG, .gif, .png, PNG" /><br />
            <button type="submit">업로드</button>
        </form>
        <br />
        <div id="imageWrapper"> </div>
        <div>
            <a id="profileLink" href="javascript:">프로필</a>
        </div>
        <form action="${pageContext.request.contextPath}/image/upload"
            method="post" enctype="multipart/form-data"
            id="profileForm">
            이미지 <input id="file" type="file" name="image" accept=".jpg, .jpeg, .JPG, .gif, .png, PNG" /><br />
        </form>
        
        <script src="${pageContext.request.contextPath}/resources/js/my_util.js"></script>
        <script>
            document.querySelector("#uploadForm").addEventListener("submit", (e) => {
                e.preventDefault();
                
                /*
                ajaxFormPromise(e.target)
                .then(res=>res.json())
                .then(data=>{
                   
                });
                */
                //만일 gura_util 을 사용하지 않는다면
                
                //서버에 전송할 data 를 구성한다.
                let data=new FormData(e.target);
                // fetch() 함수가 리턴하는 Promise 객체를 
                fetch("${pageContext.request.contextPath }/image/upload",{
                    method:"post",
                    body:data
                })
                .then(res=>res.json())
                .then(data=>{
                    console.log(data);
                    //data 는 {imageUrl:"/resources/upload/xxx"} 형식의 object 이다.
                    const imgString=`<img src="${pageContext.request.contextPath }\${data.imageUrl}">`;
                    console.log(imgString);
                    document.querySelector("#imageWrapper").innerHTML=imgString;
                });
            });
            
            document.querySelector("#profileLink").addEventListener("click", ()=>{
                document.querySelector("#file").click();
            });
            
            document.querySelector("#file").addEventListener("change", ()=>{
                // 폼에 입력한 (선택한파일) 내용을 ajax 로 제출하기
                const form=document.querySelector("#profileForm");
                ajaxFormPromise(form)
                .then(res=>res.json())
                .then(data=>{
                    const imgString=`<img src="${pageContext.request.contextPath }\${data.imageUrl}">`;
                    document.querySelector("#profileLink").innerHTML=imgString;
                });
             });
      </script>


    </div>
</body>
</html>