<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/guest/insertform.jsp</title>
</head>
<body>
    <div class="container">
        <h1>글 작성폼</h1>
        <form action="${pageContext.request.contextPath}/guest/insert" method="post">
            <div>
                <label for="writer">이름</label>
                <input type="text" name="writer" id="writer" />
            </div>
            <div>
                <label for="pwd">비밀번호</label>
                <input type="text" name="pwd" id="pwd" />
            </div>
            <div>
                <textarea name="content" id="" cols="30" rows="10"></textarea>
            </div>
            <button type="submit">추가</button>
        </form>
    </div>
</body>
</html>