<%@page import="java.util.Scanner"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>연산결과 cal3.jsp</h1>
	<%
	try {
		// 1. num1, num2 parameter 받기
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		// 2. + , -, * , / 출력하기
		out.print(num1 + " + " + num2 + " = " + (num1 + num2) + "<p>");
		out.print(num1 + " - " + num2 + " = " + (num1 - num2) + "<p>");
		out.print(num1 + " * " + num2 + " = " + (num1 * num2) + "<p>");
		out.print(num1 + " / " + num2 + " = " + (num1 / num2) + "<p>");
	} catch (NumberFormatException e) {
	%>
	<script type="text/javascript">
		alert("그게 숫자냐");
		history.go(-1);//이전페이지로 이동
	</script>
	<%
	} catch (ArithmeticException e) {
	%>
	<script type="text/javascript">
		alert("0으로 못나눠");
		history.back();//이전페이지로 이동
	</script>
	<%
	} catch (Exception e) {
	%>
	<script type="text/javascript">
		alert("에러다!");
		location.href = "num2.html";//이전페이지(고정)로 이동
	</script>
	<%
	}
	%>


</body>
</html>