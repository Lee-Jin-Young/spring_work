<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery/upload_form_drag.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<style>
	#dropZone {
		width: 400px;
		height: 400px;
		border: 2px dashed gray;
		border-radius: 20px;
		display: flex;
		justify-content: center;
		align-items: center;
		border-radius: 20px;
	}
	
	#preview {
		display: none;
		object-fit: cover;
		width: 100%;
		height: 100%;
	}
	
	#image {
		display: none;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>이미지 업로드</h3>
		
		<a href="javascript:" id="dropZoneLink" title="업로드 할 이미지 선택">
			<div id="dropZone">
				<p>이곳에 이미지를 끌어놓거나 클릭하세요.</p>
				<img id="preview" src="" alt="" />
			</div>
		</a>
		<br />
		
		<form action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
			<div>
				<label for="caption">설명</label>
				<input type="text" name="caption" id="caption"/>
			</div>
			<div>
				<input type="file" name="image" id="image" accept=".jpg, .jpeg, .png, .JPG, .JPEG"/>
			</div>
		</form>
		<button id="submitBtn">업로드</button>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
	<script>
		function handleFileUpload(files) {
			const files = $("#image").prop("files");
			
			if (files.length > 0) {
				const reader = new FileReader();
				
				reader.onload = function(event) {
					const data = event.target.result;
					$("#preview").attr("src", data);
					$("#preview").show();
					$("#dropZone p").hide();
				};
				reader.readAsDataURL(files[0]);
			}
		}
		
		$("#image").on("change", (e)=>{
				handleFileUpload();
			}
		});

		$("#dropZone")
		.on("dragover", (e)=>{
			e.preventDefault();
		})
		.on("drop", (e)=>{
			e.preventDefault();

			handleFileUpload();
		});
		
		$("#dropZoneLink").on("click",(e) => {
			$("#image").click();
		});
		
		$("#submitBtn").on("click", () => {
			$("form").submit();
		})
	</script>
</body>
</html>