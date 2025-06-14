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
int num = Integer.parseInt(request.getParameter("num"));

//1부터 num까지의 합
int sum = 0;
for(int i=1; i<=num;i++){
	sum += i;
	out.println(i + "일때 합계는 " + sum + "<br>");
}
%>
</body>
</html>