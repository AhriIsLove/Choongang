<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	                    
	 <form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" >
		파일선택:<input type="file" name="file1" multiple="multiple"> <p>
		Title:<input type="text" name="title"> <p>
		<input type="submit">
	 </form>

</body>
</html>