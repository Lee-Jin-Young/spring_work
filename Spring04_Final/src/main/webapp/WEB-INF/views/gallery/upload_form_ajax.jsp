<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageContext.request.contextPath}</title>
<style>
    #dropZone{
        width: 400px;
        height: 400px;
        border: 2px dashed red;
        border-radius: 20px;
        /* 아래는 자식 contents 를 상하 좌우로 가운데 정렬 하기 위한 css */
        display: flex;
        justify-content: center;
          align-items: center;
    }
    
    /* img 요소에 적용할 css */
    #preview{
        display: none;
        object-fit: cover;
        width: 100%;
        height: 100%;
        border-radius:20px;
    }
    
    #image{
        display: none;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>이미지 업로드 폼</h1>

            <div>
                <label for="caption">설명</label>
                <input type="text" name="caption" id="caption"/>
            </div>

        <input type="file" name="image" id="image" accept=".jpg, .jpeg, .png, .JPG, .JPEG"/>

            <!-- drag and drop 을 할 div -->
            <a href="javascript:" id="dropZoneLink" title="업로드 할 이미지 선택">
                <div id="dropZone">
                    <p>이미지를 drag drop 또는 여기를 클릭하세요</p>
                    <img src="" id="preview"/>
                </div>
            </a>
            <button id="submitBtn">업로드</button>
    </div>
    
    <script>
        document.querySelector("#submitBtn").addEventListener("click", ()=>{
            const caption=document.querySelector("#caption").value;
            const files=document.querySelector("#image").files;
            if( caption.length < 3 || files.length == 0){
                alert("caption 을 3 글자 이상 입력하고 업로드할 이미지를 선택해 주세요");
                return;
            }
            
            const data=new FormData();
            data.append("caption", caption);
            const file=files[0];
            data.append("image", file);
            /*
                fetch() 함수를 호출하면서 method:"post", body:FormData 객체를 전달하면
                <form  method="post" enctype="multipart/form-data"> 폼을 전송한것과 같다
            */
            fetch("${pageContext.request.contextPath }/gallery/ajax_upload", {
                method:"post",
                body:data
            })
            .then(res=>res.json())
            .then(data=>{
                console.log(data);
                if(data.isSuccess){
                    alert(file.name+" 이미지를 성공적으로 업로드 했습니다.");
                    
                    document.querySelector("#preview").style.display="none";
                    document.querySelector("#dropZone p").style.display="block";
                    document.querySelector("#image").value="";
                    document.querySelector("#caption").value="";
                }
            });
        });
    
        document.querySelector("#dropZoneLink").addEventListener("click", ()=>{
            document.querySelector("#image").click();
        });
    
        const dropZone=document.querySelector("#dropZone");
        
        dropZone.addEventListener("dragover", (e)=>{
            e.preventDefault();
        });
        
        dropZone.addEventListener("drop", (e)=>{
            e.preventDefault();

            const files=e.dataTransfer.files;
            console.log(files[0]);
            // drop 된 파일의 정보를 조사해서 이미지 파일이 아니면 종료
            const type=files[0].type;
            console.log(type);
            if( type != "image/png" && type != "image/jpg" && type != "image/gif"){
                alert("이미지 파일을 drop 하세요!");
                return;
            }
            
            //만일 파일 데이터가 존재한다면
            if(files.length > 0){
                const reader=new FileReader();
                //파일데이터를 모드 읽었을때 실행할 함수 등록
                reader.onload=(event)=>{
                    const data=event.target.result;
                    
                    document.querySelector("#preview").setAttribute("src", data);
                    document.querySelector("#preview").style.display="block";
                    document.querySelector("#dropZone p").style.display="none";
                };
                //파일을 DataURL 형식의 문자열로 읽어들이기
                reader.readAsDataURL(files[0]);
                document.querySelector("#image").files=files;
            }
        });
    
        document.querySelector("#image").addEventListener("change", (e)=>{
            const files = e.target.files;
            if(files.length > 0){
                const reader=new FileReader();
                //파일데이터를 모드 읽었을때 실행할 함수 등록
                reader.onload=(event)=>{
                    const data=event.target.result;
                    
                    document.querySelector("#preview").setAttribute("src", data);
                    document.querySelector("#preview").style.display="block";
                    document.querySelector("#dropZone p").style.display="none";
                };
                //파일을 DataURL 형식의 문자열로 읽어들이기
                reader.readAsDataURL(files[0]);
            }
        });
    </script>
</body>
</html>