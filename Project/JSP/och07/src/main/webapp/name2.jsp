<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>이름1</h1>
${param.name }
<h1>이름2(삼항연산자)</h1>
<!-- 비어있는지 확인 -->
${empty param.name ? "손님" : param.name } 안녕하세요<p>

</body>
</html>