<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 변수 선언 -->
	<c:set var="color" value="red,orange,yellow,green,blue,navy,violet"></c:set>
	<c:set var="num" value="1-2-3-4-5-6-7"></c:set>

	<c:forTokens var="cnt" items="${num }" delims="-">
		<c:forTokens var="col" items="${color }" delims=",">
			<font color="${col }" size="${cnt }">야호</font>
		</c:forTokens><br>
	</c:forTokens>
</body>
</html>