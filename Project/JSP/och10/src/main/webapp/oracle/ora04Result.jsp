<%@page import="och10.Dept"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Expression 부서정보</h1>
<% 
Dept dept = (Dept)request.getAttribute("dept");
%>
부서코드 : <%=dept.getDeptno() %><p>
부서명 : <%=dept.getDname() %><p>
근무지 : <%=dept.getLoc() %><p>

<h1>EL 부서정보</h1>
부서코드 : ${dept.getDeptno() }<p>
부서명 : ${dept.dname }<p>
근무지 : ${dept.loc }<p>

private 변수값을 가져오는게 아니다.<br>
getDname()의 다른 표현으로 사용하는것이다.<p>

public으로 선언 해도<br>
getDname()을 사용하는 것이기 때문에 getDname()에 접근이 안된다면 불가능하다<p> 

</body>
</html>