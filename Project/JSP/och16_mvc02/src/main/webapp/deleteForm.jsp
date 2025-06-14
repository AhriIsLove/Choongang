<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
	<h1>삭제하려면 암호를 입력하세요</h1>
	<form action="deletePro.do">
		<input type="hidden" name="pageNum" value="${pageNum }">
		<input type="hidden" name="num" value="${num }">
		<input type="text" name="passwd">
		<input type="submit" value="확인">
	</form>
</body>
</html>