<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery/upload_form.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<style>
</style>
</head>
<body>
    <div class="container">
        <h3>이미지 업로드</h3>
            <form action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
                <div>
                    <label for="caption">설명</label>
                    <input type="text" name="caption" id="caption"/>
                </div>
                <div>
                    <label for="image">이미지</label>
                    <input type="file" name="image" id="image" accept=".jpg, .jpeg, .png, .JPG, .JPEG"/>
                </div>
                <button type="submit">업로드</button>
            </form>
            <br />
            <img alt="이미지 미리보기" id="preview"/>
    </div>
    
    <script>
        document.querySelector("#image").addEventListener("change", (e)=>{
            const files = e.target.files;
         
            if(files.length > 0){
                const reader=new FileReader();
                
                reader.onload=(event)=>{
                    //읽은 파일 데이터 얻어내기 
                    const data=event.target.result;
                    document.querySelector("#preview").setAttribute("src", data);
                };
                
                //파일을 DataURL 형식의 문자열로 읽어들이기
                reader.readAsDataURL(files[0]);
            }
        });
    </script>
</body>
</html>