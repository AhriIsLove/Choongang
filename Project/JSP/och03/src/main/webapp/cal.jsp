<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int num1 = Integer.parseInt(request.getParameter("num1"));
int num2 = Integer.parseInt(request.getParameter("num2"));

int add = num1 + num2;
int minus = num1 - num2;
int multi = num1 * num2;
int division = num1 / num2;

%>
<!-- Expression(표현식) -->
덧셈 : <%=add%><p>
뺄셈 : <%=minus%><p>
곱셈 : <%=multi%><p>
나눗셈 : <%=division%><p>
</body>
</html>