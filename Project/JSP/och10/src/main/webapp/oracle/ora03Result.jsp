<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>부서정보</h1>
	부서코드 : <%=request.getAttribute("deptno") %><p>
	부서명 : <%=request.getAttribute("dname") %><p>
	근무지 : <%=request.getAttribute("loc") %><p>
	<%
		// 3. ora03Result.jsp
		// 부서정보
		// 부서코드 : 20
		// 부서명 : RESEARCH
		// 근무지 : DALLAS
	%>
</body>
</html>