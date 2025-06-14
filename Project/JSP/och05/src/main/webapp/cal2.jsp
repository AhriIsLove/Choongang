<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>연산결과 cal2.jsp</h1>
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
} catch (Exception e) {
	request.setAttribute("exceptionError", e); // 에러 객체 자체를 담아도 돼!
	//대신 사용할때 exceptionError를 getParameter("exceptionError")로 받아서 사용해야 하지 
    
	//에러페이지로 이동(단순)
	RequestDispatcher rd = request.getRequestDispatcher("error0.jsp");
	rd.forward(request, response);
}
%>
</body>
</html>