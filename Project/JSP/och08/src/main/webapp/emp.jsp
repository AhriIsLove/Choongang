<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
1.Expresson hiredate Disp
2.EL 사원명 ename Disp
 -->
1.Expresson hiredate Disp : <%=request.getParameter("hiredate").toString() %><p>
2.EL 사원명 ename Disp : ${param.ename }<p>
</body>
</html>