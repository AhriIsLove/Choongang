<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL 우승자</h1>
<!-- HTML에서 데이터를 받으면 param.name값 -->
<!-- jsp에서 request로 받으면 그냥 파라미터값 -->
1등 : ${winner[0] }<p>
2등 : ${winner[1] }<p>
3등 : ${winner[2] }<p>
<h1>Java 우승자</h1>
<%
String[] win = (String[])request.getAttribute("winner");
for(int i=0;i<win.length;i++){
	out.println((i+1)+"등 : " + win[i]+"<p>");
}

out.println("테스트 : " + (int)request.getAttribute("test"));
%>
</body>
</html>