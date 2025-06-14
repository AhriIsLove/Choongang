<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${param.name }님이 좋아하는 동물은?</h1>
<!-- HTML에서 데이터를 받으면 param.name값 -->
<!-- jsp에서 request로 받으면 그냥 파라미터값 -->
${paramValues.pet[0] }
${paramValues.pet[1] }
${paramValues.pet[2] }
</body>
</html>