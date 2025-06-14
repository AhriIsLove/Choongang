<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>연산결과 cal1.jsp</h1>
<%
try {
	// 1. num1, num2 parameter 받기
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	// 2. + , -, * , / 출력하기
	out.print(num1 + " + " + num2 + " = " + (num1+num2) + "<p>");
	out.print(num1 + " - " + num2 + " = " + (num1-num2) + "<p>");
	out.print(num1 + " * " + num2 + " = " + (num1*num2) + "<p>");
	out.print(num1 + " / " + num2 + " = " + (num1/num2) + "<p>");
	
} catch (NumberFormatException e) {
	out.println(e.getMessage() + " 문자열 입력했어");
} catch (ArithmeticException e) {
	out.println(e.getMessage() + " 0으로 나누었어");
} catch (Exception e) {
	out.println(e.getMessage());
}

%>
</body>
</html>