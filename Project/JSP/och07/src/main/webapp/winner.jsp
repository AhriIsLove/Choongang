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
String[] winner = {"홍길동", "양만춘", "강민첨"};
int[] num1 = new int[3];
num1[0] = 1;
num1[1] = 3;
num1[2] = 5;
int[] num2 = new int[] {1,3,5};

request.setAttribute("winner", winner);
request.setAttribute("test", 123);
RequestDispatcher rd = request.getRequestDispatcher("winnerResult.jsp");
rd.forward(request, response);
%>
</body>
</html>