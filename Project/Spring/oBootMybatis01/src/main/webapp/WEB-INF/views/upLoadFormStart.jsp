<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	UpLoad Image :
	<img alt="UpLoad Image" src="${pageContext.request.contextPath }/upload1/${savedName}">
	<form action="uploadForm" id="form1" method="post" enctype="multipart/form-data">
		파일 :
		<input type="file" name="file1">
		<p />
		제목 :
		<input type="text" name="title">
		<p />
		<input type="submit">
		<p />
	</form>
</body>
</html>