<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>1부터 100까지 합</h1>
	<h5>1.Expression --> sum1</h5>
	<%=request.getAttribute("sum1")%>
	<p>
	<h5>1.EL --> sum2</h5>
	${sum2 }
</body>
</html>