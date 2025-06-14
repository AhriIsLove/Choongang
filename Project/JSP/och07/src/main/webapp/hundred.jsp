<%@page import="java.io.Console"%>
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
	int sum = 0;
	for (int i = 1; i <= 100; i++) {
		sum += i;
	}
	request.setAttribute("sum1", sum);
	request.setAttribute("sum2", sum);
	
	RequestDispatcher rd = request.getRequestDispatcher("hunResult.jsp");
	rd.forward(request, response);
	
	//페이지 이동 후 코드도 먹나요?
	System.out.print("test");//실행은 된다
	sum *= -1;//값 변동은 의미가 없다
	%>
</body>
</html>